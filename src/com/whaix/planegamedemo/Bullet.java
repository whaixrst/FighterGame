package com.whaix.planegamedemo;

public class Bullet {
	private float BulletX;		//�ӵ�����
	private float BulletY;
	private int BulletFlag;		//�ӵ����ͣ�1���Լ��ɻ��ӵ���2���л��ӵ���0������
	private int BulletType;		//�ӵ�ͼƬѡȡ,1:�һ��ӵ���2���л��ӵ�1��3�л��ӵ�2��4��BOSS�ӵ�
	private int BulletDis;		//�������ӵ���Ŀ
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
