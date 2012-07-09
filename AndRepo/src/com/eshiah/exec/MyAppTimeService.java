/**
 * 
 */
package com.eshiah.exec;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import com.eshiah.adapter.RuleDbAdapter;
import com.eshiah.db.RuleListDatabaseHelper;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
/**
 * @author akaruppaiah
 *
 */
public class MyAppTimeService extends Service {
	private Timer timer = new Timer();
	private final Calendar time = Calendar.getInstance();
	private Long timeInt = 60L; 
	private Handler handler = new Handler();
	private static final String TAG = "MyAppTimeService";
	RuleListDatabaseHelper ruleListDatabaseHelper;
	Cursor navCursor;
	//MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
		ruleListDatabaseHelper = new RuleListDatabaseHelper(this);
		navCursor = ruleListDatabaseHelper.getAllRuleRecords();
		
		//player = MediaPlayer.create(this, R.raw.braincandy);
		//player.setLooping(false); // Set looping
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		//player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		//player.start();
	}
	
	
	 private void timerTaskExec() {
		 	timeInt=30000L;
		 	navCursor = ruleListDatabaseHelper.getAllRuleRecords();
		 	Toast.makeText(this,"Timer inncrement value:" + (long)timeInt,Toast.LENGTH_LONG).show();
	    	timer.scheduleAtFixedRate(new TimerTask(){ public void run() {
	    	handler.post(new Runnable(){
	    		public void run(){
	    				execAction();
	  	    		}
	    	});	
	    	}}, 0,(long) (timeInt*1000));
	    }
	 
	 private void execAction(){
		 Toast.makeText(this,"I am back after:"+timeInt/60 +" minutes", Toast.LENGTH_LONG).show();
		 
	 }
}