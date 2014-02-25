package com.whaix.planegamedemo;

import java.util.ArrayList;

public class ProductEnemyPlane extends Thread {
	ArrayList<Plane> pt=new ArrayList<Plane>();
	ArrayList<Bullet> bt=new ArrayList<Bullet>();
	DrawView drawView;
	public ProductEnemyPlane(DrawView drawView,ArrayList<Plane> pt,ArrayList<Bullet> bt){
		this.pt=pt;
		this.bt=bt;
		this.drawView=drawView;
	}
	public void run(){
		super.run();
		while(true){			
			try{
				Thread.sleep(parameter.fighterProductTime);
				float x=(float)(Math.random()*parameter.screenWidth);		//随机产生敌机位置,都是从底边出来，所以Y都是0，只需要随机产生x值就可以
				float y=(float)(Math.random()*parameter.screenHeight);
				int rand=(int)(Math.random()*100);		//随机产生敌机型号
				Plane plane;
				Bullet bullet;
				if(rand<=60){
					plane=new Plane(x,0,2,2);		//plane(x坐标,y坐标,“敌我机”，“飞机图片”)
					bullet=new Bullet(x,y,2,2,50);		
				}
				else if(rand>60&&rand<80){
					plane=new Plane(x,0,2,3);
					bullet=new Bullet(x,y,2,3,50);
				}
				else{
					plane=new Plane(x,0,2,4);
					bullet=new Bullet(x,y,2,4,10);
				}
				
				bt.add(bullet);
				pt.add(plane);
			}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
