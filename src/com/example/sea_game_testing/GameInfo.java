package com.example.sea_game_testing;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.sea_game_testing.util.Util;

public class GameInfo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_info);
		Util.AddId( android.os.Process.myPid());	
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}
	
	public void goHome(View v ) {
		finish();
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.e(" onKeyDown " , "onKeyDown" + keyCode );
		
		if ( keyCode == KeyEvent.KEYCODE_BACK ) {                    
			this.finish();
			Util.closeGame();
			return true;
       }
       return super.onKeyDown(keyCode, event);
    }
}
