/*
 * 子弹类，包括我机和敌机
 */
package com.whaix.planegamedemo;

public class Bullet {
	private float BulletX;		//子弹坐标
	private float BulletY;
	private int BulletFlag;		//子弹类型，1：自己飞机子弹；2：敌机子弹；0：销毁
	private int BulletType;		//子弹图片选取,1:我机子弹；2：敌机子弹1；3敌机子弹2；4：BOSS子弹
	private int BulletDis;		//产生的子弹数目
	public Bullet(float BulletX,float BulletY,int BulletFlag,int BulletType ,int BulletDis){
		this.BulletX=BulletX;
		this.BulletY=BulletY;
		this.BulletFlag=BulletFlag;
		this.BulletType=BulletType;
		this.BulletDis=BulletDis;
	}
	public float getBulletX() {
		return BulletX;
	}
	public void setBulletX(float bulletX) {
		BulletX = bulletX;
	}
	public float getBulletY() {
		return BulletY;
	}
	public void setBulletY(float bulletY) {
		BulletY = bulletY;
	}
	public int getBulletFlag() {
		return BulletFlag;
	}
	public void setBulletFlag(int bulletFlag) {
		BulletFlag = bulletFlag;
	}
	public int getBulletType() {
		return BulletType;
	}
	public void setBulletType(int bulletType) {
		BulletType = bulletType;
	}
	public int getBulletDis() {
		return BulletDis;
	}
	public void setBulletDis(int bulletDis) {
		BulletDis = bulletDis;
	}
}
