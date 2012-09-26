package com.game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.example.gameData.Protagonist;

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

	public void init(Protagonist p, Bitmap background ) {
		this.p = p;
		this.background = background;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if ( background != null ) {
			canvas.drawBitmap(background , 0 , 0, null);
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
}
