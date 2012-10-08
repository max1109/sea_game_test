package com.example.gameData;

import android.graphics.Canvas;

public abstract class Role {
	int x = 0;
	int y = 0;
	String name ="";
	private boolean dead = false;
	private int start_time = 0; // game start time is 4
	private int height = 0;
	private int width = 0;
	
	
	public Role () {
		
	}
	
	public Role(int x , int y ,String name , int time ) {
		this.x = x;
		this.y = y;
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
	
	public int getStartTime() {
		return start_time;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead( boolean  d) {
		dead = d;
	}
	
	public void setStartTime( int time ) {
		start_time = time;
	}
	
	public void setX(int x ) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	abstract public void draw( Canvas canvas);
}
