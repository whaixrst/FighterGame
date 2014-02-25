package com.whaix.planegamedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;

public class MainActivity extends Activity {
	Plane plane=null;
	DrawView drawView=null;	
	DisplayMetrics metric;
	private float width=0;
	private float height=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		metric=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		width=metric.widthPixels;		//ÆÁÄ»¸ß¿í£¨ÏñËØ£©
		height=metric.heightPixels;
		
		plane = new Plane(parameter.screenWidth / 2 - 25, parameter.screenHeight - 85, 1,1);
		drawView = new DrawView(MainActivity.this, plane);
		
		/**Ëæ»ú±³¾°**/
	/*	int rand = (int) (Math.random() * 10);
		if(rand<6){		
			drawView.setBackgroundResource(R.drawable.rank_backgroud);
			
		}else if(rand>=6){
			drawView.setBackgroundResource(R.drawable.backgroud);			
		}
*/
		setContentView(drawView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		drawView.setValue(x,y);
		
		return super.onTouchEvent(event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
