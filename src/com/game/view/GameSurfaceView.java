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
import com.example.gameData.Fish;
import com.example.gameData.Protagonist;
import com.example.gameData.Role;
import com.example.sea_game_testing.Game;

public class GameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {

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

//	private Context context = null;
	private Protagonist p = null;
	private Checkpoints checkpoints = null;
	private Background background = null;
	private BloodView blood= null;
	
	private void init(Context context) {
//		this.context = context;
		holder = this.getHolder();
		holder.addCallback(this);

	}
	
	public void init(
			Protagonist p, 
			Background background,
			Checkpoints checkpoints,
			BloodView blood) 
	{
		this.p = p;
		this.background = background;
		this.checkpoints = checkpoints;
		this.blood= blood;
	}

	public void Draw() {
		Canvas canvas = holder.lockCanvas();

		if (canvas != null && background != null) {
			background.drawBackground(canvas);

		}
		if (canvas != null && p != null) {
			p.drawProtagonist(canvas);
			CollideListener();

		}
		if (canvas != null && checkpoints != null) {
			checkpoints.draw(canvas);
		}

		if (canvas != null) {
			holder.unlockCanvasAndPost(canvas);
		}
	}

	private void CollideListener() {

		boolean collide_width = false;
		boolean collide_height = false;
		for (int x = 0; x < checkpoints.getRoleListSize(); x++) {
			Fish r = (Fish)checkpoints.getRole(x);
			// Log.e("chec CollideListener" , "x = " + x );
			if (r != null) {
				collide_width = CollideWidth(r);
				collide_height = CollideHeight(r);
				if ( 
						collide_width && collide_height &&
						p.getPower() > r.getPower()) 
				{ // 碰撞
					r.setDead(true);
					// add 分數
					Game.blood += r.getBlood();
					if (Game.blood >= 100)
						Game.blood = 100;
					blood.setBlood( Game.blood );
					// Log.e("chec RoleLeave" , "fish is dead" + Game.score );
				} else if ( 
						collide_width && collide_height &&
						p.getPower() < r.getPower() ) 
				{
					
				}
				RoleLeave(r);
			}
		}
	}

	// 針對章魚圖片各種大小做判斷
	private void RoleLeave(Role r) {
		if (r.getX() + r.getWidth() < 0) {
			Log.e("chec RoleLeave", "fish is leave");
			r.setDead(true);
		}
	}

	private boolean CollideWidth(Role r) {
		int px = p.getX() - p.getWidth() / 2;
		int pX = p.getX() + p.getWidth() / 2;
		if ( (px <= r.getX() && r.getX() <= pX) || 
			 (px <= (r.getX() + r.getWidth()) && (r.getX() + r.getWidth()) <= pX)) {
			return true;
		}
		return false;
	}

	private boolean CollideHeight(Role r) {
		// 因為圖片打點是以圖片中心點為 xy，若要得知圖片高度必須加上圖片一半的高度
		// 修改魚的高度
		int py = p.getY() - p.getHeight() / 2;
		int pY = p.getY() + p.getHeight() / 2;
//		Log.e("CollideHeight" , "p " + p.getY() + " pH " + p.getHeight());
//		Log.e("CollideHeight" , "py " + py + " pY " + pY);
//		Log.e("CollideHeight" , "r.getY() " + r.getY() + " r.getY() + r.getHeight() / 2 " + (r.getY() + r.getHeight() / 2));
		if ( ( py <= r.getY() && r.getY() <= pY ) || 
			 ( py <= (r.getY() + r.getHeight() / 2 ) && ( r.getY() + r.getHeight() / 2 ) <= pY )) {
//			Log.e("CollideHeight","dead");
			
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
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		// Log.e("touch" , "" +event.getAction());
		if (p != null) {
			p.setX((int) event.getX());
			p.setY((int) event.getY());
		}
		return true;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// Log.w(" surfaceCreated " , "");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
}