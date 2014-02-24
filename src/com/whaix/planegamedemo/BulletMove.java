/*
 * 这个类专用于产生子弹密度，总共可以创建60个子弹对象。
 */
package com.whaix.planegamedemo;

import java.util.ArrayList;

public class BulletMove extends Thread {
	ArrayList<Bullet> bullet=new ArrayList<Bullet>();
	DrawView drawView;

	public BulletMove(DrawView drawView,ArrayList<Bullet> bullet){
		this.bullet = bullet;
		this.drawView=drawView;		
	}	
	public void run(){
		super.run();
		while (true) {
			try {
				Thread.sleep(100);		//每100ms发射一颗
				for (int i = 0; i < bullet.size(); ++i) {
					if (null != bullet.get(i)) {
						/**限制了产生子弹的总数为60颗**/
						bullet.get(i).setBulletDis(bullet.get(i).getBulletDis() + 1);
						if (bullet.get(i).getBulletDis() > 70) {
							bullet.remove(i);
						}	
						/**这段放在这里要出现IndexOutOfBoundsException异常，待会儿放到另外一个类里面**/
				/*	synchronized (this) {		
							if(bullet.get(i).getBulletFlag()==1){
								if(bullet.get(i).getBulletFlag()!=0){
									bullet.get(i).setBulletY(bullet.get(i).getBulletY()-12);		//设置子弹间距
								}
								if(bullet.get(i).getBulletY()<0){
									bullet.remove(i);
								}
							}
						}*/
					}
				//drawView.postInvalidate();
					
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
