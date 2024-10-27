package com.stupidbeauty.shutdownat2100.receiver;

import com.stupidbeauty.shutdownat2100.helper.Constants;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.stupidbeauty.shutdownat2100androidnative.PreferenceManagerUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShutDownAt2100Receiver extends BroadcastReceiver 
{
    private static final String TAG = "ShutDownAt2100Receiver";
    public static final String ACTION_SHUT_DOWN_AT_2100_INSTALLED = "com.example.shutdowpat2100.INSTALLED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Constants.BroadcastAction.SHUT_DOWN_AT_2100_INSTALLED.equals(intent.getAction())) {
            boolean isInstalled = intent.getBooleanExtra("isInstalled", false);
            Log.d(TAG, "ShutDownAt2100 installed: " + isInstalled);
            // 处理接收到的信息
            handleShutDownAt2100Installed(context, isInstalled);
        }
    }

    private void handleShutDownAt2100Installed(Context context, boolean isInstalled) {
        // 在这里处理ShutDownAt2100应用是否安装过的信息
        if (isInstalled) {
            // 应用曾安装过
            Log.d(TAG, "ShutDownAt2100 was installed on this device.");
            
                PreferenceManagerUtil.setEverInstalledShutDownAt2100(context);

            
        } else {
            // 应用从未安装过
            Log.d(TAG, "ShutDownAt2100 was never installed on this device.");
        }
    }
}
