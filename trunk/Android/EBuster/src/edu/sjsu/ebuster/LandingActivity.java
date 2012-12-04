package edu.sjsu.ebuster;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class LandingActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
 
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Movie tab
		Intent intentAndroid = new Intent().setClass(this, MovieActivity.class);
		TabSpec tabSpecAndroid = tabHost
		  .newTabSpec("Android")
		  .setIndicator("", ressources.getDrawable(R.drawable.film))
		  .setContent(intentAndroid);
 
		// Music tab
		Intent intentApple = new Intent().setClass(this, MusicActivity.class);
		TabSpec tabSpecApple = tabHost
		  .newTabSpec("Apple")
		  .setIndicator("", ressources.getDrawable(R.drawable.music))
		  .setContent(intentApple);
 
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
 
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_landing, menu);
        return true;
    }

    
}
