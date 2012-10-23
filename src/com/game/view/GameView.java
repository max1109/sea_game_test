package com.game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.gameData.Protagonist;
import com.example.sea_game_testing.Game;
/*
public class GameView extends View {

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private Protagonist p = null;
	private Context context = null;
	private Bitmap background = null;
	private void init(Context context) {
		this.context = context;
	}

	int background_x;
	public int getBackgroundX() {
		return background_x;
	}
	public void setBackgroundX( int x) {
//		if (x >  ( background.getWidth() - 1280 ) ) {
//			x = 0;
//		}
		background_x = x;
	}
	public void init(Protagonist p, Bitmap bmp) {
		this.p = p;
		this.background = bmp;
		Log.w("init" , background.getWidth() + " " + background.getHeight());
	}
	
	private void Background( Canvas canvas ) {
		
	}
	private void DrawBackground( Canvas canvas ) {
		if ( background_x >  ( background.getWidth() - Game.DEVICE_WIDTH )  ) {
			Bitmap bmp1 = Bitmap.createBitmap(
					background, 
					background_x , 0, 
					background.getWidth() - background_x , background.getHeight()
				);
			Bitmap bmp2 = Bitmap.createBitmap(
					background,
					0 , 0, 
					background_x + Game.DEVICE_WIDTH - background.getWidth(), background.getHeight()
				);
			
			Log.w("onDraw 1 " , "background_x = " + background_x);
			
			canvas.drawBitmap(bmp1, 0 , 0, null);
			canvas.save();
			canvas.drawBitmap(bmp2, bmp1.getWidth() , 0, null);
			canvas.restore();
			Log.w("onDraw 1 " , "bmp1.getWidth()" + bmp1.getWidth() + "bmp2.getWidth()" + bmp2.getWidth());
			bmp1.recycle();
			bmp1 = null;
			bmp2.recycle();
			bmp2 = null;
			background_x  = background_x % ( background.getWidth() - 40);
		} else {
			Bitmap bmp = Bitmap.createBitmap(background, background_x, 0, Game.DEVICE_WIDTH, background.getHeight());
			Log.w("onDraw 2 " , "background_x = " + background_x);
			canvas.drawBitmap(bmp, 0 , 0, null);
			bmp.recycle();
			bmp = null;
		}
	}
	@Override
	protected void onDraw(Canvas canvas) {
		if ( background != null ) {
			DrawBackground(canvas);
//			Bitmap bmp = Bitmap.createBitmap(background, background_x, 0, Game.DEVICE_WIDTH, background.getHeight());
//			Log.w("onDraw" , "background_x = " + background_x);
//			canvas.drawBitmap(bmp, 0 , 0, null);
//			bmp.recycle();
//			bmp = null;
			
		}else {
			canvas.drawColor(Color.RED);
		}
	}

	
	@Override
	protected void onAttachedToWindow() {
		// mAnimate = true;
		super.onAttachedToWindow();
	}

	@Override
	protected void onDetachedFromWindow() {
		// mAnimate = false;
		super.onDetachedFromWindow();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;	
	}
	
	public void Refresh() {
		invalidate();
	}
}
*/