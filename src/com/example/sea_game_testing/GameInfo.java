package com.example.sea_game_testing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devices.Zigbee.ZigBeeDevice;
import com.example.File.DataStage;
import com.example.sea_game_testing.util.Util;
import com.game.view.GraphView2;

public class GameInfo extends Activity {

	private GestureDetector detector;
	ArrayList<ZigBeeDevice> list = null;
	ImageView img = null;
	TextView text = null;
	private static int str_num = 0;
	int stage = 0;
	int score = 0;
	GraphView2 view = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_info);
		Util.AddId(android.os.Process.myPid());
		view = (GraphView2) findViewById(R.id.graph);
		text = (TextView) findViewById(R.id.info);
		text.setTypeface( Typeface.createFromAsset(getAssets(),"fonts/FEASFBRG.ttf") );
		if (getIntent().getExtras() != null) {
			score = getIntent().getExtras().getInt("blood");
			stage = getIntent().getExtras().getInt("stage");
			list = (ArrayList<ZigBeeDevice>) getIntent().getSerializableExtra(
					"list");
//			draw();
			draw2();
		} else {
			score  = 60;
			stage = 7;
			ArrayList<ZigBeeDevice> aa = new ArrayList<ZigBeeDevice>();
			ZigBeeDevice z = new ZigBeeDevice("12323" , "aweawe");
			z.setZBData("20");
			z.setZBData("30");
			z.setZBData("20");
			z.setZBData("50");
			z.setZBData("60");
			z.setZBData("80");
			z.setZBData("90");
			z.setZBData("25");
			z.setZBData("65");
			z.setZBData("78");
			z.setZBData("54");
			z.setZBData("23");
			z.setZBData("65");
			z.setZBData("89");
			z.setZBData("76");
			z.setZBData("21");
			z.setZBData("98");
			z.setZBData("100");
			z.setZBData("20");
			z.setZBData("23");
			z.setZBData("12");
			z.setZBData("2");
			z.setZBData("2");
			z.setZBData("0");
			z.setZBData("0");
			z.setZBData("2");
			z.setZBData("20");
			z.setZBData("20");
			z.setZBData("20");
			z.setZBData("20");
			z.setZBData("20");
			z.setZBData("20");
			aa.add(z);
			list = aa;
			draw2();
		}
		detector = new GestureDetector( view );
		String str = String.format(
				getResources().getString(R.string.game_info), stage, score);

		text.setText(str);
	}
	
	@Override  
    public boolean onTouchEvent(MotionEvent event) {  
//        Log.i("Fling", "Activity onTouchEvent!");  
        return this.detector.onTouchEvent(event);  
    } 
	String data = "";
	private void draw2() {
		for (ZigBeeDevice device : list) {
			String str = new String(device.getZBData().toString());
			String tmp = str.substring(str_num);
			data = tmp;
			str_num = str.length();
			String ary[] = tmp.split(",");
			int[] data = new int[ary.length];
			for(int x = 0; x < ary.length; x++) {
				data[ x ] = Integer.parseInt( ary[x] );
			}
			view.setData( data);
		}
	}
	private void draw() {
		Canvas c = new Canvas();
		Bitmap bmp = null;
		Paint paint = new Paint();
		c.drawColor(Color.BLACK);
		// paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(1);
		paint.setColor(Color.RED);
		for (ZigBeeDevice device : list) {
			int x = 0;
			int y1 = 0;
			int y2 = 0;
			String str = new String(device.getZBData().toString());
			String tmp = str.substring(str_num);
			str_num = str.length();
			String ary[] = tmp.split(",");
			bmp = Bitmap.createBitmap(ary.length + 3, 100,
					Bitmap.Config.ARGB_4444);
			c.setBitmap(bmp);
			device.clearZBData();
			for (int index = 0; index < ary.length; index++) {

				if (index + 1 < ary.length) {
					y1 = Integer.parseInt(ary[index]);
					y2 = Integer.parseInt(ary[index + 1]);
					c.drawLine(x++, 100 - y1, x, 100 - y2, paint);
				}
			}
		}
		if (bmp != null) {
			img.setImageBitmap(bmp);
		}
	}

	private void setStageScore() {
		if ( Util.USER_DATA.stage != null ) 
		{
			
			boolean new_stage = true;
			for (int x = 0; x < Util.USER_DATA.stage.size(); x++) {
				if ( Util.USER_DATA.stage.get( x ).id.equals( "" + stage)) {
					Util.USER_DATA.stage.get( x ).score = ""+score;
					new_stage = false;
					Log.e("odd ", stage + " " + score);
				}
			}
			if ( new_stage ) {
				Log.e("new ", stage + " " + score + " " + Util.USER_DATA.stage.size());
				Util.USER_DATA.stage.add( new DataStage( "" + stage , "" + score));
				Log.e("new ", stage + " " + score + " " + Util.USER_DATA.stage.size());
			}
		}
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		setStageScore();
	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	public void goHome(View v) {
		gameOver();

	}

	private int nextStage() {
		int x = 0;
		if ( 
				UserInfo.item != null &&
				UserInfo.item.size() > (stage - 1) 
			)
		{
			x =  stage + 1;
		}
		return x;
	}
	public void nextPlay(View v) {
		Intent i = new Intent();
		i.setClass(this, Game.class);
		int x = nextStage(); 
		i.putExtra("stage", x );
		Log.e("game info ", "x = " + x );
		startActivity(i);
		finish();
	}

	private void gameOver() {
		Intent i = new Intent(this, UserInfo.class);

		i.putExtra("stage", stage);
		i.putExtra("score", score);
		// setResult( RESULT_OK , i);
		startActivity(i);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e(" onKeyDown ", "onKeyDown" + keyCode);

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			Util.closeGame();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
