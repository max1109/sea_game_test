package com.example.sea_game_testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.android_usb_test.testUSB;
import com.example.gameData.Background;
import com.example.gameData.Fish;
import com.example.gameData.Protagonist;
import com.example.gameData.Stage;
import com.example.gameData.StageList;
import com.example.sea_game_testing.util.DeviceUtil;
import com.example.sea_game_testing.util.Util;
import com.game.view.BloodView;
import com.game.view.GameSurfaceView;

public class Game extends Activity {

	private GameSurfaceView gv = null;

	private static final int GAME_START = 1;
	private static final int GAME_STOP = 2;
	private static final int GAME_END = 3;
	private static final int VID = 0x067b;
	private static final int PID = 0x2303;

	private static int PUSH_ID = GAME_START;
	// public static long GAME_START_TIME = 0;

	private int stage = 0;
	public static int blood = 100;
	private TestThread t = null; // 畫面 Thread
	private BloodThread bt = null; // 血量 Thread
	private Protagonist p = null; // 主角
	private Background b = null; // 背景
	private Stage c = null; // 怪物
	private BloodView bloodView = null;
	// private testUSB usb = null;
	private StageList stage_list = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		if (getIntent().getExtras() != null) {
			stage = getIntent().getExtras().getInt("stage");

		}
		Log.e("tag", "onCreate" + stage);

	}

	private void USBInit() {
		if (DeviceUtil.USB == null) {
			DeviceUtil.USB = new testUSB(this, VID, PID, p);
			DeviceUtil.USB.connect();
		}

	}

	private void init() {
		blood = 100;
		Util.AddId(android.os.Process.myPid());
		getWindowSize();
		// GAME_START_TIME = System.currentTimeMillis();
		Log.e("Game init", "time" + Util.GAME_START_TIME);
		gv = (GameSurfaceView) findViewById(R.id.game);
		bloodView = (BloodView) findViewById(R.id.blood);

	}

	public void start(View v) {

		c.addRole(new Fish(Util.DEVICE_WIDTH, (int) (Math.random()
				* Util.DEVICE_HEIGHT % Util.DEVICE_HEIGHT), "c_fish", this, 22,
				8, 80));
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
		Util.DEVICE_WIDTH = dm.widthPixels;
		Util.DEVICE_HEIGHT = dm.heightPixels;
	}

	@Override
	protected void onStart() {
		super.onStart();
		init();
		stage_list = new StageList(this, stage);
		c = stage_list.getStage();
		b = stage_list.getBackground();
		Log.e("onStart", "onCreat " );
		p = new Protagonist("章魚", this);
		gv.init(p, b, c, bloodView);
		t = new TestThread(gv );
		bt = new BloodThread(bloodView , c);
		t.start();
		bt.start();

		USBInit();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("Game", "onPause" + blood);
		PUSH_ID = GAME_STOP;
		// gv = null;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("Game", "onResume");
		PUSH_ID = GAME_START;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e("Game", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		close();
		// if (t != null) {
		// t = null;
		// }
		// if (bt != null) {
		// bt = null;
		// }
		// gv = null;
		// p.close();
		// b.close();
		// c.close();
		// Util.GAME_START_TIME = 0;
		// usb.deviceStop();
		// usb.offline();
		// usb.close();
		// android.os.Process.killProcess( android.os.Process.myPid() );
		Log.e("Game", "onDestroy");

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		Log.e(" onKeyDown ", "onKeyDown" + keyCode);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (t != null) {
				t = null;
			}
			if (bt != null) {
				bt = null;
			}

			p.close();
			b.close();
			DeviceUtil.USB.offline();
			DeviceUtil.USB.close();
			finish();
			Util.closeGame();

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void gameOver() {
		PUSH_ID = GAME_STOP;
		Intent i = new Intent(this, GameInfo.class);
		// i.putExtra("list", usb.getDeviceList().);
		Bundle b = new Bundle();
		b.putSerializable("list", DeviceUtil.USB.getDeviceList());
		i.putExtras(b);
		i.putExtra("blood", blood);
		i.putExtra("stage", stage);
		startActivity(i);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		Log.e("Game", "gameOver");
		finish();

	}

	private void close() {
		p.close();
		p = null;
		b.close();
		b = null;
		c.close();
		c = null;
		stage_list = null;
		Util.GAME_START_TIME = 0;
		if (t != null) {
			t.interrupt();
			t = null;
		}
		if (bt != null) {
			bt.interrupt();
			bt = null;
		}

	}

	class BloodThread extends Thread {
		BloodView b = null;
		boolean loop = true;
		Stage stage = null;
		BloodThread(BloodView b , Stage s) {
			this.b = b;
			this.stage = s ;
		}

		public void run() {
			while (loop) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (PUSH_ID == GAME_START) {
					blood -= 2;
					// Log.e("tag" , "score = " + score);
					if (blood <= 0) {
						gameOver();
					}
					b.setBlood(blood);
					try {
					
						if ( this.stage.isAllRoleDead() ) {
							Log.e("tag", " isAllRoleDead blood = " + blood);
							gameOver();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (PUSH_ID == GAME_STOP) {

				} else if (PUSH_ID == c.getEndTime()) {

				}
			}
		}
	}

	class TestThread extends Thread {
		GameSurfaceView view = null;
		boolean loop = true;
		
		TestThread(GameSurfaceView view  ) {
			this.view = view;
			
		}

		public void run() {
			while (loop) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				if (PUSH_ID == GAME_START) {
					synchronized (view.getHolder()) {
						view.Draw();
						Util.GAME_START_TIME += 30;
						// Log.e("Game " , "GAME_START_TIME  add"
						// +GAME_START_TIME );
					}
					
				} else if (PUSH_ID == GAME_STOP) {

				} 

			}
		}
	}
}
