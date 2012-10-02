package com.example.gameData;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.util.Log;

import com.example.sea_game_testing.Game;

public class Checkpoints {
	List<Role> role_list = new ArrayList<Role>();
	
	public Checkpoints() {
	}
	
	public void draw( Canvas canvas ) {
		
		for ( int index = 0 ; index < role_list.size(); index++) {
			Role r = getRole( index );
			if (r != null) {
				r.draw(canvas);
			}
		}
		
	}
	
	public void addRole(Role r){
		role_list.add( r );
	}
	
	public int getRoleListSize() {
		return role_list.size();
	}
	
	
	public void removeRole(int i){
		role_list.remove( i );
	}
	
	public Role getRole( int index ) {
		Role  r = role_list.get( index );
//		if ( !r.getDead() && getNowTime() > r.getStartTime() * 1000 ) {
		if ( !r.getDead() && Game.GAME_START_TIME > r.getStartTime() * 1000 ) {
			Log.e("checkpoints", "get Role" + index );
			return role_list.get( index );
		}
		return null;
	}
	
	private int getNowTime() {
		Log.e("checkpoints", "getNowTime"  + (System.currentTimeMillis() - Game.GAME_START_TIME));
		return (int)(System.currentTimeMillis() - Game.GAME_START_TIME );
	}

}
