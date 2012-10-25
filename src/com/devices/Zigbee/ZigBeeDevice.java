package com.devices.Zigbee;

import java.io.Serializable;

public class ZigBeeDevice implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number_id = "";
	private String device_id = "";
	private int data = 0;
	// ZB 設備送出的資料
	private StringBuffer ZB_data = new StringBuffer();
	private boolean status = false;
//	private 環狀資料 環狀資料 = new 環狀資料(15);// bug??

	public ZigBeeDevice(String device_id, String number_id) {
		this.number_id = number_id;
		this.device_id = device_id;
	}

//	public void setNumberId(String number_id) {
//		this.number_id = number_id;
//	}

	public int getData() {
		return data;
	}
	
	public void setData( int data) {
		this.data = data;
	}
	
	public 	String getNumberId() {
		return this.number_id;
	}

	public String getDeviceId() {
		return this.device_id;
	}
	
	public StringBuffer getZBData() {
		return ZB_data;
	}
	public void clearZBData() {
		ZB_data.delete(0 , ZB_data.length() - 1);
	}
	
	public void setZBData( String data) {
		ZB_data.append( data + ",");
	}
	
	public String start() {
//		String cmd = String.format("AT+TXT D=%S P=RSV R=%S", 終端編號, 終端代號);
		status = true;
		return "AT+TXT D=" + device_id + " P=RSV R=" + number_id;
	}

	public String stop() {
//		String cmd = String.format("AT+TXT D=%S P=CRS", 終端編號);
//		return cmd;
		status = false;
//		return String.format("AT+TXT D=%S P=CRS", device_id);
		return "AT+TXT D=" + device_id + " P=CRS"; 
	}

	public boolean isStatus() {
		return status;
	}


}
