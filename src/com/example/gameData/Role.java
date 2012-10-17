package com.example.gameData;

import android.graphics.Canvas;

public abstract class Role extends Location{
	
	String name ="";
	private boolean dead = false;
	private int start_time = 0; // game start time is 4
	private int height = 0;
	private int width = 0;
	
	public Role(int x , int y ,String name , int time ) {
		super (x , y);
		
		this.name = name ;
		this.start_time = time ;
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	protected void setHeight( int h) {
		height = h;
	}
	protected void setWidth(int w) {
		width = w;
	}
	
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead( boolean  d) {
		dead = d;
	}
	
	public int getStartTime() {
		return start_time;
	}
	
//	public void setStartTime( int time ) {
//		start_time = time;
//	}
	
	abstract public void draw( Canvas canvas);
	abstract public void close();
}
