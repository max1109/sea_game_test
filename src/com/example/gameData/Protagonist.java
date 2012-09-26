package com.example.gameData;

public class Protagonist extends Role{

	int power = 0;
	int height = 0;
	
	public Protagonist(int x, int y, String name) {
		super(x, y, name);

	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight( int h) {
		height = h;
	}
}
