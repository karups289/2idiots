package com.eshiah.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eshiah.base.R;
import com.eshiah.core.RuleRecord;

public class RuleAdapter extends BaseAdapter {

	private ArrayList<RuleRecord> ruleRecordList=new ArrayList<RuleRecord>();
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ruleRecordList.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return ruleRecordList.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if(view==null){
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view=inflater.inflate(R.layout.rule_list_item,parent,false);
		}
		RuleRecord rule=ruleRecordList.get(index);
		TextView txtRuleName = (TextView)
				view.findViewById(R.id.txtRuleName);
		TextView txtRuleTrigger = (TextView)
				view.findViewById(R.id.txtRuleTrigger);
		TextView txtRuleAction = (TextView)
				view.findViewById(R.id.txtruleAction);
		txtRuleName.setText(rule.getRuleName());
		txtRuleTrigger.setText("Trigger:"+rule.getRuleTrigger());
		txtRuleAction.setText("Action:"+rule.getRuleAction());
		
		return view;
	}
	
	public RuleAdapter(){
		//ruleRecordList.add(new RuleRecord("Rule1","Hourly","Notification"));
		//ruleRecordList.add(new RuleRecord("Rule2","Yearly","Notification"));
		//ruleRecordList.add(new RuleRecord("Rule3","weekly","Vibrate"));
	}
	
	public void addRuleRecord(RuleRecord ruleRecord){
		ruleRecordList.add(ruleRecord);
	}

}
