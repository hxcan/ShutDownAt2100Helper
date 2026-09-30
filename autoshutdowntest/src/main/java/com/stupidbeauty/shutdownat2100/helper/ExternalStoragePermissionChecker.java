package com.stupidbeauty.shutdownat2100.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

/**
 * External Storage Permission Checker
 *
 * Android 11 (API 30) 引入 Scoped Storage 后，传统 READ_EXTERNAL_STORAGE 无法访问任意路径。
 * 访问外置存储（任意路径）需要 MANAGE_EXTERNAL_STORAGE 权限，用户必须手动在系统设置中授予。
 *
 * 使用方法：
 * <pre>
 *   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
 *       if (!ExternalStoragePermissionChecker.hasManageExternalStoragePermission(context)) {
 *           ExternalStoragePermissionChecker.requestManageExternalStoragePermission(activity);
 *           return; // 等用户授权后再继续
 *       }
 *   }
 * </pre>
 *
 * 任务参考：Redmine #907286722056 / #907287692725
 */
public class ExternalStoragePermissionChecker {

    private static final String TAG = "ExternalStoragePermissionChecker"; //!< Debug tag.

    /**
     * Check if the calling application has MANAGE_EXTERNAL_STORAGE permission (Android 11+).
     *
     * @param context Application context.
     * @return true if permission is granted, or Android version is below R (Q and below use READ_EXTERNAL_STORAGE instead).
     */
    public static boolean hasManageExternalStoragePermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            boolean granted = Environment.isExternalStorageManager();
            Log.d(TAG, "hasManageExternalStoragePermission, sdk=" + Build.VERSION.SDK_INT + ", granted=" + granted); // Debug.
            return granted;
        }
        // Android 10 及以下：使用传统 READ_EXTERNAL_STORAGE（库消费方自行检查）
        Log.d(TAG, "hasManageExternalStoragePermission, sdk=" + Build.VERSION.SDK_INT + " (pre-R), returning true (caller checks READ_EXTERNAL_STORAGE)"); // Debug.
        return true;
    }

    /**
     * Request MANAGE_EXTERNAL_STORAGE permission by launching system settings page.
     * User must manually toggle "Allow access to manage all files".
     *
     * @param activity Calling activity (required to start settings Intent).
     */
    public static void requestManageExternalStoragePermission(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            Log.d(TAG, "requestManageExternalStoragePermission, no-op (sdk < R)"); // Debug.
            return;
        }
        try {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
            Log.d(TAG, "requestManageExternalStoragePermission, launched settings for " + activity.getPackageName()); // Debug.
        } catch (Exception e) {
            // Fallback: some ROMs / OEMs 不支持精确包名跳转，使用通用 ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            Log.w(TAG, "requestManageExternalStoragePermission, fallback to generic settings: " + e.getMessage()); // Debug.
            try {
                Intent fallback = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                fallback.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(fallback);
            } catch (Exception e2) {
                Log.e(TAG, "requestManageExternalStoragePermission, failed: " + e2.getMessage(), e2); // Debug.
            }
        }
    }
}