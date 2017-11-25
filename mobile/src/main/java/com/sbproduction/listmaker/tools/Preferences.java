package com.sbproduction.listmaker.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

public class Preferences 
{
	private static Context mContext = null;
	private static SharedPreferences mPreferences = null;
	private static SharedPreferences.Editor mEditor = null;
	private static final Handler mCommitHandler = new Handler();

	private static final String kPreferencesFileKey = "listmaker_preferences";
	private static final String kIsFirstUseKey = "first_time_use";
	private static final String kAutoCompleteKey = "auto_complete_enabled";
	private static final String kSwipeToDeleteKey = "swipe_to_delete_enabled";
	private static final String kSplashScreenKey = "splash_screen_enabled";
	private static final String kAppVersionKey = "app_version";
	private static final String kSessionTokenKey = "session_token";
	private static final String kUsernameKey = "username";
	private static final String kAuthId = "auth_id";
	private static final String kPasswordKey = "password";
	
	public static void init(Context context)
	{
		mContext = context;
		
		mPreferences = mContext.getSharedPreferences(kPreferencesFileKey, Context.MODE_PRIVATE);
		mEditor = mPreferences.edit();
	}
	
	public static void setIsFirstTimeUse(boolean isFirstTime)
	{
		mEditor.putBoolean(kIsFirstUseKey, isFirstTime);
		mCommitHandler.post(commitChanges);
	}
	
	public static boolean getIsFirstTimeUse()
	{
		//if it hasn't been set that is because it is the first time, return true
		return mPreferences.getBoolean(kIsFirstUseKey, true);
	}

	public static void setAutoCompleteEnabled(boolean enabled)
	{
		mEditor.putBoolean(kAutoCompleteKey, enabled);
		mCommitHandler.post(commitChanges);
	}

	public static boolean getAutoCompleteEnabled()
	{
		//by default have auto complete be off
		return mPreferences.getBoolean(kAutoCompleteKey, true);
	}

	public static void setSwipeToDeleteEnabled(boolean enabled)
	{
		mEditor.putBoolean(kSwipeToDeleteKey, enabled);
		mCommitHandler.post(commitChanges);
	}

	public static boolean getSwipeToDeleteEnabled()
	{
		//by default swipe to delete is on
		return mPreferences.getBoolean(kSwipeToDeleteKey, true);
	}

	public static void setSplashScreenEnabled(boolean enabled)
	{
		mEditor.putBoolean(kSplashScreenKey, enabled);
		mCommitHandler.post(commitChanges);
	}

	public static boolean getSplashScreenEnabled()
	{
		//by default splash screen is shown
		return mPreferences.getBoolean(kSplashScreenKey, true);
	}

	public static void setAppVersion(float version)
	{
		mEditor.putFloat(kAppVersionKey, version);
		mCommitHandler.post(commitChanges);
	}

	public static float getAppVersion()
	{
		//by default swipe to delete is on
		return mPreferences.getFloat(kAppVersionKey, 1.0f); //first introduced so default to 1.0 if there is no value set
	}

	public static void setSessionToken(String token)
	{
		mEditor.putString(kSessionTokenKey, token);
		mCommitHandler.post(commitChanges);
	}

	public static String getSessionToken()
	{
		return mPreferences.getString(kSessionTokenKey, "");
	}

	public static void setUsername(String username)
	{
		mEditor.putString(kUsernameKey, username);
		mCommitHandler.post(commitChanges);
	}

	public static String getAuthId()
	{
		return mPreferences.getString(kAuthId, "");
	}

	public static void setAuthId(String authId)
	{
		mEditor.putString(kAuthId, authId);
		mCommitHandler.post(commitChanges);
	}

	public static String getUsername()
	{
		return mPreferences.getString(kUsernameKey, "");
	}

	public static void setPassword(String password)
	{
		mEditor.putString(kPasswordKey, password);
		mCommitHandler.post(commitChanges);
	}

	public static String getPassword()
	{
		return mPreferences.getString(kPasswordKey, "");
	}
	
	private static final Runnable commitChanges = new Runnable()
	{
		public void run()
		{
			mEditor.commit();
		}
	};
}
