package com.whaix.planegamedemo;

import java.util.ArrayList;

public class BombMove extends Thread {
	ArrayList<Bomb> bombt;
	DrawView drawView;

	public BombMove(ArrayList<Bomb> bombt, DrawView drawView) {
		this.bombt = bombt;
		this.drawView = drawView;
	}

	public void run() {
		super.run();
		while (true) {
			try {
				Thread.sleep(100);
				for (int i = 0; i < bombt.size(); i++) {
					if (null != bombt.get(i)) {
						bombt.get(i).setBombDis(bombt.get(i).getBombDis() + 1);
						if (bombt.get(i).getBombDis() > 30) {
							bombt.remove(i);
						}
						bombt.get(i).setY(bombt.get(i).getY()+5);
					}
					drawView.postInvalidate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
