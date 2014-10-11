package com.ericringer.sfindiefest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.ericringer.sfindiefest.fragments.AboutFragment;
import com.ericringer.sfindiefest.fragments.BaseFragment.FragmentCallback;
import com.ericringer.sfindiefest.fragments.CommentsFragment;
import com.ericringer.sfindiefest.fragments.FilmsFragment;
import com.ericringer.sfindiefest.fragments.HomeFragment;
import com.ericringer.sfindiefest.fragments.ScheduleFragment;


public class MainActivity extends FragmentActivity {
	public static boolean didCreate;
	private LinearLayout mDrawerView;
	private DrawerLayout mDrawerLayout;

	protected String TAG = getClass().getSimpleName();
	private ActionBarDrawerToggle mDrawerToggle;
	public static View previousView;
	FragmentCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_main);
		prepareActivity();
    }


	private void prepareActivity() {
    	prepareDrawer();
		callback = new FragmentCallback(){

			@Override
			public void openDrawer() {
				MainActivity.this.openDrawer();
			}

			@Override
			public void switchFragment(Fragment fragment) {
				FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
				trans.replace(R.id.fragSFIF, fragment, "fragSFIF");
				trans.addToBackStack("fragSFIF");
				trans.commit();
			}};
			
			if(!didCreate)callback.switchFragment(HomeFragment.getInstance(callback));
			didCreate = true;
	}

    public boolean onOptionsItemSelected(MenuItem item){
    	if (mDrawerToggle.onOptionsItemSelected(item)) {
        	return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

    private void prepareDrawer() {
  
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerView = (LinearLayout)findViewById(R.id.left_drawer);
        OnClickListener itemClickListener = new OnClickListener(){

    		@Override
    		public void onClick(View v) {
    			if(previousView!=null)setViewUnSelected(previousView);
    		    setViewSelected(v);
    		    previousView = v;
    		    mDrawerLayout.closeDrawer(mDrawerView);
    		    displaySelectedItem(v);
    	}};
    	
    	findViewById(R.id.btnHome).setOnClickListener(itemClickListener);
    	findViewById(R.id.btnFilms).setOnClickListener(itemClickListener);
    	findViewById(R.id.btnSchedule).setOnClickListener(itemClickListener);
    	findViewById(R.id.btnSearch).setOnClickListener(itemClickListener);
    	findViewById(R.id.btnComments).setOnClickListener(itemClickListener);
    	findViewById(R.id.btnAbout).setOnClickListener(itemClickListener);
    	getSupportFragmentManager().addOnBackStackChangedListener(new OnBackStackChangedListener(){

    		@Override
    		public void onBackStackChanged() {
    			if(getSupportFragmentManager().getBackStackEntryCount() <=0){
    				callback.switchFragment(HomeFragment.getInstance(callback));
    			}
    			
    		}});
        
    	getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
    	mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(R.string.app_name);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.app_name);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    	
        		
	protected void displaySelectedItem(View v) {
		Fragment fragment = null;
		switch(v.getId()){
		case R.id.btnHome:
			fragment = HomeFragment.getInstance(callback);
			break;
		case R.id.btnFilms:
			fragment = FilmsFragment.getInstance(callback);
			break;
			case R.id.btnSchedule:
				fragment = ScheduleFragment.getInstance(callback);
			break;
			case R.id.btnSearch:
				//fragment = SearchFragment.getInstance(callback);
				startActivity(new Intent(getApplicationContext(),SearchActivity.class));
			break;
			case R.id.btnComments:
				fragment = CommentsFragment.getInstance(callback);
			break;
			case R.id.btnAbout:
				fragment = AboutFragment.getInstance(callback);
			break;
		}
		if(fragment==null)return;
		callback.switchFragment(fragment);
		
	}


	public void setViewSelected(View v){
		//v.setBackgroundColor(getResources().getColor(R.color.dark_grey));
		//((TextView)v).setTextColor(getResources().getColor(R.color.white));
	}
	public void setViewUnSelected(View v){
		//v.setBackgroundResource(R.drawable.button_selector);
		//((TextView)v).setTextColor(getResources().getColor(R.color.dark_grey));
	}
	
	public void openDrawer() {
		mDrawerLayout.openDrawer(mDrawerView);
	}
	
	public void closeDrawer() {
		mDrawerLayout.closeDrawer(mDrawerView);	
	}


	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
	}
	

	  @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        mDrawerToggle.syncState();
	    }
	 
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    
	    @Override
	    public void onBackPressed()
	    {
	    	if(getSupportFragmentManager().getBackStackEntryCount() > 0){
	    		getSupportFragmentManager().popBackStack();
	    	}else{
	    		super.onBackPressed();
	    	}
	    	
	    }
}

