package com.eshiah.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.eshiah.adapter.RuleAdapter;

public class AppActivity extends Activity {
	RuleAdapter ruleAdapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       /* ListView ruleListView=(ListView)findViewById(R.id.rule_list);
        ruleAdapter = new RuleAdapter();
        ruleListView.setAdapter(ruleAdapter);*/
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
    		startActivity(intent);
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
}