package com.whaix.planegamedemo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.os.Handler;
import android.view.View;

public class DrawView extends View {
	ArrayList<Bullet> bt=new ArrayList<Bullet>();
	ArrayList<Plane> pt=new ArrayList<Plane>();	
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	ArrayList<Bomb> bigBombs = new ArrayList<Bomb>();
	Context dcontext;
	//�߳�
	Plane plane;
	PlaneMove planeMove;
	Bullet bullet;
	BulletMove bulletMove;
	ProductBullet productBullet;
	MovePlanes threadMovePlane;
	FightForEnemy fightForEnemy;
	ProductEnemyPlane productEnemyPlane;
	EnemyPlaneMove enemyPlaneMove;
	BombMove bombMove;
	
	final int BACK_HEIGHT=1700;		//��¼����λͼ��ʵ�ʸ߶� 
	private Bitmap back;
	final int WIDTH=480;
	final int HEIGHT=854;
	private int startY=BACK_HEIGHT-HEIGHT;
	
	float PlaneCurrentX=0;		//��ǰ�ɻ����꣬����û�õ�
	float PlaneCurrentY=0;
	float disxv=0;
	float disyv=0;
	float v = 1;
	static  boolean fail =false;		//�һ��Ƿ񱻻���

	
	
	Bitmap bitMyPlane = BitmapFactory.decodeResource(getResources(),
			R.drawable.plane);
	Bitmap bitMyPlaneBullet = BitmapFactory.decodeResource(getResources(),
			R.drawable.planebullet);
	
	/**���Ƶл��ӵ�**/
	Bitmap bitBullet1 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemybullet1);
	Bitmap bitBullet2 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemybullet2);
	Bitmap bitBullet3 = BitmapFactory.decodeResource(getResources(),
			R.drawable.bossbullet);
	
	/**���Ƶл�**/
	Bitmap bitPlane1 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyplane1);
	Bitmap bitPlane2 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyplane2);
	Bitmap bitPlaneBoss = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyboss);
	
	/**���Ʊ���**/
//	Bitmap bitBack=Bitmap.createBitmap(back,0,startY,WIDTH,HEIGHT);
	
	/*
	 * ���췽��
	 */
	public DrawView(Context context,Plane plane){
		super(context);		
		this.plane=plane;
		this.dcontext=context;
		PlaneCurrentX=plane.getPlaneX();
		PlaneCurrentY=plane.getPlaneY();
		//��ȡ������ͼ
	
		init();
	}
	
	/*
	 * ��ʼ�������ܴ�����
	 */
	private void init(){	
		back=BitmapFactory.decodeResource(dcontext.getResources(), R.drawable.back_img);
		final Handler handler=new Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x123){
					//���¿�ʼ�ƶ�
					if(startY<=0){
						startY=BACK_HEIGHT-HEIGHT;
					}else{
						startY-=3;
					}
				}
				postInvalidate();
			}
		};
		new Timer().schedule(new TimerTask(){
			public void run(){
				handler.sendEmptyMessage(0x123);
			}
		},0,100);
				
		
		threadMovePlane=new MovePlanes();
		productBullet=new ProductBullet();
		bulletMove=new BulletMove(this,bt);
		fightForEnemy=new FightForEnemy(dcontext,pt,bt,bombs,plane,bullet,this);
		productEnemyPlane=new ProductEnemyPlane(this,pt,bt);
		enemyPlaneMove=new EnemyPlaneMove(this,pt);
		bombMove=new BombMove(bombs,this);
		
		threadMovePlane.start();
		productBullet.start();
		bulletMove.start();
		fightForEnemy.start();
		productEnemyPlane.start();
		enemyPlaneMove.start();
		bombMove.start();
	}
	
	/*
	 * ������Ļ��ȡ��ǰ����
	 */
	public void setValue(float x,float y){
		this.PlaneCurrentX=x;
		this.PlaneCurrentY=y;
		/**У��**/
		float disx = Math.abs(plane.getPlaneX() - PlaneCurrentX);
		float disy = Math.abs(plane.getPlaneY() - PlaneCurrentY);
		float dis = (float) Math.sqrt(disx * disx + disy * disy);
		float dist = dis / v;
		this.disxv = disx / dist;
		this.disyv = disy / dist;
	}
		
	/*
	 * onDraw�ص�����
	 */
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);	
				
		Bitmap bitmap123=Bitmap.createBitmap(back,0,startY,WIDTH,HEIGHT);
		canvas.drawBitmap(bitmap123, 0, 0,null);
		Paint paint=new Paint();
		
		/**������ͼ**/
		
