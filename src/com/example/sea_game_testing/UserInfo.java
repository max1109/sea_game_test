package com.example.sea_game_testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserInfo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);

	}

	public void test_Checkpoints(View v) {
		Intent i = new Intent();
    	i.setClass(this, Game.class);
    	i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    	startActivity( i );
    	finish();
	}
}
