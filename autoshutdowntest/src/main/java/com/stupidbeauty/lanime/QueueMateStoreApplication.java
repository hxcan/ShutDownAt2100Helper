package com.stupidbeauty.lanime;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 应用程序对象。
 * @author root 蔡火胜。
 *
 */
public class QueueMateStoreApplication extends Application 
{
	@SuppressLint("StaticFieldLeak")
	private static QueueMateStoreApplication sharedInstance; //!<共享实例。
	@SuppressLint("StaticFieldLeak")
	private static Context mContext;


	/**
	 * 程序被创建。
	 */
	public void onCreate()
	{
		super.onCreate(); //创建超类。
		
		sharedInstance=this; //记录共享实例。
		mContext = getApplicationContext(); //获取应用程序上下文。 


	} //public void onCreate()
	
	/**
	 * 获取应用程序上下文。
	 * @return 应用程序上下文。
	 */
	public static Context getAppContext() 
	{ 
		return mContext; 
	}  //public static Context getAppContext()

	
	/**
	 * 程序被终止。
	 */
	public void onTerminate()
	{
		super.onTerminate(); //终止超类。

	} //public void onTerminate()

	/**
	 * 获取共享实例。
	 * @return 共享实例。
	 */
	public static QueueMateStoreApplication getSharedInstance() 
	{
	
		return sharedInstance;
	} //public static QueueMateStoreApplication getSharedInstance()

} //public class QueueMateStoreApplication extends Application
