package com.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BloodView extends View {

	public BloodView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public BloodView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public BloodView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		scale = (float)(( max - min ) / 100f);
	}
	
	public void setBloodView ( int score) {
		width = score * scale;
		Log.e("blood" , "score " + score + " scale " + scale + "width = " + width );
		postInvalidate();
	}
	
	int min = 2;
	int max = 228;
	float scale = 0;
	float width = 0;
	@Override
	protected void onDraw(Canvas canvas) {
		Paint p = new Paint();
		Shader mShader = new LinearGradient(0, 0, 30, 100, new int[]{ Color.rgb(183, 19, 11) , Color.rgb(238, 71, 71)}, null, Shader.TileMode.REPEAT);
		p.setShader(mShader);	
		canvas.drawRect(min, min, width, 28, p);
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
