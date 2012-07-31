/**
 * 
 */
package com.eshiah.core;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * @author karups
 *
 */
public class TextPlay extends Activity{

	Button chkCmd ;
	ToggleButton passTog ;
	EditText input ;
	TextView display ;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		 chkCmd = (Button)findViewById(R.id.bResults);
		 passTog = (ToggleButton)findViewById(R.id.tbPassword);
		 input = (EditText)findViewById(R.id.etCommands);
		 display = (TextView)findViewById(R.id.tvResults);
		
		
	}
	public void clickTbPassword(View v){
		if(passTog.isChecked()){
			input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}else{
			input.setInputType(InputType.TYPE_CLASS_TEXT);
		}
	}

	public void clickBResults(View v){
		String check = input.getText().toString().trim();
		if(check.contentEquals("left")){
			display.setGravity(Gravity.LEFT);
		}else if(check.contentEquals("center")){
			display.setGravity(Gravity.CENTER);
		}else if(check.contentEquals("right")){
			display.setGravity(Gravity.RIGHT);
		}else if(check.contentEquals("blue")){
			display.setGravity(Gravity.RIGHT);
		}
		
	}
}
