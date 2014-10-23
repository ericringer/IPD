package com.ericringer.sfindiefest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "kPhozHy1njErQcthAr8UuQ5w1sVU97afMYSK2rLn", "kKIr72h6A7Nl1UtCBU8WBZDxQQVKtygIDcWrHtqd");
		ParseInstallation.getCurrentInstallation().saveInBackground();
		ParseUser.enableAutomaticUser();
	}
}
