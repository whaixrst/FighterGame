package com.whaix.planegamedemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
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
	//	setContentView(R.layout.activity_main);
	//	root=(FrameLayout)findViewById(R.id.root);
		
		FrameLayout paramLayout=new FrameLayout(this);
		FrameLayout.LayoutParams param=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		FrameLayout.LayoutParams viewParam=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		FrameLayout.LayoutParams scoreParam=new FrameLayout.LayoutParams(50, ViewGroup.LayoutParams.WRAP_CONTENT,Gravity.TOP|Gravity.CENTER);
		metric=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		parameter.Width=metric.widthPixels;		//屏幕高宽（像素）
		parameter.Height=metric.heightPixels;
		Float w =new Float(parameter.Width);
		Float h=new Float(parameter.Height);
		width=w.intValue();
		height=h.intValue();
		
		tvName=new TextView(this);
		tvScore=new TextView(this);
		tvName.setText("分数：");
		tvName.setTextColor(Color.WHITE);
		TextPaint tvNameStrong = tvName.getPaint(); 		//加粗
		tvNameStrong.setFakeBoldText(true); 
		
		String totelScore=Integer.toString(parameter.Score);
		tvScore.setText(totelScore);
		tvScore.setTextColor(Color.WHITE);
		tvScore.setGravity(Gravity.LEFT);
		TextPaint tvScoreStrong = tvScore.getPaint(); 		//加粗
		tvScoreStrong.setFakeBoldText(true); 
	//	tvScore.setPadding(30, 10, width-80, height-20);
	//	plane = new Plane(parameter.screenWidth / 2 - 25, parameter.screenHeight - 85, 1,1);
		/**为了动态改变tvScore，要在改变分数的时候就改变tvScore，所以先传到DrawView,再传到FightForEnemy,我真是个天才**/
		drawView = new DrawView(this,tvScore);
		
		drawView.setMinimumHeight(height);
		drawView.setMinimumWidth(width);
		paramLayout.addView(drawView,param);
		paramLayout.addView(tvName, viewParam);
		paramLayout.addView(tvScore,scoreParam);
		super.setContentView(paramLayout,param);
	//	root.addView(drawView);
	//	root.addView(tvName);
	//	tvScore=(TextView)findViewById(R.id.showScore);
		/**随机背景**/
	/*	int rand = (int) (Math.random() * 10);
		if(rand<6){		
			drawView.setBackgroundResource(R.drawable.rank_backgroud);
			
		}else if(rand>=6){
			drawView.setBackgroundResource(R.drawable.backgroud);			
		}
*/
	//	setContentView(drawView);
		
		
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
