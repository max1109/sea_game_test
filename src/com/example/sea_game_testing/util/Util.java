package com.example.sea_game_testing.util;

import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class Util {

	public static List<Integer> CLASS_ID = new ArrayList<Integer>();
	public static int GAME_START_TIME = 0; 
	public static String user = "";
	public static String sex = "";
	public static int DEVICE_WIDTH = 0;
	public static int DEVICE_HEIGHT = 0;
	public static String PATH =  Environment.getExternalStorageDirectory().getPath();
	
	public static final int VID = 0x067b;
	public static final int PID = 0x2303;
	
	public static void AddId( int id) {
		CLASS_ID.add( id );
	}
	
	public static void closeGame() {
		for (int x = 0 ; x < CLASS_ID.size(); x++ ) {
			android.os.Process.killProcess( CLASS_ID.get( x ));
		}
	}
}
