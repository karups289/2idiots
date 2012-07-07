/**
 * 
 */
package com.eshiah.base;

import com.eshiah.db.RuleListDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author akaruppaiah
 *
 */
public class AddRuleActivity extends Activity {
	

	private EditText ruleName;
	private EditText ruleTrigger;
	private EditText ruleAction;
	private EditText ruleId;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrule);
		
		ruleName=(EditText)findViewById(R.id.ruleName);
		ruleTrigger=(EditText)findViewById(R.id.ruleTrigger);
		ruleAction=(EditText)findViewById(R.id.ruleAction);
		ruleId=(EditText)findViewById(R.id.ruleid);
		
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             ruleName.setText(extras.getString(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULENAME));
             ruleTrigger.setText(extras.getString(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULETRIGGER));
             ruleAction.setText(extras.getString(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULEACTION));
             //Toast.makeText(getApplicationContext(),"going to update:"+extras.getString(RuleListDatabaseHelper.RULETRACKER_COLUMN_ID),Toast.LENGTH_LONG).show();
             ruleId.setText(extras.getString(RuleListDatabaseHelper.RULETRACKER_COLUMN_ID));
            
             
        }
		
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
		
		EditText ruleId = (EditText)findViewById(R.id.ruleid);
		intent.putExtra("RuleId", ruleId.getText().toString());

		this.setResult(RESULT_OK, intent);
		finish();
		
	}
}
