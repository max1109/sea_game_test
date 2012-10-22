package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class BackgroundObject extends Role{
	
	private Bitmap  move_animation_bitmap[];
	private int _move_animation = 0;
	private int _move = 4;
	public BackgroundObject(
			int x, int y, String name , 
			Context c  , int time,
			int img[] 
			) 
	{
		super(x, y, name, time);
		move_animation_bitmap = new Bitmap[img.length];
		for (int index = 0; index < img.length; index++ ) {
			move_animation_bitmap[index] = BitmapFactory.decodeResource(
					c.getResources(), img[index]);
		}
	}

	public int getMoveAnimation() {
		_move_animation  = ( _move_animation + 1 ) % ( move_animation_bitmap.length );
		return _move_animation;
	}
	
	public void close() {
		for (int index = 0; index < move_animation_bitmap.length; index++ ) {
			move_animation_bitmap[index].recycle();
			move_animation_bitmap[index] = null;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		int index = getMoveAnimation() ;
		super.setX( (float)(super.getX() - _move) );
		if ( super.getX() +  move_animation_bitmap[ index ].getWidth() > 0 ) 
		{
			canvas.drawBitmap( 
					move_animation_bitmap[ index ] , 
					super.getX(), 
					super.getY() - (move_animation_bitmap[ index ].getHeight() / 2) , 
					null
			);
		} else if ( super.getX() +  move_animation_bitmap[ index ].getWidth() < 0  ) {
			super.setDead( true );
		}
		
	}
	
}
