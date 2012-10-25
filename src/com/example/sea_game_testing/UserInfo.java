package com.example.sea_game_testing;

import java.util.ArrayList;

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

import com.example.android_usb_test.testUSB;
import com.example.gameData.Stage;
import com.example.sea_game_testing.util.DeviceUtil;
import com.example.sea_game_testing.util.Util;

public class UserInfo extends Activity {
	TextView info = null;
	ImageView img = null;
	GridView list = null;
	Button device = null;
	private int play_game_num = 3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		init();
		USBInit();
		if (getIntent().getExtras() != null) {
			if (
					getIntent().getExtras().getString("user") != null && 
					!getIntent().getExtras().getString("user").equals("")) 
			{
				Util.user = getIntent().getExtras().getString("user");
				
			} else if (
					getIntent().getExtras().getInt("stage") >= 0 &&
					getIntent().getExtras().getInt("score") >= 0
					) 
			{
				play_game_num++;
			}

		}

	}
	
	public void checkZB( View v ) {
		Log.e("tag" , "" + DeviceUtil.USB.getDeviceList().size() );
	}
	
	private void init() {
		Util.AddId(android.os.Process.myPid());
		info = (TextView) findViewById(R.id.info);
		img = (ImageView) findViewById(R.id.img);
		list = (GridView) findViewById(R.id.list);
		device = (Button) findViewById(R.id.device);
		String str = String.format(getResources().getString(R.string.info),
				Util.user, Util.sex, "20", "0");
		info.setText(str);
		ArrayList<DataItem> item = new ArrayList<DataItem>();

		item.add(new DataItem(0 ,20));
		item.add(new DataItem(1,30));
		item.add(new DataItem(2,80));
		item.add(new DataItem(3,0));
		item.add(new DataItem(4,0));
		item.add(new DataItem(5,0));

		list.setAdapter(new ListData(this, item));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// playGame();
				Intent i = new Intent();
				i.setClass(UserInfo.this, Game.class);
				i.putExtra("stage", arg2);

				startActivityForResult(i, ReDataId);
				finish();
			}
		});
	}

	private final int ReDataId = 1;

	
	
	private void USBInit() {
		if (DeviceUtil.USB == null) {
			DeviceUtil.USB = new testUSB(this, Util.VID, Util.PID);
			DeviceUtil.USB.connect();
		}
		
	}
	private void playGame() {
		Intent i = new Intent();
		i.setClass(this, Game.class);
		i.putExtra("stage", play_game_num + 1);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (ReDataId == requestCode) {

			if (resultCode == RESULT_OK) {
				Log.e("UserInfo", "onActivityResult");
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	class DataItem {
		int stage = 0;
		int score = 0;
		DataItem( int stage , int score) {
			this.score = score;
			this.stage = stage;
		}
		
	}
	class ListData extends BaseAdapter {
		ArrayList<DataItem> item = null;
//		TextView text = null;
		Context c = null;

		ListData(Context c, ArrayList<DataItem> item) {
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

			if (item.get( position ).score < 0)
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
