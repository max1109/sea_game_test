package com.example.gameData;

public abstract class Role {
	int x = 0;
	int y = 0;
	String name ="";
	
	public Role(int x , int y ,String name ) {
		this.x = x;
		this.y = y;
		this.name = name ;
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
}
