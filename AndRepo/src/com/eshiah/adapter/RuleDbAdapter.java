/**
 * 
 */
package com.eshiah.adapter;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.eshiah.base.R;
import com.eshiah.db.RuleListDatabaseHelper;

/**
 * @author akaruppaiah
 * 
 */
public class RuleDbAdapter extends CursorAdapter {
	public RuleDbAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	public void bindView(View view, Context context, Cursor cursor) {
		TextView txtRuleName = (TextView) view.findViewById(R.id.txtRuleName);
		TextView txtRuleTrigger = (TextView) view.findViewById(R.id.txtRuleTrigger);
		TextView txtRuleAction = (TextView) view.findViewById(R.id.txtruleAction);
		txtRuleName.setText(cursor.getString(cursor.getColumnIndex(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULENAME)));
		txtRuleTrigger.setText(cursor.getString(cursor.getColumnIndex(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULETRIGGER)));
		txtRuleAction.setText(cursor.getString(cursor.getColumnIndex(RuleListDatabaseHelper.RULETRACKER_COLUMN_RULEACTION)));
		
		
	}

	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.rule_list_item, parent, false);
		return view;
	}
}
