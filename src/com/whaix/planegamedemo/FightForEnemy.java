/*
 * V0.7.9.3新增碰撞检测血量模式，有多少血量就需要多少子弹来碰撞后才挂掉
 */
package com.whaix.planegamedemo;

import java.util.ArrayList;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class FightForEnemy extends Thread {
	ArrayList<Bullet> bt;
	ArrayList<Plane> pt;
	ArrayList<Bomb> bombt;
  
    private DrawView drawView;
    private Context fContext;
    private TextView tvScore;
	private MediaPlayer boomPlayer01,boomPlayer02,boomPlayer03;

	public FightForEnemy(Context fContext,ArrayList<Plane> prePt,ArrayList<Bullet> preBt,ArrayList<Bomb> preBombt,DrawView drawView,TextView tvScore){
		//ArrayList当参数直接传递时，如果Array在没有复制时直接分贝给成员变量会有安全隐患。不要直接用this.bt=bt;所以要修改代码如下。
	/*	if(preBt==null){
			this.bt=new ArrayList<Bullet>();
		} else{
			this.bt=arrayscopy(preBt, preBt.length);
		}*/
		this.bt=preBt;
		this.pt=prePt;
		this.drawView=drawView;
		this.fContext=fContext;
		this.bombt=preBombt;
		this.tvScore=tvScore;
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
				for (int i = 0; i < pt.size(); i++) {							//遍历飞机数组
					if (null != pt.get(i)) {
						if (pt.get(i).getPlaneType() == 2) {
							for (int j = 0; j < bt.size(); j++) {				//遍历子弹数组
								if (null != bt.get(j)) {
									synchronized (this) {									
										if (pt.get(i).getPlaneX() + 25 > bt.get(j).getBulletX()		// 不同的飞机的大小不同，子弹与飞机碰撞
												&& pt.get(i).getPlaneX() - 25 < bt.get(j).getBulletX()
												&& pt.get(i).getPlaneY() + 25 > bt.get(j).getBulletY()
												&& pt.get(i).getPlaneY() - 25 < bt.get(j).getBulletY()
												&& bt.get(j).getBulletType() == 1) {	
													pt.get(i).setPlaneShot();
													if(pt.get(i).getPlaneShot()==pt.get(i).getPlaneBlood()){
													    boomPlayer01.start();											
														Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
														bombt.add(b);
														pt.remove(i);	
														bt.remove(j);		//V0.7.9.4 这里必须要加，不然最后死亡时，最后一发击中子弹不消失
														parameter.Score+=1000;
													//if(com.example.planefight.Parameter.voice){	开启声音
													//	com.example.planefight.Parameter.mediaplayer.start();
													//}
														String totelScore=Integer.toString(parameter.Score);
														tvScore.setText(totelScore);
													}
													bt.remove(j);		//为什么死亡的时候不执行这个语句，而是直接跳到synchronized (this)这里？
											}
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
												pt.get(i).setPlaneShot();
												if(pt.get(i).getPlaneShot()==pt.get(i).getPlaneBlood()){
													Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
													bombt.add(b);
													boomPlayer02.start();
													pt.remove(i);													
													parameter.Score+=2000;
										//		if(com.example.planefight.Parameter.voice){
										//			com.example.planefight.Parameter.mediaplayer.start();
										//		}
													bt.remove(j);
													String totelScore=Integer.toString(parameter.Score);
													tvScore.setText(totelScore);
												}
												bt.remove(j);		//击中子弹消失
										}
									}
								}
							}
						} else if (pt.get(i).getPlaneType() == 4) {					
							for (int j = 0; j < bt.size(); j++) {
								if (null != bt.get(j)) {
									synchronized (this) {
									if (pt.get(i).getPlaneX() + 40 > bt.get(j).getBulletX()
											&& pt.get(i).getPlaneX() - 40 < bt.get(j).getBulletX()
											&& pt.get(i).getPlaneY() + 40 > bt.get(j).getBulletY()
											&& pt.get(i).getPlaneY() - 40 < bt.get(j).getBulletY()
											&& bt.get(j).getBulletType() == 1) {
												pt.get(i).setPlaneShot();		//击中一次，调用。
												if(pt.get(i).getPlaneShot()==pt.get(i).getPlaneBlood()){		//中弹数等于血量时，死亡
													boomPlayer03.start();  
													Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
													bombt.add(b);
													pt.remove(i);	
													bt.remove(j);
													parameter.Score+=5000;
										//		if(com.example.planefight.Parameter.voice){
										//			com.example.planefight.Parameter.mediaplayer.start();
										//		}
													String totelScore=Integer.toString(parameter.Score);
													tvScore.setText(totelScore);
												}
												bt.remove(j);		//击中子弹消失
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
								bt.get(i).setBulletY(bt.get(i).getBulletY()-3);		//设置子弹间距
							}
							if(bt.get(i).getBulletY()<0){		//当我机所发射子弹越过Y0底边是，销毁
								bt.remove(i);
							}
						}else if(bt.get(i).getBulletFlag()==2){		//绘制敌机子弹
							if(bt.get(i).getBulletFlag()!=0){
								bt.get(i).setBulletY(bt.get(i).getBulletY()+3);		//设置子弹间距
							}
							if(bt.get(i).getBulletY()>850){		//当敌机所发射子弹越过Y854底边是，销毁
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
