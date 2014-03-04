/*
 * 飞机类，飞机的坐标，敌我识别，型号。
 * V0.7.9.3新加入血量。
 */
package com.whaix.planegamedemo;

public class Plane {
	private float PlaneX;		//飞机坐标
	private float PlaneY;		
	private int PlaneFlag;		//飞机类型。1：我机；2：敌机；0：销毁
	private int PlaneType;		//飞机图片。1：我机；2：敌机1；3：敌机2；4：BOSS
	private int Blood=0;			//飞机血量，中弹一次加一，然后与设定血量上限比较，达到上限即死亡
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
	//设置中弹数
	public void setPlaneShot(){
		++this.Blood;
	}
	//获取当前对象已中弹数，这个函数与下面血量比对，如果中弹数等于血量时，判定死亡
	public int getPlaneShot(){
		return this.Blood;
	}
	//分别指定三种敌机和我机血量
	public int getPlaneBlood(){
		if(getPlaneType()==1){
			return 1;
		} else if(getPlaneType()==2){
			return 1;
		} else if(getPlaneType()==3){
			return 2;
		} else if(getPlaneType()==4){
			return 5;
		} else{						//留着扩展
			return 10;
		}
		
	}
	
}
