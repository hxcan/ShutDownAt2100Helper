package com.stupidbeauty.shutdownat2100.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 21点关机辅助库的应用黑名单管理。
 *
 * <p>黑名单里的应用，在关机前1小时内不允许启动（时间窗口判定见 {@link ShutDownAt2100Manager}）。
 * 黑名单本身只负责"某包名是否在黑名单中"这一静态判定，供各个配套应用查询。</p>
 *
 * <p>初始黑名单包含快手、抖音等沉迷类应用。主人可以通过 {@link #addToBlacklist(Context, String)}
 * 与 {@link #removeFromBlacklist(Context, String)} 增删，无需修改代码重新编译。</p>
 *
 * @author root 蔡火胜。
 */
public class AppBlacklist
{
    /**
     * SharedPreferences 存储名字。
     */
    private static final String PREFERENCE_NAME = "com.stupidbeauty.shutdownat2100.helper.blacklist";

    /**
     * 存储用户自定义黑名单的键。
     */
    private static final String KEY_ADDED_BLACKLIST = "added_blacklist";

    /**
     * 存储用户从黑名单中移除项的键。
     */
    private static final String KEY_REMOVED_BLACKLIST = "removed_blacklist";

    /**
     * 内置的默认黑名单包名。主人可在此追加，但不要删除快手与抖音。
     */
    private static final List<String> DEFAULT_BLACKLIST = Collections.unmodifiableList(Arrays.asList(
            "com.smile.gifmaker", // 快手
            "com.ss.android.ugc.aweme" // 抖音
    ));

    /**
     * 不允许实例化。
     */
    private AppBlacklist()
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
     * 获取当前完整的黑名单包名列表。
     *
     * <p>结果 = （内置黑名单 - 用户移除项）+ 用户添加项。</p>
     *
     * @param context 上下文。
     * @return 黑名单包名列表，不包含 null 项。
     */
    public static List<String> getBlacklist(Context context)
    {
        Set<String> blacklist = new LinkedHashSet<String>(DEFAULT_BLACKLIST); // 使用 LinkedHashSet 保持顺序。

        SharedPreferences preferences = getPreferences(context); //获取偏好设置。

        String removed = preferences.getString(KEY_REMOVED_BLACKLIST, ""); //被移除的项。
        if (null != removed && removed.length() > 0) //有移除项。
        {
            blacklist.removeAll(Arrays.asList(removed.split(","))); //移除。
        } //if (null != removed && removed.length() > 0)

        String added = preferences.getString(KEY_ADDED_BLACKLIST, ""); //添加的项。
        if (null != added && added.length() > 0) //有添加项。
        {
            blacklist.addAll(Arrays.asList(added.split(","))); //添加。
        } //if (null != added && added.length() > 0)

        blacklist.remove(""); //去掉空串。

        return new ArrayList<String>(blacklist);
    }

    /**
     * 判断指定的包名是否在黑名单中。
     *
     * @param context 上下文。
     * @param packageName 应用包名。
     * @return 在黑名单中则返回 true，否则返回 false。包名为空时返回 false。
     */
    public static boolean isBlacklisted(Context context, String packageName)
    {
        if (null == packageName || packageName.length() == 0) //包名为空。
        {
            return false; //不在黑名单里。
        } //if (null == packageName || packageName.length() == 0)

        List<String> blacklist = getBlacklist(context); //获取黑名单。

        if (blacklist.contains(packageName)) //完全匹配。
        {
            return true; //在黑名单里。
        } //if (blacklist.contains(packageName))

        for (String blacklistedPackage : blacklist) //逐个比较。
        {
            if (packageName.startsWith(blacklistedPackage)) //前缀匹配，兼容快手等应用的多进程包名。
            {
                return true; //在黑名单里。
            } //if (packageName.startsWith(blacklistedPackage))
        } //for (String blacklistedPackage : blacklist)

        return false; //不在黑名单里。
    } //public static boolean isBlacklisted(Context context, String packageName)

    /**
     * 添加一个包名到黑名单。
     *
     * @param context 上下文。
     * @param packageName 应用包名。
     */
    public static void addToBlacklist(Context context, String packageName)
    {
        if (null == packageName || packageName.length() == 0) //包名为空。
        {
            return; //什么都不做。
        } //if (null == packageName || packageName.length() == 0)

        SharedPreferences preferences = getPreferences(context); //获取偏好设置。

        Set<String> added = readSet(preferences.getString(KEY_ADDED_BLACKLIST, "")); //读取已添加项。

        added.remove(packageName); //先移除，保证不重复。
        added.add(packageName); //再添加。

        preferences.edit().putString(KEY_ADDED_BLACKLIST, joinSet(added)).apply(); //保存。
    } //public static void addToBlacklist(Context context, String packageName)

    /**
     * 从黑名单中移除一个包名。
     *
     * @param context 上下文。
     * @param packageName 应用包名。
     */
    public static void removeFromBlacklist(Context context, String packageName)
    {
        if (null == packageName || packageName.length() == 0) //包名为空。
        {
            return; //什么都不做。
        } //if (null == packageName || packageName.length() == 0)

        SharedPreferences preferences = getPreferences(context); //获取偏好设置。

        Set<String> added = readSet(preferences.getString(KEY_ADDED_BLACKLIST, "")); //读取已添加项。
        added.remove(packageName); //从添加项里移除。
        preferences.edit().putString(KEY_ADDED_BLACKLIST, joinSet(added)).apply(); //保存。

        Set<String> removed = readSet(preferences.getString(KEY_REMOVED_BLACKLIST, "")); //读取已移除项。
        removed.add(packageName); //加入移除项。
        preferences.edit().putString(KEY_REMOVED_BLACKLIST, joinSet(removed)).apply(); //保存。
    } //public static void removeFromBlacklist(Context context, String packageName)

    /**
     * 读取逗号分隔的字符串为集合。
     *
     * @param value 逗号分隔的字符串。
     * @return 集合，已去掉空项。
     */
    private static Set<String> readSet(String value)
    {
        Set<String> result = new LinkedHashSet<String>(); //结果。

        if (null != value && value.length() > 0) //非空。
        {
            for (String item : value.split(",")) //分割。
            {
                if (item.length() > 0) //非空项。
                {
                    result.add(item); //添加。
                } //if (item.length() > 0)
            } //for (String item : value.split(","))
        } //if (null != value && value.length() > 0)

        return result;
    } //private static Set<String> readSet(String value)

    /**
     * 把集合写成逗号分隔的字符串。
     *
     * @param set 集合。
     * @return 逗号分隔的字符串。
     */
    private static String joinSet(Set<String> set)
    {
        StringBuilder builder = new StringBuilder(); //构造器。

        for (String item : set) //逐项。
        {
            if (builder.length() > 0) //不是第一项。
            {
                builder.append(","); //加分隔符。
            } //if (builder.length() > 0)

            builder.append(item); //加内容。
        } //for (String item : set)

        return builder.toString();
    } //private static String joinSet(Set<String> set)

} //public class AppBlacklist