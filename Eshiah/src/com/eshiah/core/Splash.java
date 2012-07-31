package com.eshiah.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(5000);
				}catch(InterruptedException exception){
					exception.printStackTrace();
				}finally{
					Intent intent=new Intent("com.eshiah.core.Menu");
					startActivity(intent);
				}
			}
		};
		timer.start();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	
	
}
