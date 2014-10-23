package com.ericringer.sfindiefest.types;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.LoggingBehavior;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.Settings;

public class FacebookHelper {
	private Activity mActivity;
	private String TAG = getClass().getSimpleName();

	public Session.StatusCallback statusCallback;
	private FacebookLoginSuccessListener fbListener;
	public FacebookHelper(Activity activity) {
		mActivity = activity;
		statusCallback = new SessionStatusCallback();
	}

	public void checkForFacebook(Bundle savedInstanceState) {
		try {
			Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
			Session session = Session.getActiveSession();

			if (session == null) {
				if (savedInstanceState != null) {
					session = Session.restoreSession(mActivity, null,
							statusCallback, savedInstanceState);
				}
				
				if (session == null) {
					session = new Session(mActivity);
				}
				
				Session.setActiveSession(session);
				if (session.getState()
						.equals(SessionState.CREATED_TOKEN_LOADED)) {
					Session.getActiveSession().openForPublish(new Session.OpenRequest(mActivity) .setCallback(statusCallback).setPermissions(Arrays.asList("publish_actions")));
				}
			}
		} catch (Exception e) {
			Log.d(TAG, "Process Login Error: " + e.toString());
		}
	}
	public void facebookLogin(FacebookLoginSuccessListener fbListener) {
		this.fbListener = fbListener;
		Settings.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
		Session session = Session.getActiveSession();
		if (!session.isOpened() && !session.isClosed()) {
			session.openForRead(new Session.OpenRequest(mActivity).setCallback(statusCallback));
		} else {
			Session.openActiveSession(mActivity, true, statusCallback);
		}

	}
	
	public void handleOnSaveInstance(Bundle outState) {
		Session session = Session.getActiveSession();
		Session.saveSession(session, outState);
		
	}
	public void shareToFacebook(final String comment){
		facebookLogin(new FacebookLoginSuccessListener(){

			@Override
			public void onFacebookLogin(String fbID) {
				Session.getActiveSession().close();
				Session.setActiveSession(new Session(mActivity));
				Session.getActiveSession().openForPublish(new Session.OpenRequest(mActivity) .setCallback(new StatusCallback(){
					@Override
					public void call(Session session, SessionState state,Exception exception) {
						if (!session.getAccessToken().contentEquals("")) {
							if (!session.getPermissions().contains("publish_actions")) {
								return;
							}
							
				     	Request.newStatusUpdateRequest(Session.getActiveSession(), comment, new com.facebook.Request.Callback(){

							@Override
							public void onCompleted(com.facebook.Response response) {
								if(response.getError() !=null){
									Toast.makeText(mActivity, response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
								}else{
									Toast.makeText(mActivity, "Posted to Facebook", Toast.LENGTH_LONG).show();
								}
								
							}}).executeAsync();	
						}
				     	
					}}).setPermissions(Arrays.asList("publish_actions")));
				
		 			
			}});
	}
	private class SessionStatusCallback implements Session.StatusCallback {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {

			if(exception!=null){
				Log.e(TAG, exception.toString(),exception);
				return;
			}
			
			if (!session.getAccessToken().contentEquals("")) {
				if(fbListener!=null)fbListener.onFacebookLogin(session.getAccessToken());
			}
		}
	}
	
	public void handleOnStart(){
		Session.getActiveSession().addCallback(statusCallback);
	    
	}

	public void handleOnStop(){
		Session.getActiveSession().removeCallback(statusCallback);
	}
	
	public void hanldeActivityResult(int requestCode, int resultCode, Intent data) {
		 Session.getActiveSession().onActivityResult(mActivity, requestCode, resultCode, data);
	}
	
	public interface FacebookLoginSuccessListener{
		public void onFacebookLogin(String fbID);
	}
	
}
