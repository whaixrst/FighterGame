package com.whaix.planegamedemo;

public class Plane {
	float PlaneX;		//�ɻ�����
	float PlaneY;		
	int PlaneFlag;		//�ɻ����͡�1���һ���2���л���0������
	int PlaneType;		//�ɻ�ͼƬ��1���һ���2���л�1��3���л�2��4��BOSS
	
	public Plane(float PlaneX,float PlaneY,int PlaneFlag,int PlaneType){
		this.PlaneX=PlaneX;
		this.PlaneY=PlaneY;
		this.PlaneFlag=PlaneFlag;
		this.PlaneType=PlaneType;
	}

	public float getPlaneX() {
		return this.PlaneX;
	}

	public void setPlaneX(float planeX) {
		this.PlaneX = planeX;
	}

	public float getPlaneY() {
		return this.PlaneY;
	}

	public void setPlaneY(float planeY) {
		this.PlaneY = planeY;
	}

	public int getPlaneFlag() {
		return this.PlaneFlag;
	}

	public void setPlaneFlag(int planeFlag) {
		this.PlaneFlag = planeFlag;
	}

	public int getPlaneType() {
		return this.PlaneType;
	}

	public void setPlaneType(int planeType) {
		this.PlaneType = planeType;
	}
}
