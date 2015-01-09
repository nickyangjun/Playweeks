package com.example.playweeks;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;


public class MyApplication extends Application
{

	private static SharedPreferences mSP;
	public static Resources mResources;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		mSP = getSharedPreferences("config", Context.MODE_PRIVATE);
		mResources =getResources();	
	}
	
	public static SharedPreferences getSharedPreferences()
	{
		return mSP;
	}


	
}
