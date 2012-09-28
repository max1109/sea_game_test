package com.game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gameData.Background;
import com.example.gameData.Protagonist;
import com.example.sea_game_testing.Game;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	public GameSurfaceView(Context context) {
		super(context);
		init(context);
	}

	public GameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private Protagonist p = null;
	private Context context = null;
//	private Bitmap background = null;
	private Background background = null;
	private void init(Context context) {
		this.context = context;
		holder = this.getHolder();
		holder.addCallback(this);
		
	}
	public void init(Protagonist p, Background background) {
		this.p = p;
		this.background = background;
//		Log.w("init" , background.getWidth() + " " + background.getHeight());
		
	}
	
	public void Draw() {
		Canvas canvas = holder.lockCanvas();
		if ( canvas != null ) {
			if ( background != null ) {
				
			} else {
				
			}
		}
		if ( background != null && canvas != null) {
			background.drawBackground(canvas);
			holder.unlockCanvasAndPost(canvas);
		}else {
//			canvas.drawColor(Color.RED);
		}
	}

	private SurfaceHolder holder = null;
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
//		 TODO Auto-generated method stub
		Log.w(" surfaceCreated " , "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

		
	
}
