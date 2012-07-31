/**
 * 
 */
package com.eshiah.core;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * @author karups
 *
 */
public class TextPlay extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		Button chkCmd = (Button)findViewById(R.id.bResults);
		ToggleButton passTog = (ToggleButton)findViewById(R.id.tbPassword);
		EditText input = (EditText)findViewById(R.id.etCommands);
		TextView display = (TextView)findViewById(R.id.tvResults);
	}
	

}
