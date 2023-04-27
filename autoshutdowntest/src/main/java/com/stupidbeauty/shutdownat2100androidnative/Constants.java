package com.stupidbeauty.shutdownat2100androidnative;

import android.os.Environment;
import java.io.File;

/**
 * 一些常量的定义。
 * @author root 蔡火胜。
 *
 */
@SuppressWarnings("WeakerAccess")
public class Constants
{
    /**
     * 常用常量。
     * @author root 蔡火胜。
     *
     */
    public final class Common
    {
        public static final String SHUTDOWN_HOUR="shutdownHour"; //!<关机的小时数。
		public static final String SHUTDOWN_MINUTE="shutdownMinute"; //!<关机的分钟数。

    } //public final class Common

    public final class Timing
    {
        public static final long MinimalPublishIntervalMilliseconds=1*1000; //!<最小的发布服务时间间隔。
    }

    /**
     * 目录路径。
     * @author root 蔡火胜。
     *
     */
    public static class DirPath
    {
        public static final String FARMING_BOOK_APP_SD_CARD_PATH = Environment.getExternalStorageDirectory().getPath()+ File.separator+ "etc" + File.separator+"ShutDownAt2100"; //女神相机的路径。
    } //public static class DirPath

} //public class Constants
