package com.example.gameData;

public class Location {
	float x = 0;
	float y = 0;

	public Location(int x, int y ) {
		this.x = x;
		this.y = y;
	}
	public void setX(float f ) {
		this.x = f;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
