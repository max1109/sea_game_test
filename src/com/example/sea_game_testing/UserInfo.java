package com.example.sea_game_testing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameData.Stage;
import com.example.sea_game_testing.util.Util;

public class UserInfo extends Activity {
	TextView info = null;
	ImageView img = null;
	GridView list = null;

	private int play_game_num = 3;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		init();

		if (
				getIntent().getExtras() != null && 
				!getIntent().getExtras().getString("user").equals("")) {
			Util.user = getIntent().getExtras().getString("user");

		}

	}

	private void init() {
		Util.AddId(android.os.Process.myPid());
		info = (TextView) findViewById(R.id.info);
		img = (ImageView) findViewById(R.id.img);
		list = (GridView) findViewById(R.id.list);

		String str = String.format(getResources().getString(R.string.info),
				Util.user, Util.sex, "20", "0");
		info.setText(str);
		ArrayList<Stage> item = new ArrayList<Stage>();

		item.add(new Stage());
		item.add(new Stage());
		item.add(new Stage());
		item.add(new Stage());
		item.add(new Stage());
		item.add(new Stage());

		list.setAdapter(new ListData(this, item));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				playGame();
				Intent i = new Intent();
				i.setClass(UserInfo.this, Game.class);
				i.putExtra("stage", arg2);
				
				startActivityForResult( i , ReDataId );
			}
		});
	}

	private final int  ReDataId = 1;
	private void playGame() {
		Intent i = new Intent();
		i.setClass(this, Game.class);
		i.putExtra("stage", play_game_num + 1 );
		i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );

		startActivityForResult( i , ReDataId );
//		finish();
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
		ArrayList<Stage> item = null;
		TextView text = null;
		Context c = null;

		ListData(Context c, ArrayList<Stage> item) {
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
				convertView = new ImageView(c);
			}

			if (position > play_game_num)
				((ImageView) convertView).setBackgroundResource(R.drawable.clam_2);
			else {
				((ImageView) convertView).setBackgroundResource(R.drawable.clam_1);
				((ImageView) convertView).setImageResource(R.drawable.pearl_1);
			}

			// ((ImageView) convertView).setGravity( Gravity.CENTER);
			// ((TextView) convertView).setLayoutParams( new
			// AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT,
			// LayoutParams.WRAP_CONTENT));
			// ((TextView) convertView).setWidth(80);

			// ((TextView) convertView).setText( ""+ position);
			// ((TextView) convertView).setTextSize(35);
			// ((TextView) convertView).setTextColor(Color.CYAN);
			return convertView;
		}

	}
}
