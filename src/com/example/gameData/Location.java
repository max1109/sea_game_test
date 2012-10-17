package com.example.gameData;

public class Location {
	int x = 0;
	int y = 0;

	public Location(int x, int y ) {
		this.x = x;
		this.y = y;
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
}
