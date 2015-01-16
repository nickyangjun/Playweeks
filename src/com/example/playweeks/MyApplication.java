package com.example.playweeks;


import cn.bmob.v3.Bmob;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;


public class MyApplication extends Application
{

	// SDK的初始化建议放在Application中
    public static String APPID = "b654a83b942eb329d4882f9b90779634";
    
	private static SharedPreferences mSP;
	public static Resources mResources;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		Bmob.initialize(getApplicationContext(),APPID);
		
		mSP = getSharedPreferences("config", Context.MODE_PRIVATE);
		mResources =getResources();	
	}
	
	public static SharedPreferences getSharedPreferences()
	{
		return mSP;
	}


	
}
