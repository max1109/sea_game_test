package com.example.gameData;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.sea_game_testing.Game;
import com.example.sea_game_testing.util.Util;

public class Background {
	
//	private Context c = null;
	private Bitmap background = null;
	int location = 0;
	int moveSize = 40;
	List<Role> BG_Element= null;
	public Background( Bitmap background , List<Role> item ) 
	{
		this.BG_Element = item;
		this.background = background;
	}


	public void close() {
		background.recycle();
		background = null;
	}
	
	public void drawBackground( Canvas canvas ) {
		location += moveSize;

		if ( location >  ( background.getWidth() - Game.DEVICE_WIDTH )  ) {
			
			Bitmap bmp1 = Bitmap.createBitmap(
					background, 
					location , 0, 
					background.getWidth() - location , background.getHeight()
				);
			Bitmap bmp2 = Bitmap.createBitmap(
					background,
					0 , 0, 
					location + Game.DEVICE_WIDTH - background.getWidth(), background.getHeight()
				);
			
			canvas.drawBitmap(bmp1, 0 , 0, null);
			canvas.save();
			canvas.drawBitmap(bmp2, bmp1.getWidth() , 0, null);
			canvas.restore();
			bmp1.recycle();
			bmp1 = null;
			bmp2.recycle();
			bmp2 = null;
			location  = location % ( background.getWidth() - moveSize);
		} else {
			Bitmap bmp = Bitmap.createBitmap(background, location, 0, Game.DEVICE_WIDTH, background.getHeight());
			Paint p = new Paint();
			canvas.drawBitmap(bmp, 0 , 0, p);
			bmp.recycle();
			bmp = null;
		}
		
		for ( int index = 0 ; index < BG_Element.size(); index++ ) {
			Role r = BG_Element.get( index );
			if ( !r.isDead() && Util.GAME_START_TIME > r.getStartTime() ) {
				r.draw( canvas );
			}
		}
	}
}
