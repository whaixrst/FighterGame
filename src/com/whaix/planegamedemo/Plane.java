/*
 * �ɻ��࣬�ɻ������꣬����ʶ���ͺš�
 * V0.7.9.3�¼���Ѫ����
 */
package com.whaix.planegamedemo;

public class Plane {
	private float PlaneX;		//�ɻ�����
	private float PlaneY;		
	private int PlaneFlag;		//�ɻ����͡�1���һ���2���л���0������
	private int PlaneType;		//�ɻ�ͼƬ��1���һ���2���л�1��3���л�2��4��BOSS
	private int Blood=0;			//�ɻ�Ѫ�����е�һ�μ�һ��Ȼ�����趨Ѫ�����ޱȽϣ��ﵽ���޼�����
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
	//�����е���
	public void setPlaneShot(){
		++this.Blood;
	}
	//��ȡ��ǰ�������е������������������Ѫ���ȶԣ�����е�������Ѫ��ʱ���ж�����
	public int getPlaneShot(){
		return this.Blood;
	}
	//�ֱ�ָ�����ֵл����һ�Ѫ��
	public int getPlaneBlood(){
		if(getPlaneType()==1){
			return 1;
		} else if(getPlaneType()==2){
			return 1;
		} else if(getPlaneType()==3){
			return 2;
		} else if(getPlaneType()==4){
			return 5;
		} else{						//������չ
			return 10;
		}
		
	}
	
}
