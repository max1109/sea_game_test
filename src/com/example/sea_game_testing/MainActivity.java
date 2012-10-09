package com.example.sea_game_testing;

import com.example.sea_game_testing.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.AddId( android.os.Process.myPid());
    }

    public void login(View v) {
    	Intent i = new Intent();
    	i.setClass(getApplicationContext(), UserInfo.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity( i );
    	finish();
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
           if (keyCode == KeyEvent.KEYCODE_BACK){                    
                   this.finish();
                   Util.closeGame();
                   return true;
           }
           return super.onKeyDown(keyCode, event);
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
    
    public void finish(View v) {
    	finish();
    }
}
