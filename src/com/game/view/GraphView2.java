package com.game.view;

import com.example.sea_game_testing.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * GraphView creates a scaled line or bar graph with x and y axis labels.
 * 
 * @author Arno den Hond
 * 
 */
public class GraphView2 extends View implements OnGestureListener {

	Context context = null;

	public GraphView2(Context context) {
		super(context);
		this.context = context;
		// detector = new GestureDetector(this);
	}

	public GraphView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		// detector = new GestureDetector(this);
	}

	public GraphView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		// detector = new GestureDetector(this);
	}

	int WIDTH = 480;

	int max_Y_axis = 100;
	int max_X_axis = 100;

	int interval_Y = 10;
	int interval_X = 10;
	int interval_width = 20;
	int text_size = 3;
	int zoom = 4;
	int padding_left = 35;
	int padding_top = 20;

	int Y_axis[] = new int[ max_Y_axis /interval_Y ];
	private void drawYaxis(Canvas canvas) {
		Paint p_line = new Paint();
		Paint p_text = new Paint();
		p_line.setStrokeWidth(text_size);
		p_line.setColor(Color.GRAY);
		p_text.setTextSize(20);
		p_text.setColor(Color.BLUE);
		
		canvas.drawLine(padding_left, padding_top - (text_size - 1),
				padding_left,
				padding_top + max_Y_axis * zoom + (text_size - 1), p_line);
		int ary_num = interval_Y;
		for (int y = 0; y < max_Y_axis; y++) {

			if (y % interval_Y == 0) {
				canvas.drawLine(padding_left, padding_top + y * zoom,
						padding_left + interval_width, padding_top + y * zoom,
						p_line);
				canvas.drawText("" + (max_Y_axis - y), 3, padding_top + y
						* zoom, p_text);
				Y_axis[ --ary_num ] = padding_top + y * zoom;
			}
			// #20A9C4
		}
	}

	int[] data = { 10,20, 30,40,100,20,23,78,61,78,12,32,51,12,30,74,58,63,14,50, 50,60,70,80,90,100,12,12,12,12,12,22
			,10,20, 30,40,100,20,23,78,61,78,12,32,51,12,30,74,58,63,14,50, 50,60,70,80,90,100,12,12,12,12,12,22,
			10,20, 30,40,100,20,23,78,61,78,12,32,51,12,30,74,58,63,14,50, 50,60,70,80,90,100,12,12,12,12,12,22,
			10,20, 30,40,100,20,23,78,61,78,12,32,51,12,30,74,58,63,14,50, 50,60,70,80,90,100,12,12,12,12,12,22,
			10,20, 30,40,100,20,23,78,61,78,12,32,51,12,30,74,58,63,14,50, 50,60,70,80,90,100,12,12,12,12,12,22,
			};

	
	private void drawXaxis(Canvas canvas) {
		Paint p_line = new Paint();
		Paint p_text = new Paint();
		p_line.setStrokeWidth(text_size);
		p_line.setColor(Color.GRAY);
		p_text.setTextSize(20);
		p_text.setColor(Color.BLUE);
		
		int y = padding_top + max_Y_axis * zoom + 2;
		if ( move_x < 0 ) {
			move_x = 0;
//			Log.e("drawXaxis ", "if");
		// �j�L��ƪ�� �B ��ƪ�״�@����X��ƪ�פp��s
		}else if (move_x >= data.length && data.length - max_X_axis < 0) {
			move_x = 0;
//			Log.e("drawXaxis ", "else if1");
			
		}else if (move_x >= data.length && move_x - max_X_axis > 0) {
//			Log.e("drawXaxis ", "else if2");
			move_x = data.length - max_X_axis ;
		}
		
//		Log.e("drawXaxis ",  data.length + " " + move_x);
		
		int g = 0;
		int max_line =0 ;
		// <=  .... 0 ~ 100  < .... 0 90 
		for (int index = move_x; index <= move_x + max_X_axis; index++) {
			
			if (index % interval_X == 0) {
				int x = padding_left + g * zoom;
				canvas.drawLine(x, y, x, y - interval_width, p_line);
				canvas.drawText("" + index, x, y + padding_top, p_text);
				max_line = x;
			}
			g++;
			// #20A9C4
		}
		
		canvas.drawLine(padding_left, y, WIDTH, y, p_line);
	}

	int drawDataX = 2;
	private void drawData(Canvas canvas) {
		Paint p_line = new Paint();
		p_line.setAntiAlias(true);
		p_line.setStrokeWidth(text_size);
		p_line.setColor(Color.RED);
		
		
		if ( move_x < 0 ) {
			move_x = 0;
//			Log.e("drawXaxis ", "if");
		// �j�L��ƪ�� �B ��ƪ�״�@����X��ƪ�פp��s
		}else if (move_x >= data.length && data.length - max_X_axis < 0) {
			move_x = 0;
//			Log.e("drawXaxis ", "else if1");
			
		}else if (move_x >= data.length && move_x - max_X_axis > 0) {
//			Log.e("drawXaxis ", "else if2");
			move_x = data.length - max_X_axis ;
		}
		
//		Log.e("drawXaxis ",  data.length + " " + move_x);
		
		int g = 0;
		// <=  .... 0 ~ 100  < .... 0 90 
		Log.e("data" , move_x + " " + (move_x + max_X_axis));
		for (int index = move_x; index <= move_x + max_X_axis; index++) {
			
			if (index < data.length && index + 1 < data.length) {
				int x = padding_left + g * zoom ;
				int num = data[ index ] / interval_Y ;
				
				if ( num == 10 ) {
					num = 9;
				}
				int y = Y_axis[ num ] - ( ( data[ index ] % interval_Y ) * zoom );
				g++;
				int x2 = padding_left + g * zoom + 4;
				int num2 = data[ index + 1 ] / interval_Y ;
				if ( num2 == 10 ) {
					num2 = 9;
				}
				int y2 = Y_axis[ num2  ] - ( ( data[ index + 1] % interval_Y ) * zoom );
//				Log.e("data" ,x + " " + y + " " + x2 + " " + y2);
				canvas.drawLine(x, y, x2, y2, p_line);
				
			}
			g++;
			
		}
//		Log.e("drawXaxis ", "g = " + g );
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
//		Log.e("drawXaxis ", "data " + data.length );
		canvas.drawColor(context.getResources().getColor(R.drawable.sea));
		drawYaxis(canvas);
		drawXaxis(canvas);
		drawData(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	int move_x = 0;
	int move_x_2 = 0;
	int move_x_3 = 0;
	int x1 = 0;
	int x2 = 0;

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		move_x_2 = (int) (e1.getX() - e2.getX()) / 4 / 2;
		if (move_x_2 > 20) {
			move_x_2 = (int) (e1.getX() - e2.getX()) / 4 / 2;
			// Log.e(" onScroll ", move_x + " left " + move_x_2 );
			if ( move_x_2 + max_X_axis < data.length )
				move_x += move_x_2;
			else 
				move_x = data.length - max_X_axis;

			
			postInvalidate();
		} else if (move_x_2 < -20) {
			move_x_2 = (int) (e1.getX() - e2.getX()) / 4 / 2;
			// Log.e(" onScroll ", move_x + " right  " + move_x_2 );
			if ( move_x_2 + max_X_axis < data.length )
				move_x += move_x_2;
			else 
				move_x = data.length - max_X_axis;

			
			postInvalidate();
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDataNum(int x) {
		// TODO Auto-generated method stub
		if ( x + max_X_axis < data.length )
			move_x = x;
		else 
			move_x = data.length - max_X_axis;
	}

	// GestureDetector detector;
	// public void setD(GestureDetector detector) {
	// // TODO Auto-generated method stub
	// this.detector = detector;
	// }

}
