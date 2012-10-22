package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.sea_game_testing.R;

public class Fish extends Role {

	private int blood = 0;
	private int power = 0; // 魚魚等級 等級越高越強 
	private int _move_animation = 0;
	private int _move = 8;
	private Bitmap  move_animation_bitmap[];
	private int move_animation[] = {
		R.drawable.fish1_1,
		R.drawable.fish1_2,
		R.drawable.fish1_3
	};
	
			
	public Fish(
			int x, int y, 
			String name , Context c  , 
			int time, int power , int blood 
			) 
	{
		super(x, y, name, time);
		this.blood = blood;
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
	
	protected void setHeight( int h) {
		super.setHeight(h);
	}
	
	protected void setWidth(int w) {
		super.setWidth( w );
	}

	public int getPower() {
		return power;
	}
	
	public void setPower( int power) {
		this.power = power;
	}
	
	public int getBlood() {
		return blood;
	}
	
	public void setBlood( int blood) {
		this.blood= blood;
	}
	
	public void setMove( int m) {
		_move = m;
	}
	
	@Override
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

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
