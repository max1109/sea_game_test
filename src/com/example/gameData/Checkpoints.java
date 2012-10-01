package com.example.gameData;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.example.sea_game_testing.Game;

public class Checkpoints {
	List<Role> role_list = new ArrayList<Role>();
	
	public Checkpoints() {
//		role_list.add( new Fish1(20 , 60 , "fish_1" , null, 8));
	}
	
	public void DrawRole( Canvas canvas ) {
		
		for ( int index = 0 ; index < role_list.size(); index++) {
			Role r = getRole( index);
			if (r != null) {
				r.draw(canvas);
			}
		}
	}
	
	public void addRole(Role r){
		role_list.add( r );
	}
	
	public void removeRole(int i){
		role_list.remove( i );
	}
	
	private Role getRole( int index ) {
		Role  r = role_list.get( index );
		if ( getNowTime() > r.getStartTime()) {
			return role_list.get( index );
		}
		return null;
		
	}
	
	private int getNowTime() {
		return (int)(System.currentTimeMillis() - Game.GAME_START_TIME);
	}

}