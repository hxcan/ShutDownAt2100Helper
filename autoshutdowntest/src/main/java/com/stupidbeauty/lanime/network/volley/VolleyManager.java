package com.stupidbeauty.lanime.network.volley;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.stupidbeauty.lanime.QueueMateStoreApplication;


/**
 * 配置信息类。
 * @author root
 *
 */
public class VolleyManager 
{
	// define
	public boolean debug = true; //!<输出调试信息。
	public boolean verbose = false; //!<输出详细跟踪信息。
	public boolean info = false; //!<输出信息型调试消息。
	public boolean warn = false; //!<输出警告消息。
	public boolean error = true; //!<输出错误消息。

	// update url
	public String GMATE_UPDATE_URL; //!<OTA的检查更新路径。
	public String FAQ_UPDATE_URL_BASE; //!<FAQ的更新路径。
	//静态网页相关的参数：
	public  String STATIC_WEBPAGE_URL_PREFIX ; //!<静态网页URL前缀。
 
	
	// login url
	public String BASE_LOGIN_PATH; //!<BOSS的登录URL。
	//web login url
	public  String WEB_LOGIN_PATH; //!<BOSS的网页界面登录URL。当网厅中的会话到期时，会将网页跳转到这个地址，APP发现被跳转到这个地址，则退出登录。
	// account url
	public String ACCOUNT_TAB_URL; //!<BOSS的账户界面URL。
	
	public String EHALL_BASE_URL; //!<网厅的基准URL。
	
	//OTA 与客户端检查更新的时间间隔
	public static int    TIME_DELAY_BETWEEN_CHECK_UPDATA = 60* (1000 * 60); //!<OTA与客户端检查更新的时间间隔。
	
	public 	int packagedConfigFileID; //!<已打包的配置文件的ID。
	public String CONFIG_URL; //!<配置文件检查更新的URL。
	public int CONFIG_UPDATE_INTERVAL; //!<配置文件检查更新的时间间隔，以分钟为单位。
	private String mSectionName; //!<The section name.Level 3 postal address.
	private String mPhoneNumber; //!<The phone number.
	private String mDetailedAddress; //!<The detailed address.
	private String	mReceivePersonName; //!<The receive person name.
	private RequestQueue	mQueue; //!<The volley request queue.

	
	
	// private
	private static VolleyManager mConfig; //!<实际的配置对象实例。

	/**
	 * 获取共享的单实例。
	 * @return 共享的单实例。
	 */
	public static VolleyManager shareInstance() 
	{
		if (mConfig == null) //配置信息实例还不存在。 
		{
			mConfig=new VolleyManager(); //create a new object。
			
		}
		return mConfig;

	} //public static Config shareInstance()






	/**
	 * 是否输出话痨级别的调试信息。
	 * @return 是否输出话痨级别的调试信息。
	 */
	public static boolean verbose() 
	{
		return VolleyManager.shareInstance().verbose;
	} //public static boolean verbose()

	
	/**
	 * 是否输出调试级别的调试信息。
	 * @return 是否输出调试级别的调试信息。
	 */
	public static boolean debug() 
	{
		return VolleyManager.shareInstance().debug;
	} //public static boolean debug() 

	/**
	 * 是否输出信息级别的调试信息。
	 * @return 是否输出信息级别的调试信息。
	 */
	public static boolean info() 
	{
		return VolleyManager.shareInstance().info;
	} //public static boolean info() 

	/**
	 * 是否输出错误级别的调试信息。
	 * @return 是否输出错误级别的调试信息。
	 */
	public static boolean error() 
	{
		return VolleyManager.shareInstance().error;
	} //public static boolean error()

	/**
	 * 是否输出警告级别的调试信息。
	 * @return 是否输出警告级别的调试信息。
	 */
	public static boolean warn() 
	{
		return VolleyManager.shareInstance().warn;
	} //public static boolean warn() 
	
	/**
	 * 获取版本号字符串。
	 * @return 版本号字符串。
	 */
	public static String getVersion()
	{
		Context context = QueueMateStoreApplication.getSharedInstance(); //获取应用程序上下文。
		
		try //尝试获取软件包信息。 
		{
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0); //获取软件包信息。			 
			return pi.versionName ;		  //返回版本号名字。
		} //try //尝试获取软件包信息。 
		catch (NameNotFoundException e) //捕获异常，名字未找到。 
		{
			e.printStackTrace(); //输出错误内容。
			return "0.0.0"; //版本号为0.0.0.
		} //catch (NameNotFoundException e) //捕获异常，名字未找到。
	} //public static String getVersion()

	/**
	 * Get the section name.
	 * @return The section name.
	 */
	public String getSectionName() 
	{


		return mSectionName;
	}

	/**
	 * Get the phone number.
	 * @return The phone number.
	 */
	public String getPhoneNumber() 
	{


		return mPhoneNumber;
	}

	/**
	 * Get the detailed address.
	 * @return The detailed address.
	 */
	public String getDetailedAddress() 
	{


		return mDetailedAddress;
	}

	/**
	 * Get the receive person name.
	 * @return The receive person name.
	 */
	public String getReceivePersonName() 
	{
		return mReceivePersonName;
	}

	public void setAddressSection(String sctnNm) 
	{
		mSectionName=sctnNm; //Remember.
		
	} //public void setAddressSection(String sctnNm)

	public void setAddressDetail(String addressDtl) 
	{
		mDetailedAddress=addressDtl; //Remember.
		
	} //public void setAddressDetail(String addressDtl)

	public void setReceiverPerson(String rcver) 
	{

		mReceivePersonName=rcver; 
		
	}

	public void setReceiverPhone(String rcvPhn) 
	{


		mPhoneNumber=		rcvPhn;
	} //public void setReceiverPhone(String rcvPhn)

	/**
	 * Get the request queue.
	 * @return The request queue.
	 */
	public RequestQueue getRequestQueue() 
	{
		if (mQueue==null) //Not exist.
		{
			mQueue=Volley.newRequestQueue(QueueMateStoreApplication.getSharedInstance(),GsonHttpManager.shareInstance().getHttpStack()); //创建Volley请求队列。
		} //if (mQueue==null) //Not exist.

		return mQueue;
	} //public RequestQueue getRequestQueue()
} //public class Config 

