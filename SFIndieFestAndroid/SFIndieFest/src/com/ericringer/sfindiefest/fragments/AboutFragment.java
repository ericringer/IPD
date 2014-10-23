package com.ericringer.sfindiefest.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import com.ericringer.sfindiefest.R;

public class AboutFragment extends BaseFragment {
	public AboutFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_about,container, false));
	}

	@Override
	public void prepareLayout() {
		ToggleButton togglePushNotifications = (ToggleButton) findViewById(R.id.togglePushNotifications);
		togglePushNotifications.setChecked(isPushNotificationsEnabled());
		togglePushNotifications.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				setPushNotificationsEnabled(isChecked);
			}});

	}
	private boolean isPushNotificationsEnabled(){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		return prefs.getBoolean("pushNotificationsEnabled", true);
	}
	protected void setPushNotificationsEnabled(boolean isChecked) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor editor = prefs.edit();
		editor.putBoolean("pushNotificationsEnabled", isChecked);
		editor.commit();
	}

	public String getTitle(){
		return getString(R.string.about);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		AboutFragment frag = new AboutFragment();
		frag.setCallback(callback);
		return frag;
	}

}
