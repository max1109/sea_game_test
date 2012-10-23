package com.example.android_usb_test;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.util.Log;
import ch.serverbox.android.usbcontroller.IUsbConnectionHandler;
import ch.serverbox.android.usbcontroller.L;
import ch.serverbox.android.usbcontroller.UsbController;

import com.devices.Zigbee.ZigBeeDevice;
import com.example.gameData.Protagonist;

public class testUSB {
	private static final String TAG = "testUSB Zigbee管理";
	private int Vid = 0;
	private int Pid = 0;
	private static UsbController sUsbController = null;
	private Protagonist protagonist = null; // 主角
	
	private Activity mainActivity;
	// 定時收訊
	Timer timer = new Timer();

	public ArrayList<ZigBeeDevice> deviceList = new ArrayList<ZigBeeDevice>();
//	private 序列 命令序列 = new 序列(5);
	private StringBuilder resBuff;

	private int number_id_pool = 1 ;
	private String number_id_str = "P" ;

	private static boolean 連線完成 = false;
	
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (sUsbController != null && 連線完成 == true) {
//				L.e("run time");
//				發送命令();
				receiverData();
				if ( !resBuff.equals("") ) { 
					ControlAnalyze();
					try {
						DataAnalyze();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				L.e("no run ");
			}
		}
	};
	public testUSB( Activity mainActivity , int vid , int pid , Protagonist p ) {
		// TODO Auto-generated constructor stub
		this.mainActivity = mainActivity;
		resBuff = new StringBuilder();
		timer.schedule(task, 1000, 100);
		
		Vid = vid;
		Pid = pid;
		this.protagonist = p ;
	}


	public void connect() {
		L.e( "連線");
		if ( sUsbController == null) {
			sUsbController = new UsbController(mainActivity,
					mConnectionHandler, Vid, Pid);
			連線完成 = true;
			L.e( "連線完成");
		}
	}

	public ArrayList<ZigBeeDevice> getDeviceList() {
		 return deviceList;
	}
	
	public void getDeviceList( ArrayList<ZigBeeDevice>  list) {
		 deviceList = list;
	}
	public void offline() {
		L.e( "斷線");
		if (sUsbController != null) {
			sUsbController.stop();
			sUsbController = null;
			連線完成 = false;
			deviceList.clear();
//			命令序列.clear();
			
		}
		
	}

	public void close() {
		Log.e("tag" , "timer close");
		timer.cancel();
		timer = null;
		task.cancel();
		task = null;
		
		
		
		
	}
	private synchronized void sendData( String send ) {
		if ( send != null && !send.equals("") ) {
			Log.e("testUSB 發送命令 " , send );
			
				sUsbController.send( send + "\r");
			
		}
	}

	private void receiverData() {
		String ReStr = sUsbController.readStr();// 接收資料
		if (ReStr != null && !ReStr.equals("")) {
			resBuff.append(ReStr);
//			L.e( "resBuff = " + resBuff.toString()); 
			L.e("接收資料: " + ReStr);
		}

	}

	private void deleteBuffer(int index ,String str) {
			resBuff.delete( index ,index + str.length());
	}
	
	private void ControlAnalyze() {
		int index = 0;
		String str = resBuff.toString();
//		Log.e( " 控制命令解析 " , "'" + str + "'");
		if ( !str.trim().toString().equals( "" ) ) {
			index = resBuff.indexOf("200");
			if (index >= 0) {
				deleteBuffer(index , "200");
				L.e( "200 命令傳送成功");
			}
			
			index = resBuff.indexOf("301");
			if (index >= 0) {
				deleteBuffer(index , "301");
				L.e( "301 傳送錯誤");
			}
			index = resBuff.indexOf("306");
			if (index >= 0) {
				deleteBuffer(index , "306");
				L.e( "306 上個命令還未完成");
	
			}
			
			index = resBuff.indexOf("+DJI:");
			if (index >= 0 && resBuff.length() >= index + 16 + 5) {
				String substr = resBuff.substring(index + 5, index + 16 + 5);
				resBuff.delete(0, resBuff.length());// 清除 Buff
				L.e( "收到終端" + substr);
				createDevice(substr);
	
			}
			index = resBuff.indexOf("+CLI:");
			if (index >= 0 && resBuff.length() >= index + 16 + 5) {
				String substr = resBuff.substring(index + 5, index + 16 + 5);
				resBuff.delete(0, resBuff.length());// 清除 Buff
				L.e( "失去回應" + substr);
				deleteDavice(substr);
			}
		}
	}

