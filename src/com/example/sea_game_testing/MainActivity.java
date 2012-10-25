package com.example.sea_game_testing;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.File.UserInfoFile;
import com.example.sea_game_testing.util.Util;

public class MainActivity extends Activity {

	EditText user = null;
	EditText password = null;
	String u = "sam";
	String pwd= "qqqq";
	UserInfoFile a;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.AddId( android.os.Process.myPid());
        user = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.password);
//        loadFile();
    }

    String game = "/game/";
    private void loadFile() {
    	File f = new File( Environment.getExternalStorageDirectory() + game, "aaa");
//    	Log.e("loadFile" , f.getPath() + " " + Environment.getExternalStorageDirectory().getPath());
    	File[] ff = f.listFiles();
    	for (int x = 0; x < ff.length; x++) {
    		Log.e("tag" , ff[x].getName());
    	}
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
    		// 監聽返回鍵
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
