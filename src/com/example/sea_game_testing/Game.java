package com.example.sea_game_testing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.gameData.Background;
import com.example.gameData.Checkpoints;
import com.example.gameData.Fish1;
import com.example.gameData.Protagonist;
import com.game.view.GameSurfaceView;

public class Game extends Activity {

	private GameSurfaceView gv = null;
	public static int DEVICE_WIDTH = 0;
	public static int DEVICE_HEIGHT = 0;
	
	private final static int GAME_START = 1;
	private final static int GAME_STOP = 2;
	private final static int GAME_END = 3;
	private static int PUSH_ID = GAME_START;
	public static long GAME_START_TIME = 0;
	public static int score = 100;
	private TestThread t = null; // 畫面 Thread
	private Protagonist p = null; // 主角
	private Background b = null; // 背景
	private Checkpoints c = null; // 怪物
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		init();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.background_3200_752);
		c = new Checkpoints();
		c.addRole(new Fish1( DEVICE_WIDTH, 30, "s_fish", this, 2));
		c.addRole(new Fish1( DEVICE_WIDTH, 100, "a_fish", this, 6));
		c.addRole(new Fish1( DEVICE_WIDTH, 120, "b_fish", this, 10));
		c.addRole(new Fish1( DEVICE_WIDTH, 150, "c_fish", this, 12));
		c.addRole(new Fish1( DEVICE_WIDTH, 180, "c_fish", this, 18));
		c.addRole(new Fish1( DEVICE_WIDTH, 300, "c_fish", this, 22));
		c.addRole(new Fish1( DEVICE_WIDTH, 300, "c_fish", this, 28));
		b = new Background(bmp);
		p = new Protagonist("章魚" , this);
		gv.init( p , b , c );
		t = new TestThread( gv );
		t.start();
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		score = s;
	}
	
	private void init() {
		getWindowSize();
//		GAME_START_TIME = System.currentTimeMillis();
		Log.e("Game init" , "time" + GAME_START_TIME);
		gv = (GameSurfaceView) findViewById(R.id.game);
	}
	public void start(View v) {
		 
		c.addRole(new Fish1( DEVICE_WIDTH, (int)( Math.random() * DEVICE_HEIGHT % DEVICE_HEIGHT ), "c_fish", this, 22));
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
		p.close();
		b.close();
		Log.e("Game" , "onDestroy");
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
					this.sleep(30);
				} catch (InterruptedException e) {
				}
				if (PUSH_ID == GAME_START) {
					synchronized (view.getHolder()) {
						view.Draw();
						GAME_START_TIME += 30;
//						Log.e("Game " , "GAME_START_TIME  add" +GAME_START_TIME );
					}
				} else if (PUSH_ID == GAME_STOP) {
					
				}
				
			}
		}
	}
}
