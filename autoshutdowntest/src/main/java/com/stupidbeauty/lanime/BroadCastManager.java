package com.stupidbeauty.lanime;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * The broadcast center.
 * @author root Hxcan.
 *
 */
public class BroadCastManager 
{
	

	/**
	 * 发送广播。
	 * @param intent 要发送的广播的意图。
	 */
	private void sendBroadCast(Intent intent) 
	{
		LocalBroadcastManager lclBrdcstMngr=LocalBroadcastManager.getInstance(QueueMateStoreApplication.getAppContext()); //Get the local broadcast manger instance.
		lclBrdcstMngr.sendBroadcast(intent); //发送广播。
	} //private void sendBroadCast(Intent intent)
	
	/**
	 * Send broadcast , wlan info change.
	 */
	public void broadWifiInfoChange() 
	{
		
		Intent intent = new Intent(Constants.NativeMessage.NOTIFY_WIFI_CONN_NUM); //创建意图对象。
		sendBroadCast(intent); //发送广播。
	} //public void broadWifiInfoChange()


	/**
	 * 广播，充电状态发生改变。
	 */
	public void broadCastChargging() 
	{
		
		
		Intent intent = new Intent(Constants.NativeMessage.NOTIFY_CHARGGING_STATE); //创建意图。
		sendBroadCast(intent); //发送广播。
	} //public void broadCastChargging()

	/**
	 * 广播，Gmate信号强度发生变化。
	 */
	public void broadCastGmateSignal() 
	{
		
		
		Intent intent = new Intent(Constants.NativeMessage.NOTIFY_SIGNAL); //创建意图。
		sendBroadCast(intent); //发送广播。
	} //public void broadCastGmateSignal()

	/**
	 * Send broadcast,the apn response status from gmate.
	 * @param isSucc Whether it is set successfully.
	 */
	public void broadCastApnResponseStatus(boolean isSucc) 
	{
		Intent intent = new Intent(Constants.NativeMessage.NOTIFY_APN_RESPONSE_STATUS);
		intent.putExtra(Constants.NativeMessage.NOTIFY_APN_RESPONSE_STATUS, isSucc);
		sendBroadCast(intent);
	} //public void broadCastApnResponseStatus(boolean isSucc)

	/**
	 * 发送广播，应当读取漫游宝的日志。
	 * @param sn 序列号。
	 */
	public void broadCastReadGmateLog(String sn) 
	{
		Intent intent = new Intent(Constants.NativeMessage.NOTIFY_READ_GMATE_LOG); //创建意图。
		intent.putExtra(Constants.NativeMessage.NOTIFY_READ_GMATE_LOG_SN, sn); //加入额外参数，序列号。
		sendBroadCast(intent); //发送广播。
	} //public void broadCastReadGmateLog(String sn)

} //public class BroadCastCenter 

