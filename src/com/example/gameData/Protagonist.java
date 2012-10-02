package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.sea_game_testing.R;



public class Protagonist {

	String name="";
	int power = 0;
	int height = 0;
	int width = 0;
	
	Bitmap  move_animation_bitmap[];
	int move_animation[] = {
			R.drawable.octopus_1,
			R.drawable.octopus_2,
			R.drawable.octopus_3,
			R.drawable.octopus_4,
			R.drawable.octopus_5,
			R.drawable.octopus_5
			
			
	};
	int _move_animation = 0;
	int x = 0, y = 0;
	Context c  = null;
	public Protagonist(String name , Context c ) {
		this.name = name;
		this.c = c;
		init(c);
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	public int getX() {
		return x;
	}
	public void setX( int x) {
		this.x= x;
	}
	public void setY( int y) {
		this.y= y;
	}
	public int getY() {
		return y;
	}
	
	private void init( Context c ) {
		Bitmap tmp[] = new Bitmap[move_animation.length];
		for (int x = 0; x < tmp.length; x++ ) {
			tmp[x] = BitmapFactory.decodeResource(
					c.getResources(), 
					move_animation[x]
					);
		}
		width = tmp[0].getWidth();
		height = tmp[0].getHeight();
		move_animation_bitmap = tmp;
	}
	
	public void drawProtagonist( Canvas canvas ) {
		Bitmap bmp = move_animation_bitmap[ getMoveAnimation() ];
//		canvas.drawBitmap(bmp, x , y , null);
		canvas.drawBitmap(bmp, x - bmp.getWidth() / 2, y - bmp.getHeight() / 2 , null); //test use
	}
	public int getMoveAnimation() {
		_move_animation  = ( _move_animation + 1 ) % (move_animation.length );   
		return _move_animation;
	}
	
	
}
