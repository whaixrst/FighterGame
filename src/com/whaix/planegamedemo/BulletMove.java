/*
 * �����ר���ڲ����ӵ��ܶȣ��ܹ����Դ���60���ӵ�����
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
				Thread.sleep(100);		//ÿ100ms����һ��
				for (int i = 0; i < bullet.size(); ++i) {
					if (null != bullet.get(i)) {
						/**�����˲����ӵ�������Ϊ60��**/
						bullet.get(i).setBulletDis(bullet.get(i).getBulletDis() + 1);
						if (bullet.get(i).getBulletDis() > 70) {
							bullet.remove(i);
						}	
						/**��η�������Ҫ����IndexOutOfBoundsException�쳣��������ŵ�����һ��������**/
				/*	synchronized (this) {		
							if(bullet.get(i).getBulletFlag()==1){
								if(bullet.get(i).getBulletFlag()!=0){
									bullet.get(i).setBulletY(bullet.get(i).getBulletY()-12);		//�����ӵ����
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
