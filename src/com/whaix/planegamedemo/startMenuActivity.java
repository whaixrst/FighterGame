/*
 * V0.8添加开始界面，包含：开始游戏、退出游戏、查看排名、设置、关于等几个选项
 */
package com.whaix.planegamedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.whaix.planegamedemo.dialog.AboutDialog;


public class startMenuActivity extends Activity {

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	//	WindowManager windowManager = getWindowManager();
	//	Display display = windowManager.getDefaultDisplay();
		
		
		setContentView(R.layout.startmenu_main);
	}
	public void onBtnClick(View v){
		if(v.getId()==R.id.start){
			Intent intent=new Intent(startMenuActivity.this,MainActivity.class);
			startMenuActivity.this.startActivity(intent);
		} else if(v.getId()==R.id.about){
			AboutDialog aboutdialog=new AboutDialog(startMenuActivity.this);
			aboutdialog.show();
		}  
		else if(v.getId()==R.id.quit){
			new AlertDialog.Builder(this).setMessage("您要退出游戏吗？").setPositiveButton("确定", new OnClickListener(){
				public void onClick(DialogInterface dialog,int which){
					System.exit(0);
				}
			}).setNegativeButton("取消", new OnClickListener(){
				public void onClick(DialogInterface dialog,int which){
					return;
				}
			}).show();
		}
	}
}
