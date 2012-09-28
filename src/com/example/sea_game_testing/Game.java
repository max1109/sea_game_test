package com.example.sea_game_testing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.gameData.Background;
import com.game.view.GameSurfaceView;

public class Game extends Activity {

	private GameSurfaceView gv = null;
	private final static int GAME_START = 1;
	public static int DEVICE_WIDTH = 0;
	public static int DEVICE_HEIGHT = 0;
	private static int PUSH_ID = 0;
	TestThread t = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		getWindowSize();
		gv = (GameSurfaceView) findViewById(R.id.game);
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.background_3200_752);
		gv.init(null, new Background(bmp));
	}
	
	public void start(View v) {
		t = new TestThread( gv);
		t.start();
	}

	public void stop(View v) {
		if (PUSH_ID == GAME_START)
			PUSH_ID = 0;
		else {
			PUSH_ID = GAME_START;
		}
	}

	private void getWindowSize() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		DEVICE_WIDTH = dm.widthPixels;
		DEVICE_HEIGHT = dm.heightPixels;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (t != null) {
			t = null;
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
	    gv = null;
	}
	
	

	class TestThread extends Thread {
		GameSurfaceView view = null;
		boolean loop = true;

		TestThread(GameSurfaceView view) {
			this.view = view;
		}

		public void run() {
			while (loop) {
				try {
					this.sleep(20);
				} catch (InterruptedException e) {
				}

				synchronized (view.getHolder()) {
					view.Draw();
				}
			}
		}
	}
}
