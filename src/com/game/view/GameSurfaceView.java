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

	private Protagonist p = null;
	private Context context = null;
	private Checkpoints checkpoints = null;
	private Background background = null;

	private void init(Context context) {
		this.context = context;
		holder = this.getHolder();
		holder.addCallback(this);

	}

	public void init(Protagonist p, Background background,
			Checkpoints checkpoints) {
		this.p = p;
		this.background = background;
		this.checkpoints = checkpoints;
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

		for (int x = 0; x < checkpoints.getRoleListSize(); x++) {
			Role r = checkpoints.getRole(x);
			// Log.e("chec CollideListener" , "x = " + x );
			if (r != null) {
				// Log.e("game suface View "+ x , "r.getX()  " + r.getX() +
				// " r.getX() + r.getWidth() " + (r.getX() + r.getWidth() ));
				// Log.e("game suface View "+ x , "r.getY()  " + r.getY() +
				// " r.getY() + r.getHeight() " + (r.getY() + r.getHeight() ));
				// Log.e("game suface View "+ x , "px " + p.getX() + " py " +
				// p.getY() + " px  w" + ( p.getX() + p.getWidth() )+ " py h " +
				// (p.getY() + p.getHeight()));
				if (CollideWidth(r) && CollideHeight(r)) { // 碰撞
					r.setDead(true);
					// add 分數
					Game.score += 10;
					if (Game.score >= 100)
						Game.score = 100;
					// Log.e("chec RoleLeave" , "fish is dead" + Game.score );
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
		if ((px <= r.getX() && r.getX() <= pX)
				|| (px <= (r.getX() + r.getWidth()) && (r.getX() + r.getWidth()) <= pX)) {
			// Log.e("game suface View " ,
			// "----------------------\nCollideWidth true ");
			return true;
		}
		// Log.e("game suface View " , "CollideWidth false ");
		return false;
		
	}

	private boolean CollideHeight(Role r) {
		// 因為圖片打點是以圖片中心點為 xy，若要得知圖片高度必須加上圖片一半的高度
		int py = p.getY() - p.getHeight() / 2;
		int pY = p.getY() + p.getHeight() / 2;
		if ((py <= r.getY() && r.getY() <= pY)
				|| (py <= (r.getY() + r.getHeight()) && (r.getY() + r
						.getHeight()) <= pY)) {
			// Log.e("game suface View " ,
			// "----------------------\nCollideHeight true");
			return true;
		}
		// Log.e("game suface View " , "CollideHeight false ");
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
