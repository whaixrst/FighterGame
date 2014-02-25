/*
 * 爆炸效果的类
 */
package com.whaix.planegamedemo;

public class Bomb {
	float x;
	float y;
	float bombDis;
	
	public Bomb(float x,float y,float bombDis){
		this.x=x;
		this.y=y;
		this.bombDis=bombDis;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getBombDis() {
		return bombDis;
	}

	public void setBombDis(float bombDis) {
		this.bombDis = bombDis;
	}
	
}
