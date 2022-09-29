package com.stupidbeauty.lanime.network.volley;


/**
 * RequestConfig这个request所返回的回复对象。
 * @author root 蔡火胜。
 *
 */
public class WebServiceResponse 
{
	private boolean success; //!<是否成功。
	private String msg; //!<服务器返回的对于当前结果的解释。
	private int x2appLatestVersionCode; //!<X2App的最新版本号。
	private String x2appNewVersionPackageurl; //!<X2app的最新版本下载地址。
	
	public int getX2appLatestVersionCode() {
		return x2appLatestVersionCode;
	}

	public void setX2appLatestVersionCode(int x2appLatestVersionCode) {
		this.x2appLatestVersionCode = x2appLatestVersionCode;
	}

	/**
	 * 请求是否成功。
	 * @return 是否成功。
	 */
	public boolean isSuccess() 
	{
		return success;
	} //public boolean isSuccess()

	/**
	 * 获取其它平板的IP列表。
	 * @return 其它平板的IP列表。
	 */
	public String getMsg() 
	{
		return msg;
	} //public ArrayList<String> getOtherIpList()

	public String getX2appNewVersionPackageurl() {
		return x2appNewVersionPackageurl;
	}

	public void setX2appNewVersionPackageurl(String x2appNewVersionPackageurl) {
		this.x2appNewVersionPackageurl = x2appNewVersionPackageurl;
	}

} //public class ReportLanIpResponse
