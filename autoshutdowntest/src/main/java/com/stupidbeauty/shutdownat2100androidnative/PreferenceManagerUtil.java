package com.stupidbeauty.shutdownat2100androidnative;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

// import com.stupidbeauty.lanime.QueueMateStoreApplication;

public class PreferenceManagerUtil {

	/**
	 * 保存客户端编号。
	 * @param clientId 要保存的客户端编号。
	 */
	public static void setShutdownHour(int clientId, Context context)
	{
      Context ct = context; // 获取应用程序上下文。
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
      sp.edit().putInt(Constants.Common.SHUTDOWN_HOUR, clientId).apply(); //保存。
	} //public static void set

    /**
     * 获取关机时间的小时数。
     * @return 关机时间的小时数。
     */
    public static int getShutdownHour(Context context)
    {
      Context ct = context; // 获取应用程序上下文。
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取默认共享配置对象。
      return sp.getInt(Constants.Common.SHUTDOWN_HOUR, 21);
    } //public static String get

	/**
	 * 获取关机时间的分钟数。
	 * @return 关机时间的分钟数。
	 */
	public static int getShutdownMinute(Context context)
	{
      Context ct = context; // 获取应用程序上下文。
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取默认共享配置对象。
      return sp.getInt(Constants.Common.SHUTDOWN_MINUTE, 45);
	} //public static String get

	/**
	 * 保存客户端编号。
	 * @param clientId 要保存的客户端编号。
	 */
	public static void setShutdownMinute(int clientId, Context context)
	{
      Context ct = context; // 获取应用程序上下文。
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
      sp.edit().putInt(Constants.Common.SHUTDOWN_MINUTE, clientId).apply(); //保存。
	} //public static void set
}
