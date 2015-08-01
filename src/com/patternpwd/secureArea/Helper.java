package com.patternpwd.secureArea;

import android.content.Context;
import android.content.SharedPreferences;

public class Helper {

	public SharedPreferences getPrefs(Context context) {

		return context.getSharedPreferences(
				"patternPasswordFile",
				Context.MODE_PRIVATE);
	}

	public String getMyStringPref(Context context, String key) {
		return getPrefs(context).getString(key, "");
	}

	public void setMyStringPref(Context context, String key, String value) {
		getPrefs(context).edit().putString(key, value).commit();
	}
}
