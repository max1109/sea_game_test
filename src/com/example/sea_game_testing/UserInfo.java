package com.example.sea_game_testing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.File.DataStage;
import com.example.android_usb_test.testUSB;
import com.example.gameData.Stage;
import com.example.sea_game_testing.util.DeviceUtil;
import com.example.sea_game_testing.util.Util;

public class UserInfo extends Activity {
	TextView info = null;
	ImageView img = null;
	GridView list = null;
	Button device = null;
	public static ArrayList<DataStage> item = null; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		init();
		USBInit();
//		if (getIntent().getExtras() != null) {
//			if (
//					getIntent().getExtras().getString("user") != null && 
//					!getIntent().getExtras().getString("user").equals("")) 
//			{
//				Util.user = getIntent().getExtras().getString("user");
//				
//			} 
//			
//		}
		checkDeviceStatus();
	}
	
	private void checkDeviceStatus() {
		if ( DeviceUtil.USB.getDeviceList() != null && DeviceUtil.USB.getDeviceList().size() > 0 ) {
//			String str = String.format(
//					getResources().getString(R.string.device_number).toString() , 
//					""+DeviceUtil.USB.getDeviceList().size()
//					);
			Toast.makeText( this,  "finish!", Toast.LENGTH_SHORT).show();
//			DeviceUtil.USB.deviceStart();
			device.setBackgroundResource(R.drawable.connection_ok);
		}else {
			device.setBackgroundResource(R.drawable.connection_no);
			Toast.makeText( this,  "No Devices" + (DeviceUtil.USB.getDeviceList() != null), Toast.LENGTH_SHORT).show();
		}
	}
	
	public void checkZB( View v ) {
		checkDeviceStatus();
	}
	
	private void init() {
		Util.AddId(android.os.Process.myPid());
		info = (TextView) findViewById(R.id.info);
		img = (ImageView) findViewById(R.id.img);
		list = (GridView) findViewById(R.id.list);
		device = (Button) findViewById(R.id.device);
		if ( Util.USER_DATA != null) {
			int h = 0;
			for (int x = 0 ; x < Util.USER_DATA.stage.size();x++) {
				if ( Integer.parseInt(Util.USER_DATA.stage.get( x ).score) > 0 ) {
					h++;
				}
			}
			
			String str = String.format(getResources().getString(R.string.info),
					Util.USER_DATA.user, Util.USER_DATA.sex, "20", h );
			info.setText(str);
		} else {
			info.setText("loading...");
		}
		if ( Util.USER_DATA.stage != null) {
			for (int x= Util.USER_DATA.stage.size() ; x < 6 ;x++) {
				Util.USER_DATA.stage.add( new DataStage("" + x ,"0") );
			}
			
		}
		
		item = new ArrayList<DataStage>();
		if ( Util.USER_DATA.stage != null) {
			List<DataStage> tmp = Util.USER_DATA.stage;
			for (int x= 0; x < tmp.size();x++) {
				item.add( tmp.get(x));
			}
		
		}
		if ( Util.USER_DATA.stage_new != null) {
			List<DataStage> tmp = Util.USER_DATA.stage_new;
			for (int x= 0; x < tmp.size();x++) {
				item.add( tmp.get(x));
				Log.e( "x" , "" + x );
			}
		
		}
		
		if ( item.size() < 6 ) {
			for (int x= item.size() ; x < 6 ;x++) {
				Log.e( "x" , "" + x );
				if ( x == 0)
					item.add(new DataStage("0" ,"27"));
				else if ( x == 1)
					item.add(new DataStage("1","25"));
				else if ( x == 2)
					item.add(new DataStage("2","86"));
				else if ( x == 3)
					item.add(new DataStage("3","0"));
				else if ( x == 4)
					item.add(new DataStage("4","0"));
				else if ( x == 5)
					item.add(new DataStage("5","0"));
			}
				
		}
		list.setAdapter(new ListData(this, item));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// playGame();
				Intent i = new Intent();
				i.setClass(UserInfo.this, Game.class);
				i.putExtra("stage", arg2);

				startActivity( i );
				finish();
			}
		});
	}

	private void USBInit() {
		if (DeviceUtil.USB == null) {
			DeviceUtil.USB = new testUSB(this, Util.VID, Util.PID);
			DeviceUtil.USB.connect();
		}
		
	}
	private void playGame() {
		Intent i = new Intent();
		i.setClass(this, Game.class);
		
		// touch play when check a stage lose		
		int stage = 0;
		if ( item != null ) {
			for ( int x = stage; x < item.size(); x++ ) {
				int score = Integer.parseInt( item.get(x).score);
				if ( score > 60) {
					int s = Integer.parseInt( item.get(x).id);
					stage = s;
				}
			}
		}
		
		i.putExtra("stage", stage);
		// i.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
		// //如果這Activity是開啟的就不再重複開啟
		i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(i);
		finish();
	}

	public void play(View v) {
		playGame();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			Util.closeGame();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}



	class ListData extends BaseAdapter {
		ArrayList<DataStage> item = null;
//		TextView text = null;
		Context c = null;

		ListData(Context c, ArrayList<DataStage> item) {
			this.c = c;
			this.item = item;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.item.size();
		}

		@Override
		public Stage getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new TextView(c);
			}
			int score = Integer.parseInt( item.get( position ).score);
			if (score <= 0)
				((TextView) convertView).setBackgroundResource(R.drawable.clam_2);
			else {
				((TextView) convertView).setBackgroundResource(R.drawable.clam_1);
//				((ImageView) convertView).setImageResource(R.drawable.pearl_1);
			}

			 ((TextView) convertView).setGravity( Gravity.CENTER);
			// ((TextView) convertView).setLayoutParams( new
			// AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT,
			// LayoutParams.WRAP_CONTENT));
			// ((TextView) convertView).setWidth(80);

			 ((TextView) convertView).setText( ""+ item.get(position).score);
			 ((TextView) convertView).setTextSize(35);
			 ((TextView) convertView).setTextColor(Color.CYAN);
			return convertView;
		}

	}
}
