package com.demo.orders.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.demo.orders.activity.SplashActivity;
import com.demo.orders.helper.CommonClass;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class SessionManagerLogin {
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    // Sharedpref file name
    private static final String PREF_NAME = "Android";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context context;
    CommonClass commonClass;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManagerLogin(Context context) {
        this.context = context;
        commonClass = new CommonClass(context);
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String name, String email) {
        // Storing login value as TRUE

        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user_not_used login status
     * If false it will redirect user_not_used to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user_not_used is not logged in redirect him to Login Activity
            Intent i = new Intent(context, SplashActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }
    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user_not_used name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user_not_used email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user_not_used
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user_not_used to Login Activity
        Intent i = new Intent(context, SplashActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppCompatActivity activity = commonClass.convertActivity(context);
        // Staring Login Activity
        activity.startActivity(i);
        //Finish the Entire Previous Screens and move to login Screen
        activity.finishAffinity();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
