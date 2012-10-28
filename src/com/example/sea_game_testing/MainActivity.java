package com.example.sea_game_testing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.File.Data;
import com.example.File.UserInfoFile;
import com.example.sea_game_testing.util.Util;

public class MainActivity extends Activity {

	EditText user = null;
	EditText password = null;
	
	UserInfoFile a;
	List<String> user_name_list = null;
	UserInfoFile uf = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Util.AddId(android.os.Process.myPid());
		user = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.password);
		 
		loadFileUserName();
	}

	private void loadFileUserName() {
		user_name_list = new ArrayList<String>();
		File f = new File(Util.PATH);
		File[] ff = f.listFiles();
		if (ff != null)
			for (int x = 0; x < ff.length; x++) {
				Log.e("tag ", ff[x].getName());
				if (ff[x].isDirectory()) {
					user_name_list.add(ff[x].getName());

//					File[] user = ff[x].listFiles();
//					for (int i = 0; i < user.length; i++) {
//						if (user[i].isFile()) {
//							Log.e("tag user", user[i].getName());
//							if (ff[x].getName().equals(
//									user[i].getName()
//											.substring(
//													0,
//													ff[x].getName().toString()
//															.length()))) {
//
//								uf = new UserInfoFile(user[i]);
//								uf.getData();
//								// Log.e("tag", uf.getData());
//							}
//						}
//					}
				}
			}
	}

	
	public Data loadFileUserInfo( String str ) {
		String pa = Util.PATH + "/" + str + Util.USER_INFO_PATH;
		Log.e("loadFileUserInfo", pa);
		// this is user info.xml file
		File f = new File( pa );
		if ( f.isFile()) {
			Log.e("loadFileUserInfo", f.getName());
			
			uf = new UserInfoFile( f );
			return uf.getData();
		} else {
			return null;
		}
	}
	
	public void login(View v) {
		for (int x = 0; x < user_name_list.size(); x++) {
			Util.USER_DATA = loadFileUserInfo( user_name_list.get( x ));
			
			if (
					user.getText().toString().equals( user_name_list.get( x ).toString()) &&
					Util.USER_DATA != null && 
					password.getText().toString().equals( Util.USER_DATA.pwd.toString() )
				) 
			{
				
				Intent i = new Intent();
				i.setClass(getApplicationContext(), UserInfo.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.putExtra("user", user.getText().toString().trim());
				startActivity(i);
				finish();
				
			} else {
				Toast.makeText(this, "User or Password  error!!", Toast.LENGTH_SHORT ).show();
			}

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 監聽返回鍵
		if (keyCode == KeyEvent.KEYCODE_BACK) {
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