//		canvas.drawBitmap(bitBack, 0,0, paint);
		/**�ɻ�δ������ʱ�������Ʒɻ�ͼƬ**/
		if(fail==false){	
			RectF rectf = new RectF(plane.getPlaneX()-50, 
						plane.getPlaneY()-50,
						plane.getPlaneX()+50,
						plane.getPlaneY()+50);
			canvas.drawBitmap(bitMyPlane, null, rectf, paint);
		}/**�ɻ�������ʱ�����е�һЩ����**/
		else{
			paint.setTextSize(50);
			paint.setColor(Color.RED);
			canvas.drawText("You Failed !", 110, 350, paint);
			canvas.save();
			threadMovePlane.interrupt();
			productBullet.interrupt();		//�жϲ����ӵ����߳�					
			bulletMove.interrupt();
			fightForEnemy.interrupt();
			productEnemyPlane.interrupt();
			enemyPlaneMove.interrupt();
			
			bt.clear();		//�����Ļ�ϵ��ӵ�
			pt.clear();		//�����Ļ�ϵķɻ�
		}
		/**�����ӵ�**/	
		//�����һ������ӵ�
		for(int i=0;i<bt.size();++i){	
			if (bt.get(i).getBulletType() == 1&&fail==false) {	
				RectF rectf11 = new RectF(bt.get(i).getBulletX()-5,
								bt.get(i).getBulletY()-35,
								bt.get(i).getBulletX()+5, 
								bt.get(i).getBulletY()-25);
				canvas.drawBitmap(bitMyPlaneBullet, null, rectf11, paint);			
			} 	
			this.postInvalidate();
		}
		//���Ƶл������ӵ�
		for (int i = 0; i < bt.size(); i++) {
			RectF rectf2;
			if (bt.get(i).getBulletType() == 2&&fail==false) {
				rectf2 = new RectF(bt.get(i).getBulletX()-5,
							bt.get(i).getBulletY()-35, 
							bt.get(i).getBulletX()+5, 
							bt.get(i).getBulletY()-25);
				canvas.drawBitmap(bitBullet1, null, rectf2, paint);
			} 
			else if (bt.get(i).getBulletType() == 3) {
				rectf2 = new RectF(bt.get(i).getBulletX() - 3,
							bt.get(i).getBulletY() - 33, 
							bt.get(i).getBulletX() + 3, 
							bt.get(i).getBulletY() - 27);
				canvas.drawBitmap(bitBullet2, null, rectf2, paint);
			}
			else if (bt.get(i).getBulletType() == 4) {
				rectf2 = new RectF(bt.get(i).getBulletX() - 8,
							bt.get(i).getBulletY() - 38,
							bt.get(i).getBulletX() + 8, 
							bt.get(i).getBulletY() - 22);
				canvas.drawBitmap(bitBullet3, null, rectf2, paint);
			}
		}
		
		/**���Ƶл�**/
		for (int i = 0; i < pt.size(); i++) {
			RectF rectf3;
			if (pt.get(i).getPlaneType() == 2) {
				rectf3 = new RectF(pt.get(i).getPlaneX() - 25,
								pt.get(i).getPlaneY() - 25, 
								pt.get(i).getPlaneX() + 25, 
								pt.get(i).getPlaneY() + 25);
				canvas.drawBitmap(bitPlane1, null, rectf3, paint);
			} 
			else if (pt.get(i).getPlaneType() == 3) {
				rectf3 = new RectF(pt.get(i).getPlaneX() - 30,
								pt.get(i).getPlaneY() - 30, 
								pt.get(i).getPlaneX() + 30,
								pt.get(i).getPlaneY() + 30);
				canvas.drawBitmap(bitPlane2, null, rectf3, paint);
			} 
			else if (pt.get(i).getPlaneType() == 4) {
				rectf3 = new RectF(pt.get(i).getPlaneX() - 40,
								pt.get(i).getPlaneY() - 40, 
								pt.get(i).getPlaneX() + 40, 
								pt.get(i).getPlaneY() + 40);
				canvas.drawBitmap(bitPlaneBoss, null, rectf3, paint);
			}
		}
		
		/**���Ʊ�ը**/
		
		Bitmap explosion = BitmapFactory.decodeResource(getResources(),
				R.drawable.explosion);
		for (int i = 0; i < bombs.size(); i++) {
			RectF rectf4 = new RectF(bombs.get(i).getX()
					- bombs.get(i).getBombDis(), bombs.get(i).getY()
					- bombs.get(i).getBombDis(), bombs.get(i).getX()
					+ bombs.get(i).getBombDis(), bombs.get(i).getY()
					+ bombs.get(i).getBombDis());
			canvas.drawBitmap(explosion, null, rectf4, paint);
		}
		for (int i = 0; i < bigBombs.size(); i++) {
			RectF rectf5 = new RectF(bigBombs.get(i).getX()
					- bigBombs.get(i).getBombDis(), bigBombs.get(i).getY()
					- bigBombs.get(i).getBombDis(), bigBombs.get(i).getX()
					+ bigBombs.get(i).getBombDis(), bigBombs.get(i).getY()
					+ bigBombs.get(i).getBombDis());
			canvas.drawBitmap(explosion, null, rectf5, paint);
		}	
		
	
	}

	public class ProductBullet extends Thread {
		public void run() {
			super.run();		
			while (true) {			
				try {
					Thread.sleep(400);		//���Ʋ����ӵ��ٶȣ�̫���˾Ϳ��������ƶ���400ms�ȽϺ���
					Bullet bullet = new Bullet(plane.getPlaneX(), plane.getPlaneY()-15, 1, 1,10);
					bt.add(bullet);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class MovePlanes extends Thread {
		@Override
		public void run() {
			super.run();
			while (true) {
				try {
					Thread.sleep(3);		//�ƶ��ɻ�,ƽ���ƶ�.sleep�������ٶ�
					if ((plane.getPlaneX() >= PlaneCurrentX)) {
						plane.setPlaneX(plane.getPlaneX() - disxv);
					} else if ((plane.getPlaneX() < PlaneCurrentX)) {
						plane.setPlaneX(plane.getPlaneX() + disxv);
					}
					if ((plane.getPlaneY() >= PlaneCurrentY)) {
						plane.setPlaneY(plane.getPlaneY() - disyv);
					} else if ((plane.getPlaneY() < PlaneCurrentY)) {
						plane.setPlaneY(plane.getPlaneY() + disyv);
					}
					postInvalidate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

