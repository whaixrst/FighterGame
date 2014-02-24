package com.whaix.planegamedemo;

import java.util.ArrayList;

public class EnemyPlaneMove extends Thread {
	ArrayList<Plane> pt;
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

				for (int i = 0; i < pt.size(); i++) {
					if (null != pt.get(i)) {
						pt.get(i).setPlaneY(pt.get(i).getPlaneY() + (float) 1);
						if (pt.get(i).getPlaneY() > 800) {
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
