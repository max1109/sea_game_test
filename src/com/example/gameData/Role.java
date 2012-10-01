package com.example.gameData;

import android.graphics.Canvas;

public abstract class Role {
	int x = 0;
	int y = 0;
	String name ="";
	int score = 0;
	int time = 0;
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
		this.time = time ;
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	public void setHeight( int h) {
		height = h;
	}
	public void setWidth(int w) {
		width = w;
	}
	
	public int getStartTime() {
		return start_time;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public void setDead( boolean  d) {
		dead = d;
	}
	
	public void setStartTime( int time ) {
		start_time = time;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int s) {
		score = s;
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
		return x;
	}
	
	abstract public void draw( Canvas canvas);
}
