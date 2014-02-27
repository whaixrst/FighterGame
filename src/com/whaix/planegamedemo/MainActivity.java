package com.whaix.planegamedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	Plane plane=null;
	DrawView drawView=null;	
	DisplayMetrics metric;
	private int width=0;
	private int height=0;
	TextView tvName,tvScore;
	FrameLayout root;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);			
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_main);
		root=(FrameLayout)findViewById(R.id.root);
		
		
		metric=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		parameter.Width=metric.widthPixels;		//ÆÁÄ»¸ß¿í£¨ÏñËØ£©
		parameter.Height=metric.heightPixels;
		Float w =new Float(parameter.Width);
		Float h=new Float(parameter.Height);
		width=w.intValue();
		height=h.intValue();
		
		plane = new Plane(parameter.screenWidth / 2 - 25, parameter.screenHeight - 85, 1,1);
		drawView = new DrawView(MainActivity.this, plane);
		
		drawView.setMinimumHeight(height);
		drawView.setMinimumWidth(width);
		root.addView(drawView);
	//	tvScore=(TextView)findViewById(R.id.showScore);
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
