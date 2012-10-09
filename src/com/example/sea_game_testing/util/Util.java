package com.example.sea_game_testing.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<Integer> CLASS_ID = new ArrayList<Integer>();
	public static void AddId( int id) {
		CLASS_ID.add( id );
	}
	
	public static void closeGame() {
		for (int x = 0 ; x < CLASS_ID.size(); x++ ) {
			android.os.Process.killProcess( CLASS_ID.get( x ));
		}
//        System.exit(0);
	}
}
