package com.example.sea_game_testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sea_game_testing.util.Util;

public class MainActivity extends Activity {

	EditText user = null;
	EditText password = null;
	String u = "sam";
	String pwd= "qqqq";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.AddId( android.os.Process.myPid());
        user = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.password);
    }

    public void login(View v) {
    	if ( 
    		user.getText().toString().trim().equals( u ) &&
    		password.getText().toString().trim().equals( pwd )
    		) 
    	{
    		Intent i = new Intent();
        	i.setClass(getApplicationContext(), UserInfo.class);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	i.putExtra( "user", user.getText().toString().trim() );
        	startActivity( i );
        	finish();
    	} else {
    		Toast.makeText( this, "user or pwd error!!", Toast.LENGTH_SHORT).show();
    	}
    
    	
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