	int y = 626;
	float percent = 3.65f;
	private void DataAnalyze() {
		String pattern = "[a-zA-Z][\\w]*[=][\\s-0-9]?[0-9]{2}[,]?";// P1=9
//		String pattern = "[a-zA-Z]\\w+[=][\\s-][0-9]{2}[,]";// A= 01,B=-02,
		// String text = "Dear Snoopy, How are you !.";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(resBuff);
//		String aa = "P1=XXX,P2=2255"
		int lest=0;
		while (m.find()) {
			String str = resBuff.substring(m.start(), m.end());
//			L.e( "str = " + str);
			String[] strarr = str.split("=");

			for ( ZigBeeDevice device : deviceList ) {
//				L.e( "aac 資料解析 代號='"+strarr[0]+"' 資料= " + strarr[1] + " protagonist.getDeviceId() " + protagonist.getDeviceId() );
				// 判斷 ID 是否相同 裝置狀態(開啟 停止)
				if ( device.getNumberId().equals( strarr[0] ) && device.isStatus() ) {
//					int data = Integer.parseInt(strarr[1].replace(" ", ""));
					strarr[1] = strarr[1].trim();
					
					if( device.getNumberId().equals( protagonist.getDeviceId() )) {
						Log.e( "tag" , "資料解析 ZigBeeDevice " + device.getNumberId()+" 資料= " + strarr[1].trim() + "\n " + (y + -1 * ( Integer.parseInt( strarr[1].trim() ) * percent )));
						
						protagonist.setY( y + -1 * ( Integer.parseInt( strarr[1].trim() ) * percent ) );
					}
//					}else {
//						Log.w( "tag" , "資料解析 ZigBeeDevice " + device.getNumberId()+" 資料= " + strarr[1].trim());
//					}
					
					device.setZBData( strarr[1].trim() );
					break;
				} else if ( device.getNumberId().equals( strarr[0] ) && !device.isStatus() ) {
						sendData( device.stop() );
				}
			}
			lest = m.end();
//			System.out.println(resBuff.substring(m.start(), m.end()));
		}
		
		//clear buffer data
		resBuff.delete(0, lest); 
	}

	private void createDevice(String device_id) {
		boolean ttt = true;

		// 判斷 ZB終端是否重複
		for (ZigBeeDevice device : deviceList) {
			if ( device.getDeviceId().equals( device_id ) ) {
				ttt = false;
				break;
			}
		}

		
		if (ttt) {
			String devId = number_id_str + number_id_pool;
			L.e( "建立終端"+device_id+ " 代號:"+ devId);
			ZigBeeDevice zb = new ZigBeeDevice( device_id, devId);
			deviceList.add( zb );
			// 給定ID後啟動設備
			if ( protagonist.getDeviceId().equals("") ) {
				protagonist.setDeviceId( devId );
				sendData( zb.start() );
			}
			
			number_id_pool++;//delete use id
		}
	}
	
	private void deleteDavice(String 終端編號) {
		for(int i = deviceList.size() - 1; i>=0;i--){
			if ( deviceList.get(i).getNumberId().equals(終端編號)){
				deviceList.remove(i);
			}
		}
	}
	

	public void deviceStart() {
		if (sUsbController != null) {
			for ( ZigBeeDevice device : deviceList ) {
				
				if ( !device.isStatus() )
					sendData( device.start());
					L.e( "Start All Devices"+ device.getDeviceId());
			}
		}
	}

	public void deviceStop() {
		if (sUsbController != null) {
			for ( ZigBeeDevice device : deviceList ) {
				
				if ( device.isStatus() ) {
					sendData( device.stop() );
				}
//				L.e( "Stop All Devices"+device.getDeviceId());
			}
		}
	}

	private final IUsbConnectionHandler mConnectionHandler = new IUsbConnectionHandler() {
		@Override
		public void onUsbStopped() {
			L.e("Usb stopped!");
			L.e( "Usb停止");
		}

		@Override
		public void onErrorLooperRunningAlready() {
			L.e("Looper already running!");
			L.e( "運行錯誤");
		}

		@Override
		public void onDeviceNotFound() {
			L.e( "裝置未找到");
			if (sUsbController != null) {
				sUsbController.stop();
				sUsbController = null;
			}
		}

		@Override
		public void onRead() {

		}
	};

}