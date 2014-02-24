package com.whaix.planegamedemo;


import java.util.ArrayList;

import android.content.Context;

import android.media.MediaPlayer;


public class FightForEnemy extends Thread {
	ArrayList<Bullet> bt;
	ArrayList<Plane> pt;
	Plane plane;
	Bullet bullet;
	DrawView drawView;
	Context fContext;


	private MediaPlayer boomPlayer01,boomPlayer02,boomPlayer03;
	
	public FightForEnemy(Context fContext,ArrayList<Plane> pt,ArrayList<Bullet> bt,Plane plane,Bullet bullet,DrawView drawView){
		this.bt=bt;
		this.pt=pt;
		this.plane=plane;
		this.bullet=bullet;
		this.drawView=drawView;
		this.fContext=fContext;

		init();
	}
	
	private void init(){		
		boomPlayer01=MediaPlayer.create(fContext, R.raw.explosion01);		//加载音效
		boomPlayer02=MediaPlayer.create(fContext, R.raw.explosion02);	
		boomPlayer03=MediaPlayer.create(fContext, R.raw.explosion03);
		
	}
	public void run(){
		super.run();
		while(true){
			try{				
				Thread.sleep(15);		//改小一点，这样子弹看着很平滑
				/**发射子弹，这里必须要加这个sleep**/
				for (int i = 0; i < pt.size(); i++) {
					// 不同的飞机的大小不同
					if (null != pt.get(i)) {
						if (pt.get(i).getPlaneType() == 2) {
							for (int j = 0; j < bt.size(); j++) {
								if (null != bt.get(j)) {
									synchronized (this) {
									
									if (pt.get(i).getPlaneX() + 25 > bt.get(j).getBulletX()
											&& pt.get(i).getPlaneX() - 25 < bt.get(j).getBulletX()
											&& pt.get(i).getPlaneY() + 25 > bt.get(j).getBulletY()
											&& pt.get(i).getPlaneY() - 25 < bt.get(j).getBulletY()
											&& bt.get(j).getBulletType() == 1) {
										
										//	anim.stop();
										//	boomPlayer.stop();
										
											boomPlayer01.start();
											
									//	Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
									//		bombt.add(b);
											pt.remove(i);
											bt.remove(j);
											//com.example.planefight.Parameter.score+=1;
											//if(com.example.planefight.Parameter.voice){	开启声音
											//	com.example.planefight.Parameter.mediaplayer.start();
											//}
										}
									drawView.postInvalidate();
									}
								}
							}

						} else if (pt.get(i).getPlaneType() == 3) {
							for (int j = 0; j < bt.size(); j++) {
								if (null != bt.get(j)) {
									synchronized (this) {
									if (pt.get(i).getPlaneX() + 30 > bt.get(j).getBulletX()
											&& pt.get(i).getPlaneX() - 30 < bt.get(j).getBulletX()
											&& pt.get(i).getPlaneY() + 30 > bt.get(j).getBulletY()
											&& pt.get(i).getPlaneY() - 30 < bt.get(j).getBulletY()
											&& bt.get(j).getBulletType() == 1) {
									//	Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
									//		bombt.add(b);
										//	anim.stop();
										//	boomPlayer.stop();
										
											boomPlayer02.start();
											pt.remove(i);
											bt.remove(j);
									//		com.example.planefight.Parameter.score+=1;
									//		if(com.example.planefight.Parameter.voice){
									//			com.example.planefight.Parameter.mediaplayer.start();
									//		}
										}
										//gp.postInvalidate();
									}
								}
							}
						} else if (pt.get(i).getPlaneType() == 4) {
							for (int j = 0; j < bt.size(); j++) {
								if (null != bt.get(j)) {
									synchronized (this) {
									if (pt.get(i).getPlaneX() + 40 > bt.get(j)
											.getBulletX()
											&& pt.get(i).getPlaneX() - 40 < bt.get(j).getBulletX()
											&& pt.get(i).getPlaneY() + 40 > bt.get(j).getBulletY()
											&& pt.get(i).getPlaneY() - 40 < bt.get(j).getBulletY()
											&& bt.get(j).getBulletType() == 1) {
										//	anim.stop();
										//	boomPlayer.stop();
											
											boomPlayer03.start();
									//	Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
									//		bombt.add(b);
											pt.remove(i);
											bt.remove(j);
									//		com.example.planefight.Parameter.score+=1;
									//		if(com.example.planefight.Parameter.voice){
									//			com.example.planefight.Parameter.mediaplayer.start();
									//		}
										}
										
									}
								}
							}
						}
						drawView.postInvalidate();
					}

				}
				
				for (int i = 0; i < bt.size(); i++) {
					if (null != bt.get(i)) {
						if(bt.get(i).getBulletFlag()==1){		//绘制我机子弹
							if(bt.get(i).getBulletFlag()!=0){
								bt.get(i).setBulletY(bt.get(i).getBulletY()-2);		//设置子弹间距
							}
							if(bt.get(i).getBulletY()<0){
								bt.remove(i);
							}
						}else if(bt.get(i).getBulletFlag()==2){		//绘制敌机子弹
							if(bt.get(i).getBulletFlag()!=0){
								bt.get(i).setBulletY(bt.get(i).getBulletY()+2);		//设置子弹间距
							}
							if(bt.get(i).getBulletY()>850){
								bt.remove(i);
							}
						}
					}
					drawView.postInvalidate();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
