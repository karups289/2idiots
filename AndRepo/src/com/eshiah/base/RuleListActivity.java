package com.eshiah.base;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.eshiah.adapter.RuleDbAdapter;
import com.eshiah.db.RuleListDatabaseHelper;
import com.eshiah.exec.MyAppTimeService;

public class RuleListActivity extends Activity {

	RuleListDatabaseHelper ruleListDatabaseHelper;
	RuleDbAdapter ruleDbAdapter;
	public static final int RULE_ENTRY_REQUEST_CODE = 1;
	public static final int RULE_EDIT_REQUEST_CODE = 2;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ruleListDatabaseHelper = new RuleListDatabaseHelper(this);
        ListView ruleListView=(ListView)findViewById(R.id.rule_list);

        ruleDbAdapter = new RuleDbAdapter(this,ruleListDatabaseHelper.getAllRuleRecords());
        ruleListView.setAdapter(ruleDbAdapter);
        startService(new Intent(this, MyAppTimeService.class));
        
        
        //ruleTrackerOpenHelper.getWritableDatabase();
        
        
        ruleListView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view,
        		int position, long id) {
        		//Toast.makeText(getApplicationContext(),
        		//	"Click ListItem Number " + position, Toast.LENGTH_LONG)
        		//	.show();
        		Cursor navCursor = ruleListDatabaseHelper.getAllRuleRecords();
        		navCursor.moveToPosition(position);
        		Intent i = new Intent(parent.getContext(), AddRuleActivity.class);
                i.putExtra(RuleListDatabaseHelper.RULETRACKER_COLUMN_ID, navCursor.getString(
                		navCursor.getColumnIndexOrThrow(RuleListDatabaseHelper.RULETRACKER_COLUMN_ID)));
                i.putExtra(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULENAME, navCursor.getString(
                		navCursor.getColumnIndexOrThrow(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULENAME)));
                i.putExtra(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULETRIGGER, navCursor.getString(
                		navCursor.getColumnIndexOrThrow(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULETRIGGER)));
                i.putExtra(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULEACTION, navCursor.getString(
                		navCursor.getColumnIndexOrThrow(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULEACTION)));
                startActivityForResult(i, RULE_EDIT_REQUEST_CODE);
        	}
        });
        
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
    	if (requestCode == RULE_EDIT_REQUEST_CODE) {
    		
    		if (resultCode == RESULT_OK ) {
    			String ruleName = data.getStringExtra("RuleName");
    			String ruleTrigger = data.getStringExtra("RuleTrigger");
    			String ruleAction = data.getStringExtra("RuleAction");
    			Toast.makeText(getApplicationContext(),"going to update:"+data.getStringExtra("mRowId"),Toast.LENGTH_LONG).show();
    			ruleListDatabaseHelper.updateRuleRecord(data.getStringExtra("RuleId"),ruleName, ruleTrigger, ruleAction);
    			ruleDbAdapter.changeCursor(ruleListDatabaseHelper.getAllRuleRecords());
    			//ruleAdapter.addRuleRecord(new RuleRecord(ruleName,ruleTrigger,ruleAction));
    			//ruleAdapter.notifyDataSetChanged();
    		}
    	}
    	stopService(new Intent(this, MyAppTimeService.class));
    	startService(new Intent(this, MyAppTimeService.class));
    }
    
    
   
}