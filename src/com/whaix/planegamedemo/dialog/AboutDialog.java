package com.whaix.planegamedemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.whaix.planegamedemo.R;


public class AboutDialog extends Dialog {
	Context context;
	Button returnGame;
	public AboutDialog(Context context){
		super(context);
		this.context=context;		
	}
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_main);
		returnGame=(Button)findViewById(R.id.btn_aboutback);
		
		returnGame.setOnClickListener(new View.OnClickListener(){		//这里要指明View.OnClickListener不然有冲突
			public void onClick(View v){
				AboutDialog.this.dismiss();
			} 
		});
		
	}
}
