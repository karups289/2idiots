package com.eshiah.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.eshiah.adapter.RuleAdapter;
import com.eshiah.adapter.RuleDbAdapter;
import com.eshiah.core.RuleRecord;
import com.eshiah.db.RuleListDatabaseHelper;

public class RuleListActivity extends Activity {
	RuleAdapter ruleAdapter;
	RuleListDatabaseHelper ruleListDatabaseHelper;
	RuleDbAdapter ruleDbAdapter;
	public static final int RULE_ENTRY_REQUEST_CODE = 1;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ruleListDatabaseHelper = new RuleListDatabaseHelper(this);
        ListView ruleListView=(ListView)findViewById(R.id.rule_list);
        ruleAdapter = new RuleAdapter();
        ruleDbAdapter = new RuleDbAdapter(this,ruleListDatabaseHelper.getAllRuleRecords());
        ruleListView.setAdapter(ruleDbAdapter);
        
        
        
        //ruleTrackerOpenHelper.getWritableDatabase();
        
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
    	super.onCreateOptionsMenu(m);
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rulelistmenu,m);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	if (item.getItemId() == R.id.add_rule_menu_item) {
    		Intent intent = new Intent(this, AddRuleActivity.class);
    		//startActivity(intent);
    		startActivityForResult(intent, RULE_ENTRY_REQUEST_CODE);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == RULE_ENTRY_REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			String ruleName = data.getStringExtra("RuleName");
    			String ruleTrigger = data.getStringExtra("RuleTrigger");
    			String ruleAction = data.getStringExtra("RuleAction");
    			
    			ruleListDatabaseHelper.saveRuleRecord(ruleName, ruleTrigger, ruleAction);
    			ruleDbAdapter.changeCursor(ruleListDatabaseHelper.getAllRuleRecords());
    			//ruleAdapter.addRuleRecord(new RuleRecord(ruleName,ruleTrigger,ruleAction));
    			//ruleAdapter.notifyDataSetChanged();
    		}
    	}
    }
    
}