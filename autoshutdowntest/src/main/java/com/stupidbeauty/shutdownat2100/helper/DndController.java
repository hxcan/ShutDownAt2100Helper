package com.stupidbeauty.shutdownat2100.helper;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

/**
 * 勿扰模式（Do Not Disturb）控制器。
 *
 * <p>在 21 点关机规则生效（处于关机时段）期间，自动开启勿扰模式；
 * 在早晨时段结束后，自动恢复为正常通知状态。</p>
 *
 * <p>设计要点：</p>
 * <ul>
 *   <li>使用 {@code INTERRUPTION_FILTER_PRIORITY}（仅优先打扰）而非 SILENCE，
 *       这样重要联系人的来电仍然可以正常响铃，不会把人彻底失联。</li>
 *   <li>尊重主人的手动操作：如果主人主动关掉了勿扰模式，同一晚不再重复开启。
 *       通过记录「已开启过」的标记来实现。</li>
 *   <li>需要 {@code ACCESS_NOTIFICATION_POLICY} 权限；未授予时不做任何破坏性操作，
 *       仅记录日志。</li>
 * </ul>
 *
 * @author root 蔡火胜。
 */
public class DndController
{
    /**
     * 调试信息标记。
     */
    private static final String TAG = "DndController";

    /**
     * SharedPreferences 存储名字。
     */
    private static final String PREFERENCE_NAME = "com.stupidbeauty.shutdownat2100.helper";

    /**
     * 「已开启过勿扰」的标记键。
     */
    private static final String KEY_DND_ENABLED_ONCE = "dnd_enabled_once";

    /**
     * 禁止实例化。
     */
    private DndController()
    {
        // 工具类，禁止实例化。
    }

    /**
     * 获取 SharedPreferences。
     *
     * @param context 上下文。
     * @return SharedPreferences 实例。
     */
    private static SharedPreferences getPreferences(Context context)
    {
        return context.getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 是否已经获得勿扰模式访问权限。
     *
     * @param context 上下文。
     * @return 已授权返回 true。
     */
    public static boolean hasNotificationPolicyAccess(Context context)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            // Android 6.0 之前没有勿扰模式 API。
            return false;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null)
        {
            return false;
        }
        return notificationManager.isNotificationPolicyAccessGranted();
    }

    /**
     * 获取当前的勿扰模式过滤级别。
     *
     * @param context 上下文。
     * @return 当前的过滤级别，无法获取时返回 {@link NotificationManager#INTERRUPTION_FILTER_ALL}。
     */
    public static int getCurrentInterruptionFilter(Context context)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return NotificationManager.INTERRUPTION_FILTER_ALL;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null)
        {
            return NotificationManager.INTERRUPTION_FILTER_ALL;
        }
        return notificationManager.getCurrentInterruptionFilter();
    }

    /**
     * 开启勿扰模式（仅优先打扰）。
     *
     * <p>如果主人已经手动关闭过勿扰模式，则不再重复开启。</p>
     *
     * @param context 上下文。
     * @return 成功开启返回 true；被主人手动关闭过、未授权等情况返回 false。
     */
    public static boolean enableDoNotDisturb(Context context)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            Log.w(TAG, "enableDoNotDisturb: Android < M, not supported");
            return false;
        }
        if (!AppPreferences.getEnableDndOnShutdown(context))
        {
            Log.d(TAG, "enableDoNotDisturb: disabled by configuration");
            return false;
        }
        if (isEnabledOnce(context))
        {
            // 主人已经手动关掉过勿扰模式，尊重主人的操作，这一晚不再重复开启。
            Log.d(TAG, "enableDoNotDisturb: already enabled once, respect user's manual operation");
            return false;
        }
        if (!hasNotificationPolicyAccess(context))
        {
            Log.w(TAG, "enableDoNotDisturb: ACCESS_NOTIFICATION_POLICY not granted");
            return false;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null)
        {
            Log.w(TAG, "enableDoNotDisturb: notification manager is null");
            return false;
        }
        try
        {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_PRIORITY);
            markEnabledOnce(context);
            Log.d(TAG, "enableDoNotDisturb: do not disturb enabled, current filter: " + getCurrentInterruptionFilter(context));
            return true;
        }
        catch (SecurityException e)
        {
            Log.w(TAG, "enableDoNotDisturb: security exception", e);
            return false;
        }
    }

    /**
     * 关闭勿扰模式，恢复正常通知。
     *
     * @param context 上下文。
     * @return 成功关闭返回 true。
     */
    public static boolean disableDoNotDisturb(Context context)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return false;
        }
        if (!hasNotificationPolicyAccess(context))
        {
            Log.w(TAG, "disableDoNotDisturb: ACCESS_NOTIFICATION_POLICY not granted");
            return false;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null)
        {
            Log.w(TAG, "disableDoNotDisturb: notification manager is null");
            return false;
        }
        try
        {
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
            clearEnabledOnce(context);
            Log.d(TAG, "disableDoNotDisturb: do not disturb disabled, current filter: " + getCurrentInterruptionFilter(context));
            return true;
        }
        catch (SecurityException e)
        {
            Log.w(TAG, "disableDoNotDisturb: security exception", e);
            return false;
        }
    }

    /**
     * 根据是否处于关机时段，同步勿扰模式的状态。
     *
     * @param context 上下文。
     * @param inShutDownPeriod 是否处于关机时段。
     */
    public static void syncDoNotDisturb(Context context, boolean inShutDownPeriod)
    {
        if (inShutDownPeriod)
        {
            enableDoNotDisturb(context);
        }
        else
        {
            if (AppPreferences.getRestoreDndAfterShutDownPeriod(context))
            {
                disableDoNotDisturb(context);
            }
        }
    }

    /**
     * 是否已经开启过勿扰模式（用于尊重主人的手动操作）。
     *
     * @param context 上下文。
     * @return 已经开启过返回 true。
     */
    private static boolean isEnabledOnce(Context context)
    {
        return getPreferences(context).getBoolean(KEY_DND_ENABLED_ONCE, false);
    }

    /**
     * 记录「已经开启过勿扰模式」。
     *
     * @param context 上下文。
     */
    private static void markEnabledOnce(Context context)
    {
        getPreferences(context).edit().putBoolean(KEY_DND_ENABLED_ONCE, true).apply();
    }

    /**
     * 清除「已经开启过勿扰模式」的标记。
     *
     * @param context 上下文。
     */
    private static void clearEnabledOnce(Context context)
    {
        getPreferences(context).edit().putBoolean(KEY_DND_ENABLED_ONCE, false).apply();
    }

    /**
     * 构造跳转系统「勿扰权限」设置的 Intent。
     *
     * @param context 上下文。
     * @return Intent 对象。
     */
    public static Intent buildNotificationPolicyAccessIntent(Context context)
    {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            intent.setData(Uri.parse("package:" + context.getPackageName()));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

} //public class DndController
