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

import com.game.view.GameSurfaceView;

public class Game extends Activity {

	GameSurfaceView gv = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		getWindowSize();
		gv = (GameSurfaceView) findViewById(R.id.game);
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.background_3200_752);
		// Log.w("onCreate" , bmp.getWidth() + " " + bmp.getHeight());
		// gv.setBackgroundX(2590);
		gv.init(null, bmp);
		// mRedrawHandler = new RefesHandler();
		// mRedrawHandler.sendEmptyMessage(what)
		// PUSH_ID = GAME_START;
		// updateUI();
	}

	public void updateUI() {
		Log.i("tag", "updateUI");
		gv.setBackgroundX(gv.getBackgroundX() + 20);
		// gv.Refresh();
		if (mRedrawHandler != null)
			mRedrawHandler.sleep(120);
	}

	class RefesHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case GAME_START:
				Game.this.updateUI();
				Log.i("tag", "RUN" + msg);

				break;
			}

			// Log.i("tag", "handleMessage" + msg.what);

		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			if (!isFinishing())
				sendMessageDelayed(obtainMessage(PUSH_ID), delayMillis);
		}
	}

	private RefesHandler mRedrawHandler = null;
	private final static int GAME_START = 1;
	public static int DEVICE_WIDTH = 0;
	public static int DEVICE_HEIGHT = 0;
	private static int PUSH_ID = 0;
	TestThread t = null;
	public void start(View v) {
		// gv.setBackgroundX( gv.getBackgroundX() + 10);
		t = new TestThread( gv);
		t.start();
		// gv.Refresh();
	}

	public void stop(View v) {
		if (PUSH_ID == GAME_START)
			PUSH_ID = 0;
		else {
			PUSH_ID = GAME_START;
			updateUI();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mRedrawHandler != null) {
			// mRedrawHandler.removeCallbacks( r);
			mRedrawHandler = null;
		}
		if (t != null) {
//			t.stop();
			t = null;
		}
	}
	@Override
	public void onPause(){
		super.onPause();
	    gv = null;
	}
	private void getWindowSize() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		DEVICE_WIDTH = dm.widthPixels;
		DEVICE_HEIGHT = dm.heightPixels;
		// Log.w("device" , DEVICE_WIDTH +" " + DEVICE_HEIGHT );
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
