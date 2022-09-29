package com.stupidbeauty.lanime;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.DataSink;

/**
 * @author yexiaoming
 * 在后台做事的服务。
 */
public class LanImeService extends Service  
{
	public static final String TAG = "LanImeService"; //!<The tag used to output debug info.
	private static LanImeService mSimoUIController = new LanImeService(); //!<创建单实例。
	private static RequestQueue mVolleyQueue; //!<volley请求队列。
	
	private IBinder mBinder = new LocalBinder();
	public TelephonyManager mTelephonyManager = null;
	private Handler mHandler = new Handler(); //!<多线程通信的处理器。
	
	
	
	private boolean mHasShowAutoCheckApkUpdateDialog; //!<是否已经显示过自动检查更新结果的对话框了。
	private boolean haveRegisteredBroadcastReceiver=false; //!<是否已经注册了广播事件接收器。
	private BroadCastManager mBroadCastCenter=new BroadCastManager(); //!<创建广播中心。


	/**
	 * 此服务对象被创建。
	 */
	@Override
	public void onCreate() 
	{
		super.onCreate(); //创建超类。
		
		
		mSimoUIController = this; //记录单实例。

		mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	} //public void onCreate()

	/**
	 * 获取服务对象的单实例。
	 * @return 服务对象的单实例。
	 */
	public static LanImeService shareInstance() 
	{
		return mSimoUIController;
	} //public static SimoMateService shareInstance()

	/**
	 * 本地绑定器。
	 * @author root 蔡火胜。
	 *
	 */
	public class LocalBinder extends Binder 
	{

		/**
		 * 获取服务实例。
		 * @return 服务实例。
		 */
		public LanImeService getService() 
		{
			return LanImeService.this;
		} //public SimoMateService getService()

	} //public class LocalBinder extends Binder

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	
	

	@Override
	/**
	 * 服务被启动。也可能是重新启动。
	 */
	public int onStartCommand(Intent intent, int flags, int startId) 
	{
		if (haveRegisteredBroadcastReceiver) //已经注册了广播事件接收器。
		{
		} //if (haveRegisteredBroadcastReceiver) //已经注册了广播事件接收器。
		else //还未注册广播事件接收器。
		{
			haveRegisteredBroadcastReceiver=true; //现在已经注册了。
		} //else //还未注册广播事件接收器。
		
		return START_STICKY; //被杀死时，自动重启。
	} //public int onStartCommand(Intent intent, int flags, int startId)
}
