package com.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gameData.Background;
import com.example.gameData.Checkpoints;
import com.example.gameData.Protagonist;
import com.example.gameData.Role;
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
	private Checkpoints checkpoints = null; 
	private Background background = null;
	private void init(Context context) {
		this.context = context;
		holder = this.getHolder();
		holder.addCallback(this);
		
	}
	public void init(Protagonist p, Background background, Checkpoints checkpoints) {
		this.p = p;
		this.background = background;
		this.checkpoints = checkpoints; 
		h = p.getHeight();
		w = p.getWidth();
		pX = p.getX() + w;
		pY = p.getY() + h;
	}
	
	public void Draw() {
		Canvas canvas = holder.lockCanvas();
		
		if ( canvas != null && background != null ) {
			background.drawBackground(canvas);
			
		}
		if ( canvas != null && p != null ) {
			p.drawProtagonist(canvas);
			CollideListener();
		}
		if ( canvas != null && checkpoints != null ) {
			checkpoints.draw(canvas);
		}
		
		if (canvas != null) {
			holder.unlockCanvasAndPost(canvas);
		}
	}
	int h = 0;
	int w = 0;
	int pX = 0; 
	int pY = 0;
	private void CollideListener() {
		
		for (int x = 0; x < checkpoints.getRoleListSize(); x++ ) {
			Role r = checkpoints.getRole( x );
			if (r != null ) {
				if ( CollideWidth(r) && CollideHeight(r) ) { //碰撞 
					r.setDead( true );
//					add 分數
					Game.score+=10;
					if ( Game.score >= 100 )
						Game.score = 100;
				}
			}
		}
	}
	private boolean Leave( Role r ) {
		if ( r.getX() < 0 ) {
			return true;
		}
		return false;
	}
	private boolean CollideWidth( Role r ) {
		if ( 
				( w <= r.getX() && r.getX() <= pX ) && 
				( w <= ( r.getX() + r.getWidth() ) && ( r.getX() + r.getWidth() ) <= pX ) ) 
		{
			return true;
		}
		return false;
	}
	
	private boolean CollideHeight( Role r ) {
		if ( 
				( h <= r.getY() && r.getY() <= pY ) && 
				( h <= (r.getY() + r.getHeight() ) && (r.getY() + r.getHeight() ) <= pY ) ) 
		{
			return true;
		}
		return false;
	}
	
	private SurfaceHolder holder = null;
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public boolean onTouchEvent( MotionEvent event ) {
		super.onTouchEvent(event);
//		Log.e("touch" , "" +event.getAction());
		if ( p != null ) {
			p.setX( (int)event.getX());
			p.setY( (int)event.getY());
		}
		
		return true;
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
//		 TODO Auto-generated method stub
//		Log.w(" surfaceCreated " , "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

		
	
}
