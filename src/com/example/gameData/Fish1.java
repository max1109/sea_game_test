package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.sea_game_testing.R;

public class Fish1 extends Role{

	public Fish1() {
		
	}
			
	public Fish1(int x, int y, String name , Context c  , int time) {

		super(x, y, name, time);
		init( c );
	}
	
	private void init( Context c ) {
		Bitmap tmp[] = new Bitmap[move_animation.length];
		for (int x = 0; x > tmp.length; x++ ) {
			tmp[x] = BitmapFactory.decodeResource(
					c.getResources(), 
					move_animation[x]
					);
		}
		move_animation_bitmap = tmp;
	}
	
	private Context c = null; 
	Bitmap  move_animation_bitmap[];
	int move_animation[] = {
		R.drawable.fish1_1,
		R.drawable.fish1_2,
		R.drawable.fish1_3
	};
	
	int _move_animation = 0;
	int _move = 8;
	
	 
	
	public void draw( Canvas canvas) {
		Bitmap tmp = move_animation_bitmap[ getMoveAnimation() ];
		canvas.drawBitmap(
				tmp, 
				x - tmp.getWidth() / 2 - _move, 
				y - tmp.getHeight() / 2 , 
				null
			); //test use	
	}
	
	public int getMoveAnimation() {
		_move_animation  = ( _move_animation + 1 ) % (move_animation.length - 1 );   
		return move_animation[ _move_animation ];
	}
	
}
