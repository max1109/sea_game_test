package com.example.sea_game_testing;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devices.Zigbee.ZigBeeDevice;
import com.example.sea_game_testing.util.Util;

public class GameInfo extends Activity {

	ArrayList<ZigBeeDevice> list = null;
	ImageView img = null;
	TextView text = null;
	private static int str_num = 0;
	int stage = 0;
	int score = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_info);
		Util.AddId(android.os.Process.myPid());
		img = (ImageView) findViewById(R.id.graph);
		text = (TextView) findViewById(R.id.info);

		if (getIntent().getExtras() != null) {
			score = getIntent().getExtras().getInt("blood");
			stage = getIntent().getExtras().getInt("stage");
			list = (ArrayList<ZigBeeDevice>) getIntent().getSerializableExtra(
					"list");
			draw();
		}
		String str = String.format(
				getResources().getString(R.string.game_info), stage, score);

		text.setText(str);
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
		if (UserInfo.item != null && UserInfo.item.size() > stage
				&& UserInfo.item.get(stage).score < score) {
			UserInfo.item.get(stage).score = score;
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
