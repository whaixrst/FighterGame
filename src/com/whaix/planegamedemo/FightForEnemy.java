/*
 * V0.7.9.3������ײ���Ѫ��ģʽ���ж���Ѫ������Ҫ�����ӵ�����ײ��Źҵ�
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
		//ArrayList������ֱ�Ӵ���ʱ�����Array��û�и���ʱֱ�ӷֱ�����Ա�������а�ȫ��������Ҫֱ����this.bt=bt;����Ҫ�޸Ĵ������¡�
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
		boomPlayer01=MediaPlayer.create(fContext, R.raw.explosion01);		//������Ч
		boomPlayer02=MediaPlayer.create(fContext, R.raw.explosion02);	
		boomPlayer03=MediaPlayer.create(fContext, R.raw.explosion03);
		
	}
	
	public void run(){
		super.run();
		while(true){
			try{				
				Thread.sleep(15);		//��Сһ�㣬�����ӵ����ź�ƽ��
				/**�����ӵ����������Ҫ�����sleep**/
				for (int i = 0; i < pt.size(); i++) {							//�����ɻ�����
					if (null != pt.get(i)) {
						if (pt.get(i).getPlaneType() == 2) {
							for (int j = 0; j < bt.size(); j++) {				//�����ӵ�����
								if (null != bt.get(j)) {
									synchronized (this) {									
										if (pt.get(i).getPlaneX() + 25 > bt.get(j).getBulletX()		// ��ͬ�ķɻ��Ĵ�С��ͬ���ӵ���ɻ���ײ
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
														bt.remove(j);		//V0.7.9.4 �������Ҫ�ӣ���Ȼ�������ʱ�����һ�������ӵ�����ʧ
														parameter.Score+=100;
													//if(com.example.planefight.Parameter.voice){	��������
													//	com.example.planefight.Parameter.mediaplayer.start();
													//}
														String totelScore=Long.toString(parameter.Score);
														tvScore.setText(totelScore);
													}
													bt.remove(j);		//Ϊʲô������ʱ��ִ�������䣬����ֱ������synchronized (this)���
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
													parameter.Score+=200;
										//		if(com.example.planefight.Parameter.voice){
										//			com.example.planefight.Parameter.mediaplayer.start();
										//		}
													bt.remove(j);
													String totelScore=Long.toString(parameter.Score);
													tvScore.setText(totelScore);
												}
												bt.remove(j);		//�����ӵ���ʧ
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
												pt.get(i).setPlaneShot();		//����һ�Σ����á�
												if(pt.get(i).getPlaneShot()==pt.get(i).getPlaneBlood()){		//�е�������Ѫ��ʱ������
													boomPlayer03.start();  
													Bomb b = new Bomb(pt.get(i).getPlaneX(), pt.get(i).getPlaneY(), 10);
													bombt.add(b);
													pt.remove(i);	
													bt.remove(j);
													parameter.Score+=500;
										//		if(com.example.planefight.Parameter.voice){
										//			com.example.planefight.Parameter.mediaplayer.start();
										//		}
													String totelScore=Long.toString(parameter.Score);
													tvScore.setText(totelScore);
												}
												bt.remove(j);		//�����ӵ���ʧ
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
						if(bt.get(i).getBulletFlag()==1){		//�����һ��ӵ�
							if(bt.get(i).getBulletFlag()!=0){
								bt.get(i).setBulletY(bt.get(i).getBulletY()-3);		//�����ӵ����
							}
							if(bt.get(i).getBulletY()<0){		//���һ��������ӵ�Խ��Y0�ױ��ǣ�����
								bt.remove(i);
							}
						}else if(bt.get(i).getBulletFlag()==2){		//���Ƶл��ӵ�
							if(bt.get(i).getBulletFlag()!=0){
								bt.get(i).setBulletY(bt.get(i).getBulletY()+3);		//�����ӵ����
							}
							if(bt.get(i).getBulletY()>850){		//���л��������ӵ�Խ��Y854�ױ��ǣ�����
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
