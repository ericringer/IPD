package com.ericringer.sfindiefest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.parse.ParsePushBroadcastReceiver;

public class PushReciever extends ParsePushBroadcastReceiver {

	@Override
	protected void onPushOpen(Context context, Intent intent) {
		Intent i = new Intent(context,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
	}

	@Override
	protected void onPushReceive(Context context, Intent intent) {
		if(!isPushNotificationsEnabled(context))return;
		super.onPushReceive(context,intent);
	}
	
	private boolean isPushNotificationsEnabled(Context context){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs.getBoolean("pushNotificationsEnabled", true);
	}

}
