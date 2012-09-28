package com.example.gameData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sea_game_testing.Game;

public class Background {
	
//	int
//	private Context c = null;
	private Bitmap background = null;
	int location = 0;
	int moveSize = 40;
	public Background(  Bitmap background ) {
//		this.c = c;
		this.background = background;
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
			
//			Log.w("onDraw 1 " , "location = " + location);
			
			canvas.drawBitmap(bmp1, 0 , 0, null);
			canvas.save();
			canvas.drawBitmap(bmp2, bmp1.getWidth() , 0, null);
			canvas.restore();
//			Log.w("onDraw 1 " , "bmp1.getWidth()" + bmp1.getWidth() + "bmp2.getWidth()" + bmp2.getWidth());
			bmp1.recycle();
			bmp1 = null;
			bmp2.recycle();
			bmp2 = null;
			location  = location % ( background.getWidth() - moveSize);
		} else {
			Bitmap bmp = Bitmap.createBitmap(background, location, 0, Game.DEVICE_WIDTH, background.getHeight());
//			Log.w("onDraw 2 " , "location = " + location + (canvas == null));
			Paint p = new Paint();
			canvas.drawBitmap(bmp, 0 , 0, p);
			bmp.recycle();
			bmp = null;
		}
	
	}
}
