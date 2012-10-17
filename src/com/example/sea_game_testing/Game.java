package com.example.sea_game_testing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.gameData.Background;
import com.example.gameData.BackgroundObject;
import com.example.gameData.Checkpoints;
import com.example.gameData.Fish;
import com.example.gameData.Protagonist;
import com.example.gameData.Role;
import com.example.sea_game_testing.util.Util;
import com.game.view.BloodView;
import com.game.view.GameSurfaceView;


public class Game extends Activity {

	private GameSurfaceView gv = null;
	public static int DEVICE_WIDTH = 0;
	public static int DEVICE_HEIGHT = 0;
	
	private final static int GAME_START = 1;
	private final static int GAME_STOP = 2;
	private final static int GAME_END = 3;
	private static int PUSH_ID = GAME_START;
//	public static long GAME_START_TIME = 0;
	public static int blood = 100;
	private TestThread t = null; // 畫面 Thread
	private BloodThread bt = null; // 血量 Thread
	private Protagonist p = null; // 主角
	private Background b = null; // 背景
	private Checkpoints c = null; // 怪物
	private BloodView bloodView = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		init();
		// test tmp data 
		c = new Checkpoints();
		c.addRole(new Fish( DEVICE_WIDTH, 30, "s_fish", this, 2 ,20, 20));
		c.addRole(new Fish( DEVICE_WIDTH, 100, "a_fish", this, 6, 5 , 8));
		c.addRole(new Fish( DEVICE_WIDTH, 120, "b_fish", this, 10, 4 ,1));
		c.addRole(new Fish( DEVICE_WIDTH, 150, "c_fish", this, 12, 70, 2));
		c.addRole(new Fish( DEVICE_WIDTH, 180, "c_fish", this, 18,70, 2));
		c.addRole(new Fish( DEVICE_WIDTH, 300, "c_fish", this, 22,70, 2));
		c.addRole(new Fish( DEVICE_WIDTH, 300, "c_fish", this, 28,70, 2));
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.background_3200_752);
		int bgf[] = {R.drawable.background_fish_2};
		List<Role> item = new ArrayList<Role>();
		item.add( new BackgroundObject( DEVICE_WIDTH, 300, "fish bg", this, 5, bgf));
		//---------
		b = new Background(bmp , item);
		p = new Protagonist("章魚" , this);
		gv.init( p , b , c , bloodView );
		t = new TestThread( gv );
		bt = new BloodThread( bloodView );
		t.start();
		bt.start();
	}
	
	private void init() {
		Util.AddId( android.os.Process.myPid());
		getWindowSize();
//		GAME_START_TIME = System.currentTimeMillis();
		Log.e("Game init" , "time" + Util.GAME_START_TIME);
		gv = (GameSurfaceView) findViewById(R.id.game);
		bloodView = (BloodView) findViewById(R.id.blood);
	}

	public void start(View v) {
		 
		c.addRole(new Fish( DEVICE_WIDTH, (int)( Math.random() * DEVICE_HEIGHT % DEVICE_HEIGHT ), "c_fish", this, 22, 8 ,80 ));
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
		Log.e("Game" , "onStart");
	}
	@Override
	public void onPause(){
		super.onPause();
		Log.e("Game" , "onPause");
		PUSH_ID = GAME_STOP;
//	    gv = null;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.e("Game" , "onResume");
		PUSH_ID = GAME_START;
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e("Game" , "onRestart");
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
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		Log.e(" onKeyLongPress " , "onKeyDown" + keyCode );
		return super.onKeyLongPress(keyCode, event);
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		Log.e(" onKeyDown " , "onKeyDown" + keyCode );
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (t != null) {
	       			t = null;
	       	}
	       	p.close();
	       	b.close();
	       	Util.closeGame();
	       	return true;
		} 
        return super.onKeyDown(keyCode, event);
    }
	
	class BloodThread extends Thread {
		BloodView b = null;
		boolean loop = true;
		BloodThread ( BloodView b ) {
			this.b = b; 
		}
		public void run() {
			while (loop) {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if ( PUSH_ID == GAME_START) {
					blood -= 2;
//					Log.e("tag" , "score = " + score);
					if (blood <= 0) {
						PUSH_ID = GAME_STOP;
						Intent i = new Intent(getApplicationContext() , GameInfo.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						overridePendingTransition( R.anim.zoom_enter, R.anim.zoom_exit);
						finish();
					}
					b.setBlood( blood );
				} else if ( PUSH_ID == GAME_STOP) {
					
				}
			}
		}
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
						Util.GAME_START_TIME += 30;
//						Log.e("Game " , "GAME_START_TIME  add" +GAME_START_TIME );
					}
				} else if (PUSH_ID == GAME_STOP) {
					
				}
				
			}
		}
	}
}
