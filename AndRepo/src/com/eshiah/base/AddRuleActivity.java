/**
 * 
 */
package com.eshiah.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.eshiah.adapter.DatabaseHandler;
import com.eshiah.adapter.RulesVO;

/**
 * @author akaruppaiah
 *
 */
public class AddRuleActivity extends Activity {
	
	//Class variables
	private EditText txtRuleCat;
	private EditText txtRulName;
	private TextView txtRulNamValidate;
	private int mYear;
	private int mMonth;
	private int mDay;
	private TextView txtFrmDtDisplay;
	private TextView txtToDtDisplay;
	private Button btnFrmDtPick;
	private Button btnToDtPick;
	private Button btnFreq;
	private Button btnPeriod;
	static final int DATE_DIALOG_ID = 0;
	private boolean fromDialog =true;
	private DatePickerDialog.OnDateSetListener mDateSetListener;
	int itemIndex;
	private Button btnSubmit;
	public void onCreate(Bundle savedInstanceState) {
		
		//Hardcoding an arraylist now:This arraylist will contain all the rule names which will be loaded from the DB later.
		final List<String> ruleNames = new ArrayList<String>();
		ruleNames.add("rule1");
		ruleNames.add("rule2");
		ruleNames.add("rule3");
		ruleNames.add("rule4");
		
		super.onCreate(savedInstanceState);
		//Dropdown for Location based rule options
		/*Spinner spinner = (Spinner)this.findViewById(R.id.spLoc);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		        this,
		        android.R.layout.simple_spinner_item,
		        new String[] { "On Reaching", "On Moving Away"});
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		System.out.println("Inside AddRuleActivity");*/
		setContentView(R.layout.addrule);
		
    	txtRuleCat=(EditText)findViewById(R.id.ruleType);
    	txtRuleCat.setEnabled(false);

		final CharSequence[] items = {"Time based", "Location based"};
		final CharSequence[] frequency ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14",} ;
/*		for(int i=0;i<60;i++){
			frequency[i]= Integer.toString(i);
		}*/
		final CharSequence[] period = {"Sec", "Min", "Hrs"};
		
		//Alertbox to choose the trigger type
		
		//Common AlertDialog builder passing the array of items
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Rule Type");
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    	txtRuleCat=(EditText)findViewById(R.id.ruleType);
		    	txtRuleCat.setText(items[item]);
		    	//txtRuleCat.setVisibility(View.VISIBLE);
		    }
		});
		final AlertDialog alert = builder.create();
		
		
		//Code to handle SelectButton click
		Button addRuleBtn = (Button) findViewById(R.id.btnRuleSel);
		addRuleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
           	alert.show();
            }
        });
		
		//Code to handle Rulename uniqueness check
		txtRulName=(EditText)findViewById(R.id.ruleName);
		txtRulNamValidate=(TextView)findViewById(R.id.rulNamValidate);
		txtRulName.addTextChangedListener(new TextWatcher() { 
	        public void afterTextChanged(Editable s) { 
	        	System.out.println("Inside afterTxtChanged function");
	           if(ruleNames.contains(txtRulName.getText().toString())){
	        	//if(txtRulName.getText().toString().equals("11")){
	        		System.out.println("Inside check function");
	        	   txtRulNamValidate.setText("The rule name already exists");
	        	   findViewById(R.id.btnSubmit).setEnabled(false);
	           }else{
	        	   txtRulNamValidate.setText("");
	        	   findViewById(R.id.btnSubmit).setEnabled(true);
	           }
	        } 
	        
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {} 
	        public void onTextChanged(CharSequence s, int start, int before, int count) {} 
	    }); 
		
		//Listener for the category text change
		
		txtRuleCat.addTextChangedListener(new TextWatcher(){
			 public void afterTextChanged(Editable s) { 
		        	System.out.println("Inside afterTxtChanged function");
		        	if(txtRuleCat.getText().toString().equalsIgnoreCase("Location based")){
		           //if("Location based".equalsIgnoreCase((txtRuleCat.getText().toString()){
		        	//if(txtRulName.getText().toString().equals("11")){
		        		System.out.println("Inside check function");
	        	    	btnFreq.setVisibility(View.INVISIBLE);
	        	    	btnPeriod.setVisibility(View.INVISIBLE);
	        	    	findViewById(R.id.lblFrequency).setVisibility(View.INVISIBLE);
	        	    	findViewById(R.id.loc_spinner).setEnabled(true);
		           }else{
		        	   	btnFreq.setVisibility(View.VISIBLE);
	        	    	btnPeriod.setVisibility(View.VISIBLE);
	        	    	findViewById(R.id.lblFrequency).setVisibility(View.VISIBLE);
	        	    	findViewById(R.id.loc_spinner).setEnabled(false);
		           }
		        } 
		        
		        public void beforeTextChanged(CharSequence s, int start, int count, int after) {} 
		        public void onTextChanged(CharSequence s, int start, int before, int count) {} 
			
		});
		
		//Code to handle the calendar button and text field
		
		txtFrmDtDisplay = (TextView) findViewById(R.id.txtDtFrom);        
	    btnFrmDtPick = (Button) findViewById(R.id.btnDtFrm);
	    btnToDtPick = (Button) findViewById(R.id.btntDtTo);
	    txtToDtDisplay = txtFrmDtDisplay = (TextView) findViewById(R.id.txtDtTo); 
	    btnFreq = (Button) findViewById(R.id.btnFreq);
	    btnPeriod = (Button) findViewById(R.id.btnPeriod);
	    
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Frequency");
		builder.setItems(frequency, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        //Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
		    	btnFreq.setText(frequency[item]);
		    	//txtRuleCat.setVisibility(View.VISIBLE);
		    }
		});
		final AlertDialog alert1 = builder.create();
		
		
		builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Period");
		builder.setItems(period, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	btnPeriod.setText(period[item]);
		    }
		});
		final AlertDialog alert2 = builder.create();
		
	    
	    btnFreq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert1.show();
			}
		});
	    
	    btnPeriod.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert2.show();
			}
		});
	    btnFrmDtPick.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	            fromDialog=true;
	            
	        }
	    });
	    
	    btnToDtPick.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	            fromDialog=false;
	        }
	    });
	    
	   // updateDisplay();
	    
	      mDateSetListener =
			    new DatePickerDialog.OnDateSetListener() {
			        public void onDateSet(DatePicker view, int year, 
			                              int monthOfYear, int dayOfMonth) {
			            mYear = year;
			            mMonth = monthOfYear;
			            mDay = dayOfMonth;
			            updateDisplay();
			        }
			    };
			    
			    //Change calendar functions based on the RadioButton 
			    
			       RadioButton rbExtTime = (RadioButton)findViewById(R.id.exactTime);
			       RadioButton rbIntTime = (RadioButton)findViewById(R.id.intTime);

			       OnClickListener radio_listener = new OnClickListener() {
		        	    public void onClick(View v) {
		        	    	System.out.println("about to enter rb select");
		        	        // Perform action on clicks
		        	    	//if(txtRuleCat.getText().toString().equals("Time based")){
		        	    		System.out.println("inside rb select");
		        	    	txtToDtDisplay.setVisibility(View.INVISIBLE);
		        	    	btnToDtPick.setVisibility(View.INVISIBLE);
		        	    	btnFreq.setVisibility(View.INVISIBLE);
		        	    	btnPeriod.setVisibility(View.INVISIBLE);
		        	    	findViewById(R.id.lblFrequency).setVisibility(View.INVISIBLE);
		        	    	//}
/*		        	    	if(txtRuleCat.getText().equals("Location based")){
		        	    		btnFreq.setVisibility(View.INVISIBLE);
			        	    	btnPeriod.setVisibility(View.INVISIBLE);
			        	    	findViewById(R.id.lblFrequency).setVisibility(View.INVISIBLE);
		        	    	}*/
		        	    	
		        	     //   Toast.makeText(HelloFormStuff.this, rb.getText(), Toast.LENGTH_SHORT).show();
		        	    }
		        	};
		        	 OnClickListener radio_listener1 = new OnClickListener() {
		         	    public void onClick(View v) {
		         	        // Perform action on clicks
		        	    	txtToDtDisplay.setVisibility(View.VISIBLE);
		        	    	btnToDtPick.setVisibility(View.VISIBLE);
		        	    	if(txtRuleCat.getText().toString().equals("Time based")){
		        	    	btnFreq.setVisibility(View.VISIBLE);
		        	    	btnPeriod.setVisibility(View.VISIBLE);
		        	    	findViewById(R.id.lblFrequency).setVisibility(View.VISIBLE);
		        	    	}
		        	    	if(txtRuleCat.getText().toString().equals("Location based")){
		        	    		btnFreq.setVisibility(View.INVISIBLE);
			        	    	btnPeriod.setVisibility(View.INVISIBLE);
			        	    	findViewById(R.id.lblFrequency).setVisibility(View.INVISIBLE);
		        	    	}
		        	    	
		         	    }
		         	};
		         	rbExtTime.setOnClickListener(radio_listener);
		         	rbIntTime.setOnClickListener(radio_listener1);
		         	
		         	//spinner code for location - towards and away
		         	Spinner spinner = (Spinner) findViewById(R.id.loc_spinner);
        	    	// Create an ArrayAdapter using the string array and a default spinner layout
        	    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        	    	        R.array.planets_array, android.R.layout.simple_spinner_item);
        	    	// Specify the layout to use when the list of choices appears
        	    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	    	// Apply the adapter to the spinner
        	    	spinner.setAdapter(adapter);
        	    	findViewById(R.id.loc_spinner).setEnabled(false);
        	    	
        	    	//spinner for trigger action
		         	//spinner code for location - towards and away
        	    	Spinner spinner1 = (Spinner) findViewById(R.id.spTrigAction);
        	    	// Create an ArrayAdapter using the string array and a default spinner layout
        	    	ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
        	    	        R.array.trig_action, android.R.layout.simple_spinner_item);
        	    	// Specify the layout to use when the list of choices appears
        	    	adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	    	// Apply the adapter to the spinner
        	    	spinner1.setAdapter(adapter1);
        	    	
        	    	DateFormat  fromUser = new SimpleDateFormat("dd/MM/yyyy");
        	    	int totSeconds = Integer.parseInt(btnPeriod.getText().toString());
        	    	if(spinner.getSelectedItem().toString().equals("min")){
        	    		totSeconds=(Integer.parseInt(btnPeriod.getText().toString()))*60;
        	    	}else if(spinner.getSelectedItem().toString().equals("hrs")){
        	    		totSeconds=(Integer.parseInt(btnPeriod.getText().toString()))*60*60;
        	    	}
        	    	//handler for the submit button
        	    	btnSubmit= (Button) findViewById(R.id.btnSubmit);
        	    	final DatabaseHandler dbHandler = new DatabaseHandler(this);
        	    final RulesVO ruleVO = new RulesVO();
        	    	ruleVO.setRuleName(txtRulName.getText().toString());
        	    	ruleVO.setRuleCategory(txtRuleCat.getText().toString());
        	    	try {
						ruleVO.setDateFrom(fromUser.parse(txtFrmDtDisplay.getText().toString()));
						ruleVO.setDateTo(fromUser.parse(txtToDtDisplay.getText().toString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	    	ruleVO.setDirection(spinner.getSelectedItem().toString());
        	    	ruleVO.setTrigAction(spinner1.getSelectedItem().toString());
        	    	ruleVO.setInterval(rbIntTime.hasSelection());
        	    	ruleVO.setPeriod(totSeconds);
        	    	btnSubmit.setOnClickListener(new View.OnClickListener() {
        		        public void onClick(View v) {
        		            dbHandler.addRule(ruleVO);
        		            fromDialog=false;
        		        }
        		    });
		         	       
		}
	private void updateDisplay() {
		System.out.println(fromDialog);
		if(fromDialog){
	    this.txtFrmDtDisplay.setText(
	        new StringBuilder()
	                // Month is 0 based so add 1
	                .append(mMonth + 1).append("-")
	                .append(mDay).append("-")
	                .append(mYear).append(" "));
		}else{
			this.txtToDtDisplay.setText(
			        new StringBuilder()
			                // Month is 0 based so add 1
			                .append(mMonth + 1).append("-")
			                .append(mDay).append("-")
			                .append(mYear).append(" "));
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	   switch (id) {
	   case DATE_DIALOG_ID:
	      return new DatePickerDialog(this,
	                mDateSetListener,
	                mYear, mMonth, mDay);
	   }
	   return null;
	}
	
	
	
	//unused function
	private void alertDialBuilder(CharSequence[] listItems){
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Rule Type");
		builder.setItems(listItems, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	itemIndex=item;
		    }
		});
		final AlertDialog alert = builder.create();
		alert.show();
	}
	
	
}
