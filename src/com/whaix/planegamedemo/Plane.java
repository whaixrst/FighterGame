package com.whaix.planegamedemo;

public class Plane {
	float PlaneX;		//飞机坐标
	float PlaneY;		
	int PlaneFlag;		//飞机类型。1：我机；2：敌机；0：销毁
	int PlaneType;		//飞机图片。1：我机；2：敌机1；3：敌机2；4：BOSS
	
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
