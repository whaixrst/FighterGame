package com.whaix.planegamedemo;

import java.util.ArrayList;

public class EnemyPlaneMove extends Thread {
	ArrayList<Plane> pt=new ArrayList<Plane>();
	DrawView drawView;
	public EnemyPlaneMove(DrawView drawView,ArrayList<Plane> pt) {
		// TODO Auto-generated constructor stub
		this.pt=pt;
		this.drawView=drawView;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			try {
				Thread.sleep(15);

				for (int i = 0; i < pt.size(); ++i) {
					if (null != pt.get(i)) {
						if(pt.get(i).getPlaneType()==2){
							pt.get(i).setPlaneY(pt.get(i).getPlaneY() + (float) 2);		//控制敌机速度，这里应该分开控制各机速度。
						} else if(pt.get(i).getPlaneType()==3){
							pt.get(i).setPlaneY(pt.get(i).getPlaneY() + (float) 1.5);	//V0.7.9.2改变了三种飞机不同速度
						} else if(pt.get(i).getPlaneType()==4){
							pt.get(i).setPlaneY(pt.get(i).getPlaneY() + (float) 1);	
						}
						if (pt.get(i).getPlaneY() > 850) {
							pt.remove(i);
						}
					}
					drawView.postInvalidate();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
