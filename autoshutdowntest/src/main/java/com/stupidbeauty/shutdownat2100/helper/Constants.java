package com.stupidbeauty.shutdownat2100.helper;

import android.os.Environment;

import java.io.File;

/**
 * 各个常量。
 * @author root Hxcan Cai
 */
public class Constants 
{
    /**
     * 目录路径。
     * @author root 蔡火胜。
     */
    public static class DirPath
    {
      public static final String FARMING_BOOK_APP_SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath()+ File.separator+ "etc" + File.separator+"ShutDownAt2100"; //女神相机的路径。
    } //public static class DirPath
}
