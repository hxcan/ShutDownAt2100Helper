package com.stupidbeauty.shutdownat2100.helper;

import com.stupidbeauty.lanime.Constants;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.InputType;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import com.stupidbeauty.shutdownat2100.helper.ShutDownAt2100Manager;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.protobuf.InvalidProtocolBufferException;
// import com.google.zxing.client.android.CaptureActivity;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
// import com.stupidbeauty.exist.ExistMessageContainer;
// import com.stupidbeauty.exist.ServicePublisher;
// import com.stupidbeauty.lanime.callback.ClipboardTextCallback;
// import com.stupidbeauty.lanime.callback.CommitControlCharacterCallback;
// import com.stupidbeauty.lanime.callback.CommitTextCallback;
// import com.stupidbeauty.lanime.callback.HideKeyboardCallback;
// import com.stupidbeauty.lanime.callback.PhoneAvatarCallback;
// import com.stupidbeauty.lanime.callback.PhoneInformationCallback;
import com.stupidbeauty.lanime.network.volley.GsonRequest;
import com.stupidbeauty.lanime.network.volley.MapUtils;
import com.stupidbeauty.lanime.network.volley.VolleyManager;
import com.stupidbeauty.lanime.network.volley.WebServiceResponse;
import com.stupidbeauty.shutdownat2100.AmqpMessage;
import com.stupidbeauty.shutdownat2100.Sda2FunctionName;
import com.stupidbeauty.shutdownat2100.Sda2Message;
import com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage;
import com.stupidbeauty.shutdownat2100androidnative.PreferenceManagerUtil;
// import com.stupidbeauty.shutdownat2100.helper.StopUsingPhoneActivity;
//import com.stupidbeauty.wakeup.Waker;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.stupidbeauty.lanime.Constants.LanImeAction.InputtingForPackage;
import static com.stupidbeauty.lanime.Constants.LanImeAction.PackageNameOfInputting;


/**
 * Example of writing an input method for a soft keyboard.  This code is
 * focused on simplicity over completeness, so it should in no way be considered
 * to be a complete soft keyboard implementation.  Its purpose is to provide
 * a basic example for how you would get started writing an input method, to
 * be fleshed out as appropriate.
 */
public class ShutDownAt2100Manager
{
  private ShutDownAt2100Manager shutDownAt2100Manager; //!< Shut down at 2100 manager.
  private int shutDownHour=-1; //!<关机小时数。
  private int shutDownMinute=-1; //!<关机分钟数。

  private boolean exceededShutDownTime=false; //!<是否已经超过了关机时间。

  /**
    * This boolean indicates the optional example code for performing
    * processing of hard keys in addition to regular text generation
    * from on-screen interaction.  It would be used for input methods that
    * perform language translations (such as converting text entered on 
    * a QWERTY keyboard to Chinese), but may not be used for input methods
    * that are primarily intended to be used for on-screen text entry.
    */
  static final boolean PROCESS_HARD_KEYS = true;

  private static final String TAG = "SoftKeyboard"; //!<输出调试信息时使用的标记。

  private static final String LanServiceProtocolType = "HTTP"; //!< 服务协议类型是HTTP。

  private static final int LanServicePort = 15563;
  private static final int udpPort=16574; //!<手机端监听的UDP端口号。

  private static final String LanServiceName = "LanImeAndroid"; //!<局域网服务名字。

  private InputMethodManager mInputMethodManager;

  private CompletionInfo[] mCompletions;
  private MediaPlayer mediaPlayer;
  
  private StringBuilder mComposing = new StringBuilder(); //!<正在编辑的内容。
  private Context context; //!< The context.
  private boolean mCompletionOn;
  private int mLastDisplayWidth;
  private boolean mCapsLock;
  private long mLastShiftTime;
  private long mMetaState;
    
  private static final float BEEP_VOLUME = 0.20f;
  private String mWordSeparators; //!<单词分隔符集合字符串。

  protected String callbackIp="127.0.0.1"; //!<回调的IP。
  private RequestQueue mQueue; //!<Volley请求队列。

  protected Set<Long> committedTransactionIdSet=new HashSet<Long>(); //!<已提交的事务编号集合。
	
	/**
	 * Constructor.
	 */
	public ShutDownAt2100Manager(Context context)
	{
      this.context=context;
	} //private void startUdpServer()

