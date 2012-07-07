/**
 * 
 */
package com.eshiah.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author akaruppaiah
 *
 */
public class AddRuleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrule);
		}
	
	public void onCancel(View view) {
		//Intent intent = new Intent(this, RuleListActivity.class);
		//startActivity(intent);
		finish();
	}
	
	public void onSave(View view) {
		Intent intent = getIntent();
		EditText ruleName = (EditText)findViewById(R.id.ruleName);
		intent.putExtra("RuleName", ruleName.getText().toString());
		
		EditText ruleTrigger = (EditText)findViewById(R.id.ruleTrigger);
		intent.putExtra("RuleTrigger", ruleTrigger.getText().toString());
		
		EditText ruleAction = (EditText)findViewById(R.id.ruleAction);
		intent.putExtra("RuleAction", ruleAction.getText().toString());
		this.setResult(RESULT_OK, intent);
		finish();
		
	}
}
