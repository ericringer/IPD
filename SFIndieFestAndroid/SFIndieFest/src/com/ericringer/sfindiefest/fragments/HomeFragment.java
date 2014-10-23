package com.ericringer.sfindiefest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ericringer.sfindiefest.R;

public class HomeFragment extends BaseFragment {
	public HomeFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_home,
				container, false));
	}

	@Override
	public void prepareLayout() {
		findViewById(R.id.logo).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callback.openDrawer();
			}
		});
	}
	
	public String getTitle(){
		return getString(R.string.app_name);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		HomeFragment frag = new HomeFragment();
		frag.setCallback(callback);
		return frag;
	}

}
