package com.example.sea_game_testing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameData.Checkpoints;
import com.example.sea_game_testing.util.Util;

public class UserInfo extends Activity {
	TextView info = null;
	ImageView img = null;
	GridView list = null;
	String str = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		init();
		
	}

	private void init() {
		Util.AddId( android.os.Process.myPid());
		info = (TextView)findViewById(R.id.info);
		img = (ImageView)findViewById(R.id.img);
		list = (GridView)findViewById(R.id.list);
		
		str = String.format( getResources().getString(R.string.info) ,"Samuel","man","20","0");
		info.setText(str);
		ArrayList<Checkpoints> item = new ArrayList<Checkpoints>();
		
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		item.add( new Checkpoints());
		
		list.setAdapter( new ListData(this, item));
		list.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void play(View v) {
		Intent i = new Intent();
    	i.setClass(getApplicationContext(), Game.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity( i );
    	finish();
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
           if (keyCode == KeyEvent.KEYCODE_BACK){                    
                   this.finish();
                   Util.closeGame();
                   return true;
           }
           return super.onKeyDown(keyCode, event);
    }
	
	class ListData extends BaseAdapter {
		ArrayList<Checkpoints> item = null;
		TextView text = null;
		Context c = null;
		ListData( Context c, ArrayList<Checkpoints> item ) {
			this.c = c;
			this.item = item;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.item.size();
		}

		@Override
		public Checkpoints getItem(int arg0) {
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
			((TextView) convertView).setGravity( Gravity.CENTER);
//			((TextView) convertView).setLayoutParams( new AbsListView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//			((TextView) convertView).setWidth(80);
			
			((TextView) convertView).setText( ""+ position);
			((TextView) convertView).setTextSize(35);
			((TextView) convertView).setTextColor(Color.CYAN);
			return convertView;
		}
		
	}
}
