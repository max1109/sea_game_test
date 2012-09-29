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
	int move_animation[] = {
			R.drawable.octopus_1,
			R.drawable.octopus_2,
			R.drawable.octopus_3,
			R.drawable.octopus_4,
			R.drawable.octopus_5,
			R.drawable.octopus_5,
			R.drawable.octopus_5,
			
	};
	int _move_animation = 0;
	int x = 0, y = 0;
	Context c  = null;
	public Protagonist(String name , Context c ) {
		this.name = name;
		this.c = c; 
	}

	public int getHeight() {
		return height;
	}
	public void setX( int x) {
		this.x= x;
	}
	public void setY( int y) {
		this.y= y;
	}
	public void setHeight( int h) {
		height = h;
	}
	public void drawProtagonist( Canvas canvas ) {
		Bitmap bmp = BitmapFactory.decodeResource(c.getResources(), getMoveAnimation() );
//		canvas.drawBitmap(bmp, x , y , null);
		canvas.drawBitmap(bmp, x - bmp.getWidth() / 2, y - bmp.getHeight() / 2 , null); //test use
	}
	public int getMoveAnimation() {
		_move_animation  = ( _move_animation + 1 ) % (move_animation.length - 1 );   
		return move_animation[ _move_animation ];
	}
}
