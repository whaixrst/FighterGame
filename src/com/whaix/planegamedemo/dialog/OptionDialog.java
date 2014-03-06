/*
 * 暂时屏蔽，后面跟SQLite一起加入
 */
package com.whaix.planegamedemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.whaix.planegamedemo.R;

public class OptionDialog extends Dialog {
	Context context;
	ToggleButton toggle_voice;
	Button btn_back;
	public OptionDialog(Context context){
		super(context);
		this.context=context;
	}
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.option_main);
		
		btn_back=(Button)findViewById(R.id.btn_optionback);
		toggle_voice=(ToggleButton)findViewById(R.id.toggle_voice);
		
	/* 	toggle_voice.setOnCheckedChangeListenser(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton button,boolean isChecked){
				if(isChecked){
					
				}
			}
		});
		*/
	}
}
