package com.example.gameData;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.sea_game_testing.R;
import com.example.sea_game_testing.util.Util;


public class StageList {
	Context context = null;
	int stage = 0;
	
	public StageList( Context context  ,int stage ) {
		this.context = context;
		this.stage = stage;
	}
	public Stage getStage (  ) {
		
		Stage c = new Stage();
		if ( 0 == stage ) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 270, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 300, "a_fish", context, 0, 5 , 8));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "b_fish", context, 0, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 380, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 310, "c_fish", context, 22,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 590, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 280, "c_fish", context, 30,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 290, "c_fish", context, 33,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 300, "c_fish", context, 40,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 50,70, 2));
		} else if ( 1 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 520, "a_fish", context, 6, 5 , 8));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 410, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 380, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 280, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 430, "c_fish", context, 22,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 270, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 330, "c_fish", context, 33,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 350, "c_fish", context, 40,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 50,70, 2));
		} else {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 6, 5 , 8));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 22,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 330, "c_fish", context, 33,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 350, "c_fish", context, 40,70, 2));
//			c.addRole(new Fish( Util.DEVICE_WIDTH, 622, "c_fish", context, 50,70, 2));
		}
		return c;
	}
	
	public Background getBackground() {
		int bgf[] = {R.drawable.background_fish_2};
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.background_3200_752);
		List<Role> item = new ArrayList<Role>();
		if ( 1 == stage ) {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 300, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 400, "fish bg", context, 15, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 430, "fish bg", context, 20, bgf));
		} else if ( 2 == stage ) {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 280, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 620, "fish bg", context, 7, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 530, "fish bg", context, 18, bgf));
		} else {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 280, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 620, "fish bg", context, 7, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 530, "fish bg", context, 18, bgf));
		}
		Background b = new Background(bmp , item);
		return b ;
		
	}
}
