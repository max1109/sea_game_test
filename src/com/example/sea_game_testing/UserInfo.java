package com.example.sea_game_testing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameData.Checkpoints;

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
		
		info = (TextView)findViewById(R.id.info);
		img = (ImageView)findViewById(R.id.img);
		list = (GridView)findViewById(R.id.list);
		
		str = String.format( getResources().getString(R.string.info) ,"a","d","c","b");
		info.setText(str);
		ArrayList<Checkpoints> item = new ArrayList<Checkpoints>();
		new Checkpoints();
		item.add( new Checkpoints());
		list.setAdapter( new ListData(this, item));
	}
	
	public void play(View v) {
		Intent i = new Intent();
    	i.setClass(this, Game.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	startActivity( i );
    	finish();
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
			((TextView) convertView).setText( ""+ position);
			((TextView) convertView).setTextSize(35);
			((TextView) convertView).setTextColor(Color.CYAN);
			return convertView;
		}
		
	}
}
