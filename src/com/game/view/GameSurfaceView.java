package com.game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gameData.Protagonist;
import com.example.sea_game_testing.Game;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	public GameSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public GameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public GameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private Protagonist p = null;
	private Context context = null;
	private Bitmap background = null;
	private void init(Context context) {
		this.context = context;
		holder = this.getHolder();
		holder.addCallback(this);
		
	}
	public void init(Protagonist p, Bitmap bmp) {
		this.p = p;
		this.background = bmp;
		Log.w("init" , background.getWidth() + " " + background.getHeight());
		
	}
	
	int background_x;
	public int getBackgroundX() {
		return background_x;
	}
//	public void Start() {
//		if ( background != null)
//			run();
//		else 
//			Log.e("Start" , "bg is null");	
//	}
	public void setBackgroundX( int x) {
//		if (x >  ( background.getWidth() - 1280 ) ) {
//			x = 0;
//		}
		background_x = x;
	}

	private void drawBackground( Canvas canvas ) {
		background_x += 40;
		
			
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
			Log.w("onDraw 2 " , "background_x = " + background_x + (canvas == null));
			Paint p = new Paint();
			canvas.drawBitmap(bmp, 0 , 0, p);
			bmp.recycle();
			bmp = null;
		}
	}
//	@Override
//	protected void onDraw(Canvas canvas) {
//		if ( background != null ) {
//			drawBackground(canvas);
////			Bitmap bmp = Bitmap.createBitmap(background, background_x, 0, Game.DEVICE_WIDTH, background.getHeight());
////			Log.w("onDraw" , "background_x = " + background_x);
////			canvas.drawBitmap(bmp, 0 , 0, null);
////			bmp.recycle();
////			bmp = null;
//			
//		}else {
//			canvas.drawColor(Color.RED);
//		}
//	}


	public void Draw() {
		Canvas canvas = holder.lockCanvas();
		if ( background != null && canvas != null) {
			drawBackground(canvas);
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

//	@Override
//	public void run() {
//		while(true) {
//			background_x+= 10;
//			Canvas canvas = holder.lockCanvas();
////			onDraw(canvas);
//			drawBackground(canvas);
////			holder.unlockCanvasAndPost(canvas);
//			try {
//				Thread.sleep(110);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			}
//			
//		}
		
	
}
