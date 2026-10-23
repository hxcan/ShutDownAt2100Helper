package com.stupidbeauty.shutdownat2100.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 21点关机辅助库的偏好设置管理。
 *
 * <p>统一管理关机时间、早晨阈值时间等配置项。</p>
 *
 * <p>⚠️ 历史上早晨阈值时间被硬编码在 {@code ShutDownAt2100Manager} 中（7:01），
 * 导致早晨时段无法根据主人的实际需求调整。本类将其改为可配置。</p>
 *
 * @author root 蔡火胜。
 */
public class AppPreferences
{
    /**
     * SharedPreferences 存储名字。
     */
    private static final String PREFERENCE_NAME = "com.stupidbeauty.shutdownat2100.helper";

    /**
     * 关机小时数的键。
     */
    private static final String KEY_SHUT_DOWN_HOUR = "shut_down_hour";

    /**
     * 关机分钟数的键。
     */
    private static final String KEY_SHUT_DOWN_MINUTE = "shut_down_minute";

    /**
     * 早晨阈值小时的键。
     */
    private static final String KEY_MORNING_HOUR = "morning_hour";

    /**
     * 早晨阈值分钟的键。
     */
    private static final String KEY_MORNING_MINUTE = "morning_minute";

    /**
     * 不允许实例化。
     */
    private AppPreferences()
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
     * 获取关机小时数。
     *
     * @param context 上下文。
     * @return 关机小时数，未配置时返回 -1。
     */
    public static int getShutDownHour(Context context)
    {
        return getPreferences(context).getInt(KEY_SHUT_DOWN_HOUR, -1);
    } //public static int getShutDownHour(Context context)

    /**
     * 设置关机小时数。
     *
     * @param context 上下文。
     * @param hour 关机小时数。
     */
    public static void setShutDownHour(Context context, int hour)
    {
        getPreferences(context).edit().putInt(KEY_SHUT_DOWN_HOUR, hour).apply();
    } //public static void setShutDownHour(Context context, int hour)

    /**
     * 获取关机分钟数。
     *
     * @param context 上下文。
     * @return 关机分钟数，未配置时返回 -1。
     */
    public static int getShutDownMinute(Context context)
    {
        return getPreferences(context).getInt(KEY_SHUT_DOWN_MINUTE, -1);
    } //public static int getShutDownMinute(Context context)

    /**
     * 设置关机分钟数。
     *
     * @param context 上下文。
     * @param minute 关机分钟数。
     */
    public static void setShutDownMinute(Context context, int minute)
    {
        getPreferences(context).edit().putInt(KEY_SHUT_DOWN_MINUTE, minute).apply();
    } //public static void setShutDownMinute(Context context, int minute)

    /**
     * 获取早晨阈值小时。
     *
     * @param context 上下文。
     * @return 早晨阈值小时，未配置时返回 -1。
     */
    public static int getMorningHour(Context context)
    {
        return getPreferences(context).getInt(KEY_MORNING_HOUR, -1);
    } //public static int getMorningHour(Context context)

    /**
     * 设置早晨阈值小时。
     *
     * @param context 上下文。
     * @param hour 早晨阈值小时。
     */
    public static void setMorningHour(Context context, int hour)
    {
        getPreferences(context).edit().putInt(KEY_MORNING_HOUR, hour).apply();
    } //public static void setMorningHour(Context context, int hour)

    /**
     * 获取早晨阈值分钟。
     *
     * @param context 上下文。
     * @return 早晨阈值分钟，未配置时返回 -1。
     */
    public static int getMorningMinute(Context context)
    {
        return getPreferences(context).getInt(KEY_MORNING_MINUTE, -1);
    } //public static int getMorningMinute(Context context)

    /**
     * 设置早晨阈值分钟。
     *
     * @param context 上下文。
     * @param minute 早晨阈值分钟。
     */
    public static void setMorningMinute(Context context, int minute)
    {
        getPreferences(context).edit().putInt(KEY_MORNING_MINUTE, minute).apply();
    } //public static void setMorningMinute(Context context, int minute)

    /**
     * 设置早晨阈值时间。
     *
     * @param context 上下文。
     * @param hour 小时。
     * @param minute 分钟。
     */
    public static void setMorningTime(Context context, int hour, int minute)
    {
        SharedPreferences.Editor editor = getPreferences(context).edit(); //获取编辑器。
        editor.putInt(KEY_MORNING_HOUR, hour); //设置小时。
        editor.putInt(KEY_MORNING_MINUTE, minute); //设置分钟。
        editor.apply(); //保存。
    } //public static void setMorningTime(Context context, int hour, int minute)

    /**
     * 设置关机时间。
     *
     * @param context 上下文。
     * @param hour 小时。
     * @param minute 分钟。
     */
    public static void setShutDownTime(Context context, int hour, int minute)
    {
        SharedPreferences.Editor editor = getPreferences(context).edit(); //获取编辑器。
        editor.putInt(KEY_SHUT_DOWN_HOUR, hour); //设置小时。
        editor.putInt(KEY_SHUT_DOWN_MINUTE, minute); //设置分钟。
        editor.apply(); //保存。
    } //public static void setShutDownTime(Context context, int hour, int minute)

} //public class AppPreferences