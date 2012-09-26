package com.example.sea_game_testing;

import com.game.view.GameView;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class Game extends Activity {

	GameView gv = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		gv = (GameView )findViewById(R.id.game);
		
		gv.init(null, BitmapFactory.decodeResource(getResources(), R.drawable.background));

//		gv.post( new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true) {
//					try {
//						Thread.sleep(200);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		});
	}

}
