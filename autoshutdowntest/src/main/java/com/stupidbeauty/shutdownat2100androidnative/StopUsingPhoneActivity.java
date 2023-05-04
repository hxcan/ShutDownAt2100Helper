package com.stupidbeauty.shutdownat2100androidnative;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.stupidbeauty.shutdownat2100.helper.R2;
import com.stupidbeauty.shutdownat2100.helper.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StopUsingPhoneActivity extends Activity
{
  private static final int PERMISSIONS_REQUEST = 1;

  private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
  private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; //!<录音权限。

  @Override
  /**
    * 活动被创建。
    */
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    askShowSystemWallpaper(); //要求显示系统的墙纸在本活动后面。

    requestWindowFeature(Window.FEATURE_NO_TITLE); //不显示标题栏。

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) 
    {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    setContentView(R.layout.stop_using_phone_activity); // Set the content view.

    ButterKnife.bind(this); //视图注入。

    checkPermission(); //检查权限。
  } //protected void onCreate(Bundle savedInstanceState)

    /**
     * 启动拨号界面。
     */
    @OnClick(R2.id.dialerbutton)
    public void startDialer()
    {
//        Uri u = Uri.parse("tel:" + e.getText().toString());
        Uri u = null;

        // Create the intent and set the data for the
        // intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, u);

        try
        {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i);
        }
        catch (SecurityException s)
        {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, s.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }

    } //public void startDialer()

    @Override
    /**
     * 按了后退键。
     */
    public void onBackPressed()
    {
    } //public void onBackPressed()


    /**
     * 检查权限。
     */
    private void checkPermission()
    {
        if (hasPermission()) {
        } else {
            requestPermission();
        }

    } //private void checkPermission()

    private void requestPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
            {
                Toast.makeText(this, "Camera AND storage permission are required for this demo", Toast.LENGTH_LONG).show();
            } //if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
            requestPermissions(new String[] {PERMISSION_STORAGE, PERMISSION_RECORD_AUDIO}, PERMISSIONS_REQUEST);
        }
    } //private void requestPermission()





    private boolean hasPermission()
    {
        boolean result=false; //结果。

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        {
            result= checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED; //存储权限。

            if (result) //存储权限已有。
            {
                result=(checkSelfPermission(PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED); //录音权限。
            } //if (result) //存储权限已有。
        } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        else //旧版本。
        {
            result=true; //有权限。
        } //else //旧版本。

        return result;
    } //private boolean hasPermission()



    /**
     * 要求显示系统的墙纸在本活动后面。
     */
    private void askShowSystemWallpaper()
    {
        WindowManager.LayoutParams p=getWindow().getAttributes();
        p.flags |= WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;

//        getWindow().setAttributes(p);

    } //private void askShowSystemWallpaper()


}