	/**
	 * 广播接收器。
	 */
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() 
	{
		@Override
		/**
		 * 接收到广播。
		 */
		public void onReceive(Context context, Intent intent) 
		{
			String action = intent.getAction(); //获取广播中带的动作字符串。
			
			if (Constants.Operation.CommitText.equals(action)) //提交文本内容。 
			{
				Bundle extras=intent.getExtras(); //获取参数包。
				String textToCommit=extras.getString("text"); //获取要提交的文本内容。

				boolean hasTransactionId=false; //是否拥有事务编号。
				long transactionId=0; //事务编号。

				if (extras.containsKey("transactionId")) //传入了事务编号参数。
				{
					transactionId=extras.getLong("transactionId"); //获取事务编号。
					
					hasTransactionId=true; //拥有事务编号。
				} //if (extras.containsKey("transactionId")) //传入了事务编号参数。

				boolean beep=true;

				if (extras.containsKey("beep")) //响声
				{
					beep=extras.getBoolean("beep");
				} //if (extras.containsKey("beep")) //响声
			} //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
			else if (Constants.NativeMessage.NOTIFY_CALLBACK_IP.equals(action)) //报告回调IP。 
			{
				Bundle extras=intent.getExtras(); //获取参数包。
				callbackIp=extras.getString("callbackIp"); //获取回调IP。
			} //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
			else	if (Constants.NativeMessage.NOTIFY_WIFI_CONN_NUM.equals(action)) //无线网发生变化。 
			{
				checkUpdate(); //检查更新。
			} //if (Constants.NativeMessage.NOTIFY_CHARGGING_STATE.equals(action)) //电池充电状态变化。
		} //public void onReceive(Context context, Intent intent)
	}; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()

	/**
	 * 记住远端的IP。
	 */
	private void rememberRemoteIp()
	{
	} //private void rememberRemoteIp()

	private String clipboardText=""; //!<剪贴板文字内容。
	
	/**
	 * 检查更新。
	 */
	protected void checkUpdate() 
	{
      Log.d(TAG,"checkUpdate,reporting new ip."); //Debug.
      requestConnectMe(callbackIp); //请求电脑连接到本设备。
	} //protected void checkUpdate()
	
	/**
	   * 请求电脑端连接到本手机。
	   * @param text 电脑端的IP。
	   */
	  private void requestConnectMe(String text) 
	  {
		  String gsonUrl = "http://"+text+":18122/connectMe/"; //The gson request string.最新版本号。
		  
		  Log.d(TAG,"requestConnectMe,gsonUrl:"+gsonUrl); //Debug.
			
		  GsonRequest<WebServiceResponse> gsonRequest=new GsonRequest<WebServiceResponse>
		  (
				  com.android.volley.Request.Method.GET,
				  gsonUrl,
				  WebServiceResponse.class,
				  new Response.Listener<WebServiceResponse>() {
					  public void onResponse(WebServiceResponse response) {

						  parseSubmitPasswordResponse(response); //解析请求结果。
					  }
				  },
				  new Response.ErrorListener() 
				  {
					  @Override
					  public void onErrorResponse(VolleyError error) 
					  {
						  Log.e(TAG,error.getMessage(),error);
					  } //public void onErrorResponse(VolleyError error)
				  } //new Response.ErrorListener()
				  );

		  mQueue.add(gsonRequest); //添加请求。
	  } //private void requestConnectMe(String text)

	/**
	 * 启动友军“21点关机”的服务。
	 */
	protected void startFriendShutDownAt2100Service() 
	{
		//com.stupidbeauty.shutdownat2100androidnative.TimeCheckService
		
		Intent intentt = new Intent();
		intentt.setComponent(new ComponentName("com.stupidbeauty.shutdownat2100androidnative", "com.stupidbeauty.shutdownat2100androidnative.TimeCheckService")); //设置组件。

		try
		{
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //前台服务
			{
              context.startForegroundService(intentt); //启动前台服务
			} //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //前台服务
			else //没有前台服务
			{
              context.startService(intentt); //启动服务。
			} //else //没有前台服务
		}
		catch (IllegalStateException e) //不允许启动服务。
		{
			e.printStackTrace();
		} //catch (IllegalStateException e)

		try
		{
          Intent intenttFloating = new Intent();
          intenttFloating.setComponent(new ComponentName("com.stupidbeauty.shutdownat2100androidnative", "com.example.lzc.floatingwindowdemo.service.FloatingService")); //设置组件。

          context.startService(intenttFloating); //启动服务。
		}
		catch (IllegalStateException e) //不允许启动服务。
		{
			e.printStackTrace(); //报告错误。
		} //catch (IllegalStateException e) //不允许启动服务。
		catch (SecurityException e) //安全异常
		{
			e.printStackTrace(); //报告错误。
		} //catch (SecurityException e) //安全异常
	} //protected void startFriendShutDownAt2100Service()

