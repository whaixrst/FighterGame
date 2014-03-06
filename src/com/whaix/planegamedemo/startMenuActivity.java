/*
 * V0.8��ӿ�ʼ���棬��������ʼ��Ϸ���˳���Ϸ���鿴���������á����ڵȼ���ѡ��
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
			new AlertDialog.Builder(this).setMessage("��Ҫ�˳���Ϸ��").setPositiveButton("ȷ��", new OnClickListener(){
				public void onClick(DialogInterface dialog,int which){
					System.exit(0);
				}
			}).setNegativeButton("ȡ��", new OnClickListener(){
				public void onClick(DialogInterface dialog,int which){
					return;
				}
			}).show();
		}
	}
}
