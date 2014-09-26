package com.ericringer.sfindiefest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericringer.sfindiefest.R;

public class ScheduleFragment extends BaseFragment {
	public ScheduleFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_schedule,container, false));
	}

	@Override
	public void prepareLayout() {


	}
	
	public String getTitle(){
		return getString(R.string.search);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		ScheduleFragment frag = new ScheduleFragment();
		frag.setCallback(callback);
		return frag;
	}

}
