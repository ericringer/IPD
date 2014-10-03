package com.ericringer.sfindiefest.fragments;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BaseFragment extends Fragment {
	FragmentCallback callback;
	private View fragmentView;
	protected abstract String getTitle();
	protected abstract void prepareLayout();
	
	public BaseFragment() {
	}
	public Intent getIntent(){
		return getActivity().getIntent();
	}
	public Context getApplicationContext(){
		if(getActivity() == null)return null;
		return getActivity().getApplicationContext();
	}
	public View findViewById(int resourceID){
		if(fragmentView == null)return null;
		return fragmentView.findViewById(resourceID);
	}
	public View getFragmentView() {
		return fragmentView;
	}
	public View prepareAndSetView(View view) {
		setRetainInstance(true);
		fragmentView = view;
		prepareLayout();
		return view;
	}
	public static Fragment getInstance(Class<BaseFragment> fragmentClass,FragmentCallback callback) {
			BaseFragment frag = null;
			try {
				frag = fragmentClass.newInstance();
				frag.setCallback(callback);
			} catch (java.lang.InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return frag;
		}
	public void setCallback(FragmentCallback callback) {
		this.callback = callback;
	}
	public interface FragmentCallback{
		public void openDrawer();
		public void switchFragment(Fragment fragment);
	}
	@Override
	public void onResume() {
		getActivity().setTitle(getTitle());
		super.onResume();
	}
	
	
}
