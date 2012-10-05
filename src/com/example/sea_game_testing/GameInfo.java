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
import com.game.view.BloodView;
import com.game.view.GameSurfaceView;

public class GameInfo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_info);
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void finish(View v ) {
		finish();
	}
}
