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
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class DrawView extends View {
	ArrayList<Bullet> bt=new ArrayList<Bullet>();
	ArrayList<Plane> pt=new ArrayList<Plane>();	
	ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	//ArrayList<Bomb> bigBombs = new ArrayList<Bomb>();
	Context dcontext;
	//线程
	Plane plane;
//	PlaneMove planeMove;
	Bullet bullet;
	BulletMove bulletMove;
	ProductBullet productBullet;
	MoveMyPlanes threadMoveMyPlane;
	FightForEnemy fightForEnemy;
	ProductEnemyPlane productEnemyPlane;
	EnemyPlaneMove enemyPlaneMove;
	TextView tvScore;
	
	final int BACK_HEIGHT=1700;		//记录背景位图的实际高度 
	private Bitmap back;
	final int WIDTH=480;		//此处需要改成android系统获取屏幕尺寸，以适应不同尺寸屏幕
	final int HEIGHT=854;
	private int startY=BACK_HEIGHT-HEIGHT;
	
	float PlaneCurrentX=0;		//当前飞机坐标，好像没用到
	float PlaneCurrentY=0;
	float disxv=0;
	float disyv=0;
	float v = 1;
	static  boolean fail =false;		//我机是否被击毁

	float pWidth=0;
	float pHeight=0;
	/*
	 * onDraw()内的一些初始化
	 */
	
	
	/**绘制我机**/
	Bitmap bitMyPlane = BitmapFactory.decodeResource(getResources(),
			R.drawable.plane);
	
	/**绘制子弹**/	
	Bitmap bitMyPlaneBullet = BitmapFactory.decodeResource(getResources(),
			R.drawable.planebullet);
	Bitmap bitBullet1 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemybullet1);
	Bitmap bitBullet2 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemybullet2);
	Bitmap bitBullet3 = BitmapFactory.decodeResource(getResources(),
			R.drawable.bossbullet);
	
	/**绘制敌机**/
	Bitmap bitPlane1 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyplane1);
	Bitmap bitPlane2 = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyplane2);
	Bitmap bitPlaneBoss = BitmapFactory.decodeResource(getResources(),
			R.drawable.enemyboss);
	
	/**绘制爆炸**/	
	Bitmap explosion = BitmapFactory.decodeResource(getResources(),
			R.drawable.explosion);	

	/*
	 * 构造方法
	 */
	public DrawView(Context context,TextView tvScore){
		super(context);		
		this.dcontext=context;
		this.tvScore=tvScore;
		plane = new Plane(parameter.screenWidth / 2 - 25, parameter.screenHeight - 85, 1,1);
		PlaneCurrentX=plane.getPlaneX();
		PlaneCurrentY=plane.getPlaneY();	
		init();
	}
	
	/*
	 * 初始化，功能待完善
	 */
	private void init(){	
		
		back=BitmapFactory.decodeResource(dcontext.getResources(), R.drawable.back_img);		//设置了背景图片
		final Handler handler=new Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x123){
					//重新开始移动
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
						
		threadMoveMyPlane=new MoveMyPlanes();
		productBullet=new ProductBullet();
		bulletMove=new BulletMove(this,bt,bombs);
		fightForEnemy=new FightForEnemy(dcontext,pt,bt,bombs,this,tvScore);
		productEnemyPlane=new ProductEnemyPlane(this,pt,bt);
		enemyPlaneMove=new EnemyPlaneMove(this,pt);
		
		threadMoveMyPlane.start();
		productBullet.start();
		bulletMove.start();
		fightForEnemy.start();
		productEnemyPlane.start();
		enemyPlaneMove.start();
	}
	
	/*
	 * 触屏屏幕获取当前坐标
	 */
	public void setValue(float x,float y){
		this.PlaneCurrentX=x;
		this.PlaneCurrentY=y;
		/**校正**/
		float disx = Math.abs(plane.getPlaneX() - PlaneCurrentX);
		float disy = Math.abs(plane.getPlaneY() - PlaneCurrentY);
		float dis = (float) Math.sqrt(disx * disx + disy * disy);
		float dist = dis / v;
		this.disxv = disx / dist;
		this.disyv = disy / dist;
	}
	
	/*
	 * onDraw回调函数
	 * 不要再这里面创建对象，会影响速度的。 不然每次回调都会重新创建一个
	 */
	 
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);	
		Paint paint=new Paint();
		/**画背景图**/				
		Bitmap bitmap123=Bitmap.createBitmap(back,0,startY,WIDTH,HEIGHT);		//这个不能拿到外面去，当心空指针异常
		canvas.drawBitmap(bitmap123, 0, 0,null);		
				
		/**飞机未被击毁时正常绘制飞机图片**/		
		if(fail==false){	
			RectF rectf = new RectF(plane.getPlaneX()-50, 
						plane.getPlaneY()-50,
						plane.getPlaneX()+50,
						plane.getPlaneY()+50);
			canvas.drawBitmap(bitMyPlane, null, rectf, paint);
		}/**飞机被击毁时，运行的一些参数**/
		else{
			paint.setTextSize(50);
			paint.setColor(Color.RED);
			canvas.drawText("You Failed !", 110, 350, paint);
			canvas.save();
			threadMoveMyPlane.interrupt();
			productBullet.interrupt();		//中断产生子弹的线程					
			bulletMove.interrupt();
			fightForEnemy.interrupt();
			productEnemyPlane.interrupt();
			enemyPlaneMove.interrupt();
			
			bt.clear();		//清除屏幕上的子弹
			pt.clear();		//清除屏幕上的飞机
		}
				
		//绘制我机发射子弹
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
		//绘制敌机发射子弹
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
				
		/**绘制敌机**/
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
		
		/**绘制爆炸**/	
		for (int i = 0; i < bombs.size(); i++) {
			RectF rectf4 = new RectF(bombs.get(i).getX()- bombs.get(i).getBombDis(), 
									 bombs.get(i).getY()- bombs.get(i).getBombDis(),
									 bombs.get(i).getX()+ bombs.get(i).getBombDis(),
									 bombs.get(i).getY()+ bombs.get(i).getBombDis());
			canvas.drawBitmap(explosion, null, rectf4, paint);
		}
	/*	for (int i = 0; i < bigBombs.size(); i++) {
			RectF rectf5 = new RectF(bigBombs.get(i).getX()- bigBombs.get(i).getBombDis(),
									 bigBombs.get(i).getY()- bigBombs.get(i).getBombDis(), 
									 bigBombs.get(i).getX()+ bigBombs.get(i).getBombDis(), 
									 bigBombs.get(i).getY()+ bigBombs.get(i).getBombDis());
			canvas.drawBitmap(explosion, null, rectf5, paint);
		}	
	*/		
	}
	/*
	 * 我机发射子弹类
	 * 子弹每次向Y移动-15个像素
	 */
	public class ProductBullet extends Thread {
		public void run() {
			super.run();		
			while (true) {			
				try {
					Thread.sleep(400);		//控制产生子弹速度，太快了就看不出在移动，400ms比较合适
					Bullet bullet = new Bullet(plane.getPlaneX(), plane.getPlaneY()-20, 1, 1,10);		//Bullet初始化
					bt.add(bullet);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class MoveMyPlanes extends Thread {
		@Override
		public void run() {
			super.run();
			while (true) {
				try {
					Thread.sleep(3);		//移动我机,平滑移动.sleep来控制速度
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

