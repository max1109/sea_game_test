package com.example.gameData;

import com.example.sea_game_testing.R;



public class Protagonist {

	String name="";
	int power = 0;
	int height = 0;
	int move_animation[] = {
			R.drawable.squid,
			R.drawable.squid2
	};
	int _move_animation;
	
	public Protagonist(String name) {
		this.name = name;

	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight( int h) {
		height = h;
	}
	
	public int getMoveAnimation() {
		_move_animation  =_move_animation + 1% 2;   
		return move_animation[ _move_animation ];
	}
}
