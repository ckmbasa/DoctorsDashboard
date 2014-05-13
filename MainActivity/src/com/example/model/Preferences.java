package com.example.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Preferences extends HelperSharedPreferences{
	
	private  ConnectivityManager connectivityManager;
	
	public boolean isNetworkAvailable(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    
	    // if no network is available networkInfo will be null
	    // otherwise check if we are connected
	    
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    }
	    
	    return false;
	} 

}