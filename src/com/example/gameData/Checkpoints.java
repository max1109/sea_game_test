package com.example.gameData;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.example.sea_game_testing.util.Util;

public class Checkpoints {
	List<Role> role_list = new ArrayList<Role>();
	private int end_time = 0; 
	public Checkpoints() {
	
	}
	public Checkpoints(int end_time) {
		this.end_time = end_time;
	}
	
	// get game end time
	public int getEndTime() {
		return end_time;
	}
	
	// set game end time
	public void setEndTime( int endTime) {
		this.end_time = endTime;
	}
		
	public void draw( Canvas canvas ) {
		
		for ( int index = 0 ; index < role_list.size(); index++) {
			Role r = getRole( index );
			if (r != null) {
				r.draw(canvas);
			}
		}
		
	}
	int role_num = 0;
	public boolean isAllRoleDead () {
		// 若怪物已死亡會把ID直給避免重複判斷
		for (int x = role_num; x < role_list.size(); x++ ) {
			if (!role_list.get( x ).isDead()) {
				return false;
			}else {
				role_num = x;
			}
		}
		return true;
	}
	
	public void addRole(Role r){
		role_list.add( r );
	}
	
	public int getRoleListSize() {
		return role_list.size();
	}
	
	
	public void removeRole(int i) {
		role_list.remove( i );
	}
	
	public Role getRole( int index ) {
		Role  r = role_list.get( index );
//		if ( !r.getDead() && getNowTime() > r.getStartTime() * 1000 ) {
		// 怪物死亡 出場時間
		if ( !r.isDead() && Util.GAME_START_TIME > r.getStartTime() * 100 ) {
//			Log.e("checkpoints", "get Role" + index  + "Game.GAME_START_TIME" + Game.GAME_START_TIME + " r.getStartTime()  " + r.getStartTime() * 1000 );
			return role_list.get( index );
		}
		return null;
	}
	
	public void close() {
		role_list = null;
	}


}
