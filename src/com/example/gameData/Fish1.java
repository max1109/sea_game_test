package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.sea_game_testing.R;

public class Fish1 extends Role {

	public Fish1() {
		
	}
			
	public Fish1(int x, int y, String name , Context c  , int time) {

		super(x, y, name, time);
		init( c );
	}
	
	private void init( Context c ) {
		move_animation_bitmap = new Bitmap[move_animation.length];
		
		for (int x = 0; x < move_animation.length; x++ ) {
			move_animation_bitmap[x] = BitmapFactory.decodeResource(
					c.getResources(), 
					move_animation[x]
					);
//			Log.w("Fish1" , "for " + tmp[x].getWidth());
		}
//		Log.w("Fish1" , "size" + move_animation.length);
//		if (tmp[0] == null) {
//			Log.w("Fish1" , "tmp 0  null");
//		} else {
//			Log.w("Fish1" , "tmp 0  not null" + tmp[0].getWidth());
//		}
		setWidth( move_animation_bitmap[0].getWidth());
		setHeight( move_animation_bitmap[0].getHeight());
//		move_animation_bitmap = tmp;
	}
	public void setHeight( int h) {
		super.setWidth(h);
	}
	public void setWidth(int w) {
		super.setHeight( w );
	}
	Bitmap  move_animation_bitmap[];
	int move_animation[] = {
		R.drawable.fish1_1,
		R.drawable.fish1_2,
		R.drawable.fish1_3
	};
	
	int _move_animation = 0;
	int _move = 8;

	public void setMove( int m) {
		_move = m;
	}
	public void draw( Canvas canvas) {
		int gg = getMoveAnimation();
		Bitmap tmp = move_animation_bitmap[ gg ];
		
		super.x = super.x - _move;
//		Log.w("Fish1 draw" , "x = " + x );
		canvas.drawBitmap(
				tmp, 
				super.x, 
				super.y - tmp.getHeight() / 2 , 
				null
			); 	
	}
	
	public int getMoveAnimation() {
		_move_animation  = ( _move_animation + 1 ) % ( move_animation_bitmap.length );
//		Log.e("Fish1 getMoveAnimation" , " MoveAnimation = " + _move_animation );
		return _move_animation;
	}
	
}