	/**
	 * 载入21点关机的配置信息。
	 */
	private void loadShutDownAt2100Configuration()
	{
      File goddessCameraDirectory=new File(Constants.DirPath.FARMING_BOOK_APP_SD_CARD_PATH); //女神相机目录。

      goddessCameraDirectory.mkdirs(); //创建目录。

      File configurationFile=new File(goddessCameraDirectory, "shutdownat2100.oot"); //配置文件对象。

      try //尝试读取内容
      {
        byte[] messageContent= FileUtils.readFileToByteArray(configurationFile); //读取内容。

        Sda2Message amqpMessage= Sda2Message.parseFrom(messageContent); //解析消息。

        ShutDownAt2100ConfigurationMessage commitTextMessage=amqpMessage.getShutDownAt2100ConfigurationMessage(); //获取提交文字消息。

        Sda2FunctionName functionName=amqpMessage.getFunctionName(); //

        //获取关机小时数。
        shutDownHour = commitTextMessage.getHour();
        //获取关机分钟数。
        shutDownMinute = commitTextMessage.getMiniute();


        PreferenceManagerUtil.setShutdownHour(shutDownHour);
        PreferenceManagerUtil.setShutdownMinute(shutDownMinute);
      } //try //尝试读取内容
      catch (IOException e) //文件不存在
      {
        shutDownHour=PreferenceManagerUtil.getShutdownHour();
        shutDownMinute=PreferenceManagerUtil.getShutdownMinute();

        e.printStackTrace();
      } //catch (IOException e) //文件不存在
	} //private void loadShutDownAt2100Configuration()

	/**
	 * 检查关机时间。
	 */
	private void checkShutDownTime()
	{
      //是否超过了关机时间。

      checkWhetherExceededShutDownTime(); //检查，是否超过了关机时间。

      if (exceededShutDownTime) //是超过了关机时间。
      {
        executeShutDown(); //执行关机过程。
      } //if (exceededShutDownTime) //是超过了关机时间。
	} //private void checkShutDownTime()

	/**
	 * 执行关机过程。
	 */
	private void executeShutDown()
	{
      Intent launchIntent=new Intent(); //获取当前软件包的启动意图。
      launchIntent.setAction(Intent.ACTION_MAIN); //设置动作。

      String packageName="com.stupidbeauty.shutdownat2100androidnative"; //21点关机。
      String serviceName="com.stupidbeauty.shutdownat2100androidnative.StopUsingPhoneActivity"; //21点关机。

      ComponentName cn = new ComponentName(packageName, serviceName);
      launchIntent.setComponent(cn);

      launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标志位。

      try
      {
        context.startActivity(launchIntent); // 启动 stop using phone 窗口。
      } //
      catch (ActivityNotFoundException e)
      {
        e.printStackTrace();
      } //catch (ActivityNotFoundException e)
	} //private void executeShutDown()

