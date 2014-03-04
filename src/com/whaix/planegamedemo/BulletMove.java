/*
 * 这个类专用于产生子弹密度，总共可以创建60个子弹对象。
 * 把BombMove类合并进来，少开一条线程
 */
package com.whaix.planegamedemo;

import java.util.ArrayList;

public class BulletMove extends Thread {
	ArrayList<Bullet> bullet=new ArrayList<Bullet>();
	ArrayList<Bomb> bombt=new ArrayList<Bomb>();
	DrawView drawView;

	public BulletMove(DrawView drawView,ArrayList<Bullet> bullet,ArrayList<Bomb> bombt){
		this.bullet = bullet;
		this.drawView=drawView;	
		this.bombt=bombt;
	}	
	public void run(){
		super.run();
		while (true) {
			try {
				Thread.sleep(120);		//每100ms发射一颗
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
				for (int i = 0; i < bombt.size(); i++) {		//爆炸效果移动,就是一张图，从小变到大
					if (null != bombt.get(i)) {
						bombt.get(i).setBombDis(bombt.get(i).getBombDis() + 1);
						if (bombt.get(i).getBombDis() > 30) {
							bombt.remove(i);
						}
						bombt.get(i).setY(bombt.get(i).getY()+5);
					}
					drawView.postInvalidate();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
