package com.stupidbeauty.shutdownat2100.helper;

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
import com.upokecenter.cbor.CBORObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;
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
// import com.android.volley.RequestQueue;
// import com.android.volley.Response;
// import com.android.volley.VolleyError;
import com.google.protobuf.InvalidProtocolBufferException;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
// import com.stupidbeauty.shutdownat2100.AmqpMessage;
import com.stupidbeauty.shutdownat2100.Sda2FunctionName;
import com.stupidbeauty.shutdownat2100.Sda2Message;
import com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage;
import com.stupidbeauty.shutdownat2100androidnative.PreferenceManagerUtil;
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
  private long lastCheckShutDownTimeMilliseconds=0; //!< The time stamp of last time doing the check.
  private boolean exceededShutDownTime=false; //!< If we have exceeded the shut down time.
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
  protected Set<Long> committedTransactionIdSet=new HashSet<Long>(); //!<已提交的事务编号集合。
	
	/**
	 * 写入关机时间到外置存储。
	 * @param clock_time 关机的小时数。
	 * @param ClkMnt 关机的分钟数。
	 */
	public void writeShutDownTimeToExternalStorage(int clock_time, int ClkMnt)
	{
    Log.d(TAG, "writeShutDownTimeToExternalStorage, shut down time: "+ clock_time + ", " + ClkMnt); //Debug.

    Sda2Message translateRequestBuilder = new Sda2Message(); //创建消息构造器。
    ShutDownAt2100ConfigurationMessage shutDownAt2100ConfigurationBuilder=new ShutDownAt2100ConfigurationMessage (); //创建构造器。

		shutDownAt2100ConfigurationBuilder.setHour(clock_time); //设置小时数。
		shutDownAt2100ConfigurationBuilder.setMinute(ClkMnt); // Set minute.

		translateRequestBuilder.setFunctionName(Sda2FunctionName.ShutDownAt2100Configuration); //设置函数名字，配置文件。

		translateRequestBuilder.setShutDownAt2100ConfigurationMessage(shutDownAt2100ConfigurationBuilder); //设置关机消息。

		// final byte[] messageContent=translateRequestBuilder.build().toByteArray();//序列化成字节数组。

    CBORObject cborObject= CBORObject.FromObject(translateRequestBuilder); //创建对象

    byte[] messageContent=cborObject.EncodeToBytes();

    // String arrayString=new String(array);

		
		//写入文件：
		File goddessCameraDirectory=new File(Constants.DirPath.FARMING_BOOK_APP_SD_CARD_PATH); //女神相机目录。

		goddessCameraDirectory.mkdirs(); //创建目录。

		File configurationFile=new File(goddessCameraDirectory, "shutdownat2100.oot"); //配置文件对象。

		try
		{
			FileUtils.writeByteArrayToFile(configurationFile, messageContent); //写入到文件中去。
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	} //private void writeShutDownTimeToExternalStorage(int clock_time, int ClkMnt)

  /**
  * Constructor.
  */
  public ShutDownAt2100Manager(Context context)
  {
    this.context=context;
      
    loadShutDownAt2100Configuration(); //载入21点关机的配置信息。

    checkShutDownTime(); //检查关机时间。
  } //private void startUdpServer()

	private String clipboardText=""; //!<剪贴板文字内容。
	
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

        // Sda2FunctionName functionName=amqpMessage.getFunctionName(); //

        //获取关机小时数。
        shutDownHour = commitTextMessage.getHour();
        //获取关机分钟数。
        shutDownMinute = commitTextMessage.getMinute();


        PreferenceManagerUtil.setShutdownHour(shutDownHour,context);
        PreferenceManagerUtil.setShutdownMinute(shutDownMinute,context);
      } //try //尝试读取内容
      catch (IOException e) //文件不存在
      {
        if (context!=null)
        {
          shutDownHour=PreferenceManagerUtil.getShutdownHour(context);
          shutDownMinute=PreferenceManagerUtil.getShutdownMinute(context);
        } // if (context!=null)
      
        e.printStackTrace();
      } //catch (IOException e) //文件不存在
	} //private void loadShutDownAt2100Configuration()
	
	/**
	*  Remember , ever installed shut down at 2100.
	*/
	public void setEverInstalledShutDownAt2100()
	{
    PreferenceManagerUtil.setEverInstalledShutDownAt2100(context);
	} // public void setEverInstalledShutDownAt2100()
	
	/**
	* Check , if installed shut down at 2100 ever.
	*/
	public boolean getEverInstalledShutDownAt2100()
	{
    boolean shutDownHour=PreferenceManagerUtil.getEverInstalledShutDownAt2100(context);
    
    return shutDownHour;
	} // public boolean getEverInstalledShutDownAt2100()

	/**
	 * 检查关机时间。
	 */
	public void checkShutDownTime()
	{
    //是否超过了关机时间。

    long now = System.currentTimeMillis();

    if ((now-lastCheckShutDownTimeMilliseconds)>=(1*60*1000)) // only check once every minute
    {
      checkWhetherExceededShutDownTime(); //检查，是否超过了关机时间。

      lastCheckShutDownTimeMilliseconds=now;

      if (exceededShutDownTime) // 是超过了关机时间。
      {
        executeShutDown(); //执行关机过程。
        lastCheckShutDownTimeMilliseconds=0; // reset the time stamp for frequent check later.
      } //if (exceededShutDownTime) //是超过了关机时间。
    } // if ((now-lastCheckShutDownTimeMilliseconds)>=(1*60*1000)) // only check once every minute
	} //private void checkShutDownTime()
	
	/**
	* Execute fall back shut down.
	*/
	public void executeFallBackShutDown()
	{
    // Chen xin.
    String packageName=context.getPackageName(); // the package name of calling application.
    executeShutDown(packageName);
	} // public void executeFallBackShutDown()

	/**
	 * Execute shutdown. Or equivalent operations.
	 */
	public void executeShutDown(String packageName)
	{
    Intent launchIntent=new Intent(); //获取当前软件包的启动意图。
    launchIntent.setAction(Intent.ACTION_MAIN); //设置动作。

    // String packageName="com.stupidbeauty.shutdownat2100androidnative"; //21点关机。
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
	 * Execute shutdown. Or equivalent operations.
	 */
	public void executeShutDown()
	{
    // Intent launchIntent=new Intent(); //获取当前软件包的启动意图。
    // launchIntent.setAction(Intent.ACTION_MAIN); //设置动作。

    String packageName="com.stupidbeauty.shutdownat2100androidnative"; // the package name of shut down at 2100.
    
    executeShutDown(packageName);
    
    // String serviceName="com.stupidbeauty.shutdownat2100androidnative.StopUsingPhoneActivity"; //21点关机。
    // 
    // ComponentName cn = new ComponentName(packageName, serviceName);
    // launchIntent.setComponent(cn);
    // 
    // launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标志位。

    // try
    // {
    //   context.startActivity(launchIntent); // 启动 stop using phone 窗口。
    // } //
    // catch (ActivityNotFoundException e)
    // {
    //   e.printStackTrace();
    // } //catch (ActivityNotFoundException e)
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
	* Get the status of whether exceeded the shut down time.
	*/
	public boolean getExceededShutDownTime() 
	{
    return exceededShutDownTime;
	} // public boolean getExceededShutDownTime()

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