	/**
	 * 检查，是否超过了关机时间。
	 */
	private void checkWhetherExceededShutDownTime()
	{
	    Log.d(TAG, "checkWhetherExceededShutDownTime, shut down time: " + shutDownHour + ", " + shutDownMinute); //Debug.

		if (shutDownHour>=0) //载入了有效的关机时间。
		{
			GregorianCalendar t=new GregorianCalendar(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。


			GregorianCalendar thresholdTime=new GregorianCalendar(t.get(GregorianCalendar.YEAR),t.get(GregorianCalendar.MONTH),t.get(GregorianCalendar.DATE),shutDownHour,shutDownMinute); //阈值时间。

			if (t.after(thresholdTime)) //时间比阈值时间还要晚。
			{
				exceededShutDownTime=true;
			} //if (t.after(thresholdTime)) //时间比阈值时间还要晚。

		} //if (shutDownHour>=0) //载入了有效的关机时间。



	} //private void checkWhetherExceededShutDownTime()


	/**
	 * 报告正在输入普通文字。
	 */
	private void reportInputtingNormalText() 
	{
		Map<String,String> map = new HashMap<String, String>();

		map.put("inputType", "text"); //Add the parameter , 输入类型是文字.

		String gsonUrl = "http://"+callbackIp+":18122/reportInputType/?"+MapUtils.toUrlGetString(map); //The gson request string.最新版本号。

		GsonRequest<WebServiceResponse> gsonRequest=new GsonRequest<WebServiceResponse>
		(
				com.android.volley.Request.Method.GET,
				gsonUrl,
			WebServiceResponse.class,
			new Response.Listener<WebServiceResponse>() {
				public void onResponse(WebServiceResponse response) {

					parseSubmitPasswordResponse(response); //解析请求结果。
				}
			},
			new Response.ErrorListener()
			{
				@Override
				public void onErrorResponse(VolleyError error)
				{
					Log.e(TAG,error.getMessage(),error);
				} //public void onErrorResponse(VolleyError error)
			} //new Response.ErrorListener()
			);

			mQueue.add(gsonRequest); //添加请求。
	} //private void reportInputtingNormalText()
	
	/**
	 * 解析提交密码信息的结果。
	 * @param weatherInfo 结果对象。
	 */
	protected void parseSubmitPasswordResponse(WebServiceResponse weatherInfo) 
	{
		if (weatherInfo.isSuccess()) //请求成功了。
		{
		} //if (weatherInfo.isSuccess()) //请求成功了。
		else //服务器返回结果为失败。
		{
		} //else //服务器返回结果为失败。
	} //protected void parseSubmitPasswordResponse(PhoneRegisterResponse weatherInfo)


	/**
	 * 报告正在输入密码。
	 */
	private void reportInputtingPassword() 
	{
		Map<String,String> map = new HashMap<String, String>();

		map.put("inputType", "password"); //Add the parameter , 输入类型是密码.

		String gsonUrl = "http://"+callbackIp+":18122/reportInputType/?"+MapUtils.toUrlGetString(map); //The gson request string.最新版本号。
		
		Log.d(TAG,"reportInputtingPassword,gsonUrl:"+gsonUrl); //Debug.
		
		GsonRequest<WebServiceResponse> gsonRequest=new GsonRequest<WebServiceResponse>
		(
				com.android.volley.Request.Method.GET,
				gsonUrl,
			WebServiceResponse.class,
			new Response.Listener<WebServiceResponse>() 
			{
				public void onResponse(WebServiceResponse response) 
				{

					parseSubmitPasswordResponse(response); //解析请求结果。
				}
			},
			new Response.ErrorListener() 
			{
				@Override
				public void onErrorResponse(VolleyError error) 
				{
					Log.e(TAG,error.getMessage(),error);
				} //public void onErrorResponse(VolleyError error)
			} //new Response.ErrorListener()
			);
		
		mQueue.add(gsonRequest); //添加请求。


	} //private void reportInputtingPassword()

	/**
	 * 是否正在输入密码。
	 * @param attribute 输入法属性。
	 * @return 是否正在输入密码。
	 */
	private boolean isInputingPassword(EditorInfo attribute) 
    {
		Log.d(TAG,"onStartInput,输入类型："+attribute.inputType+",TYPE_MASK_VARIATION之后："+(attribute.inputType & InputType.TYPE_MASK_VARIATION)+",variation password:"+InputType.TYPE_TEXT_VARIATION_PASSWORD+","+InputType.TYPE_NUMBER_VARIATION_PASSWORD+","+InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD); //Debug.
		
		int inputType=attribute.inputType; //获取输入类型。
		int masked=inputType & InputType.TYPE_MASK_VARIATION; //做掩码计算。
		
		boolean result=false; //不是在输入密码。
		if ((masked & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) //文本类型密码。
		{
			result=true; //是在输入密码。
		} //if ((masked & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) //文本类型密码。
		else if ((masked & InputType.TYPE_NUMBER_VARIATION_PASSWORD) == InputType.TYPE_NUMBER_VARIATION_PASSWORD) //数字类型密码。
		{
			result=true; //是在输入密码。
		} //else if ((masked & InputType.TYPE_NUMBER_VARIATION_PASSWORD) == InputType.TYPE_NUMBER_VARIATION_PASSWORD) //数字类型密码。
		else if ((masked & InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) //网页类型密码。
		{
			result=true; //是在输入密码。
		} //else if ((masked & InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) //网页类型密码。
	
		return result;
	} //private boolean isInputingPassword(EditorInfo attribute)

    /**
     * Helper to determine if a given character code is alphabetic.
     */
    private boolean isAlphabet(int code) {
		return Character.isLetter(code);
    }
    
    private void checkToggleCapsLock() {
        long now = System.currentTimeMillis();
        if (mLastShiftTime + 800 > now) {
            mCapsLock = !mCapsLock;
            mLastShiftTime = 0;
        } else {
            mLastShiftTime = now;
        }
    }
    
    /**
     * 获取单词分隔符集合字符串。
     * @return 单词分隔符集合字符串。
     */
    private String getWordSeparators() 
    {
        return mWordSeparators;
    } //private String getWordSeparators()
    
    /**
     * 判断指定的代码是不是单词分隔符。
     * @param code 要判断的代码。
     * @return 是否是单词分隔符。
     */
    public boolean isWordSeparator(int code) 
    {
        String separators = getWordSeparators(); //获取单词分隔符集合字符串。
        return separators.contains(String.valueOf((char)code)); //包含，则是的。
    } //public boolean isWordSeparator(int code)

    public void swipeUp() {
    }
    
    public void onPress(int primaryCode) {
    }
    
    public void onRelease(int primaryCode) {
    }

    /**
     * 获取剪贴板文字。
     * @return 剪贴板文字。
     */
	public String getClipboardText() 
	{
	
		String result=""; //结果。
		
		result=clipboardText; //记录结果。
		
		return result;
	} //public String getClipboardText()
} //public class SoftKeyboard extends InputMethodService  implements KeyboardView.OnKeyboardActionListener