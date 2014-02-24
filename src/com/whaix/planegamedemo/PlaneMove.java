package com.whaix.planegamedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class PlaneMove extends Thread {
	Plane plane;
	Context context;
	Canvas canvas;
	
	public PlaneMove(Context context,Canvas canvas,Plane plane){
		this.plane = plane;
		this.canvas = canvas;
		this.context = context;
	}
	
	public void drawPlane(float x,float y){
		Paint paint=new Paint();
		Bitmap bit = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.plane);
		RectF rectf = new RectF(x, y, x + 50, y + 50);
		canvas.drawBitmap(bit, null, rectf, paint);
	}
	
	public void run(){
		super.run();
		while (true) {
			if (plane.PlaneFlag == 1) {

			} else if (plane.PlaneFlag == 2) {

			}
		}
	}
}
