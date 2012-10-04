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

public class BloodView extends View {

	public BloodView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public BloodView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public BloodView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private Context context = null;
	private void init(Context context) {
		this.context = context;
	}


	public void init(Bitmap bmp) {
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
	
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
	
}
