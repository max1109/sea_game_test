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
		int [] fish2 = { R.drawable.fish2_1 , R.drawable.fish2_2};
		int [] fish3 = { R.drawable.fish3_1 };
		int [] fish4 = { R.drawable.fish4_1 };
		if ( 0 == stage ) {
			
			c.addRole(new Fish( Util.DEVICE_WIDTH, 270, "s_fish", context, 0 ,20, 20 ,  fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 330, "a_fish", context, 4, 5 , 8 ));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 370, "b_fish", context, 9, 4 ,1 , fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 12, 70, 2,  fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 230, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 510, "c_fish", context, 22,70, 2 ,  fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 630, "c_fish", context, 30,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 35,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 290, "c_fish", context, 38,70, 2 , fish4));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 40,70, 2 , fish3));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 46,70, 2 , fish3));
			
		} else if ( 1 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 520, "a_fish", context, 6, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 230, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 600, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 300, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 590, "c_fish", context, 22,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 370, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 230, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 470, "c_fish", context, 33,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 500, "c_fish", context, 40,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 400, "c_fish", context, 45,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 50 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 520, "a_fish", context, 54, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 322, "s_fish", context, 59 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 420, "a_fish", context, 62, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 64 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 69, 5 , 8, fish2));
		} else if ( 2 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 6, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 430, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 200, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 600, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 590, "c_fish", context, 22,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 270, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 430, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 370, "c_fish", context, 33,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 500, "c_fish", context, 40,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 300, "c_fish", context, 45,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 50 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 54, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 522, "s_fish", context, 59 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 420, "a_fish", context, 62, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 322, "s_fish", context, 64 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 520, "a_fish", context, 69, 5 , 8, fish2));
		} else if ( 3 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 422, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 390, "a_fish", context, 6, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 420, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 500, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 530, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 600, "c_fish", context, 22,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 570, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 430, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 370, "c_fish", context, 33,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 510, "c_fish", context, 40,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 300, "c_fish", context, 45,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 282, "s_fish", context, 50 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 54, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 422, "s_fish", context, 59 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "a_fish", context, 62, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 522, "s_fish", context, 64 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 69, 5 , 8, fish2));
		} else if ( 4 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "a_fish", context, 7, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 290, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 350, "c_fish", context, 22,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 370, "c_fish", context, 33,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 510, "c_fish", context, 40,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 45,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 262, "s_fish", context, 50 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 620, "a_fish", context, 54, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 422, "s_fish", context, 59 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "a_fish", context, 62, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 64 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "a_fish", context, 69, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "a_fish", context, 73, 5 , 8, fish2));
		} else if ( 5 == stage) {
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "s_fish", context, 2 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 222, "a_fish", context, 7, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "b_fish", context, 10, 4 ,1));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 12, 70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 240, "c_fish", context, 18,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 350, "c_fish", context, 22,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 28,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 380, "c_fish", context, 30,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 230, "c_fish", context, 33,70, 2, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 310, "c_fish", context, 40,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "c_fish", context, 45,70, 2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 262, "s_fish", context, 50 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "a_fish", context, 54, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 422, "s_fish", context, 59 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "a_fish", context, 62, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 272, "s_fish", context, 64 ,20, 20));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 320, "a_fish", context, 69, 5 , 8, fish2));
			c.addRole(new Fish( Util.DEVICE_WIDTH, 220, "a_fish", context, 73, 5 , 8, fish2));
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
		int bgf1[] = {R.drawable.background_fish_1};
		int bgf2[] = {R.drawable.background_fish_3};
//		int bgf3[] = {R.drawable.background_grass_1};
//		int bgf4[] = {R.drawable.background_grass_2};
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.background_3200_752);
		
		List<Role> item = new ArrayList<Role>();
		if ( 0 == stage ) {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 300, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 400, "fish bg", context, 15, bgf1));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 430, "fish bg", context, 20, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 230, "fish bg", context, 35, bgf2));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 570, "fish bg", context, 60, bgf));
		} else if ( 1 == stage ) {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 280, "fish bg", context, 5, bgf1));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 420, "fish bg", context, 10, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 530, "fish bg", context, 18, bgf1));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 530, "fish bg", context, 18, bgf2));
		} else if ( 2 == stage ) {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 300, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 400, "fish bg", context, 15, bgf2));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 430, "fish bg", context, 20, bgf1));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 230, "fish bg", context, 35, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 570, "fish bg", context, 60, bgf1));
		} else {
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 300, "fish bg", context, 5, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 400, "fish bg", context, 15, bgf2));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 430, "fish bg", context, 20, bgf1));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 230, "fish bg", context, 35, bgf));
			item.add( new BackgroundObject( Util.DEVICE_WIDTH, 570, "fish bg", context, 60, bgf1));
		}
		Background b = new Background(bmp , item);
		return b ;
		
	}
}
