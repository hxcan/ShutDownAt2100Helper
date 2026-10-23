package com.stupidbeauty.shutdownat2100.helper;

import android.content.Context;

import java.util.Calendar;

/**
 * 21点关机的启动策略判定引擎。
 *
 * <p><b>核心设计：两个时间窗口，方向相反。</b></p>
 *
 * <table border="1">
 * <tr><th></th><th>晚间</th><th>早晨</th></tr>
 * <tr><td>阈值时间点</td><td>关机时间，如 21:00</td><td>早晨阈值时间，如 07:01</td></tr>
 * <tr><td>黑名单窗口</td><td>阈值点<b>之前</b>1小时</td><td>阈值点<b>之后</b>1小时</td></tr>
 * <tr><td>窗口范围</td><td>20:00 ~ 21:00</td><td>07:01 ~ 08:01</td></tr>
 * <tr><td>防止什么</td><td>睡前刷手机，把睡觉时间拖过去</td><td>起床后刷手机，把整个早上毁掉</td></tr>
 * </table>
 *
 * <p>两个窗口都是「距离阈值时间点1小时以内」，但方向相反 ——
 * 晚间向前推，早晨向后推，从而达到<b>对称的躲避效果</b>。</p>
 *
 * @author root 蔡火胜。
 */
public class LaunchPolicy
{
    /**
     * 黑名单生效窗口的时长，单位：分钟。
     */
    private static final int BLACKLIST_WINDOW_MINUTES = 60;

    /**
     * 早晨阈值时间的默认小时数。主人尚未配置时使用。
     */
    private static final int DEFAULT_MORNING_HOUR = 7;

    /**
     * 早晨阈值时间的默认分钟数。主人尚未配置时使用。
     */
    private static final int DEFAULT_MORNING_MINUTE = 1;

    /**
     * 判定原因：不在黑名单中，允许启动。
     */
    public static final String REASON_ALLOWED_NOT_BLACKLISTED = "ALLOWED_NOT_BLACKLISTED";

    /**
     * 判定原因：黑名单应用，但在窗口之外，允许启动。
     */
    public static final String REASON_ALLOWED_OUTSIDE_WINDOW = "ALLOWED_OUTSIDE_WINDOW";

    /**
     * 判定原因：黑名单应用，且处于晚间窗口（关机前1小时），禁止启动。
     */
    public static final String REASON_BLOCKED_EVENING_WINDOW = "BLOCKED_EVENING_WINDOW";

    /**
     * 判定原因：黑名单应用，且处于早晨窗口（早晨阈值后1小时），禁止启动。
     */
    public static final String REASON_BLOCKED_MORNING_WINDOW = "BLOCKED_MORNING_WINDOW";

    /**
     * 判定结果。
     */
    public static class Result
    {
        /**
         * 是否允许启动。
         */
        public final boolean allowed;

        /**
         * 判定原因。
         */
        public final String reason;

        /**
         * 窗口结束时间（毫秒时间戳）。不在窗口内时为0。
         */
        public final long blockedUntil;

        /**
         * 构造判定结果。
         *
         * @param allowed 是否允许启动。
         * @param reason 判定原因。
         * @param blockedUntil 窗口结束时间。
         */
        public Result(boolean allowed, String reason, long blockedUntil)
        {
            this.allowed = allowed;
            this.reason = reason;
            this.blockedUntil = blockedUntil;
        }
    } //public static class Result

    /**
     * 不允许实例化。
     */
    private LaunchPolicy()
    {
        // 工具类，禁止实例化。
    }

    /**
     * 判断指定包名当前是否允许启动。
     *
     * @param context 上下文。
     * @param packageName 应用包名。
     * @return 允许启动返回 true，禁止启动返回 false。
     */
    public static boolean isLaunchAllowed(Context context, String packageName)
    {
        return check(context, packageName, null, null).allowed;
    }

    /**
     * 判断指定包名当前是否允许启动，并返回详细原因。
     *
     * @param context 上下文。
     * @param packageName 应用包名。
     * @param shutDownHour 关机小时数。传 null 则从偏好设置读取。
     * @param shutDownMinute 关机分钟数。传 null 则从偏好设置读取。
     * @return 判定结果。
     */
    public static Result check(Context context, String packageName, Integer shutDownHour, Integer shutDownMinute)
    {
        // 第一步：不在黑名单，永远允许。
        if (!AppBlacklist.isBlacklisted(context, packageName)) //不在黑名单。
        {
            return new Result(true, REASON_ALLOWED_NOT_BLACKLISTED, 0L); //允许。
        } //if (!AppBlacklist.isBlacklisted(context, packageName))

        int hour = resolveShutDownHour(context, shutDownHour); //获取关机小时。
        int minute = resolveShutDownMinute(context, shutDownMinute); //获取关机分钟。

        // 第二步：关机时间未配置，无法判定窗口，放行。
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) //关机时间无效。
        {
            return new Result(true, REASON_ALLOWED_OUTSIDE_WINDOW, 0L); //放行。
        } //if (hour < 0 || hour > 23 || minute < 0 || minute > 59)

        Calendar now = Calendar.getInstance(); //当前时间。

        // 第三步：检查晚间窗口 —— 关机时间点之前1小时。
        long eveningWindowEnd = buildTimeOfToday(now, hour, minute); //窗口结束点就是关机时间。
        long eveningWindowStart = eveningWindowEnd - BLACKLIST_WINDOW_MINUTES * 60L * 1000L; //往前推1小时。

        long nowMillis = now.getTimeInMillis(); //当前时间戳。

        if (nowMillis >= eveningWindowStart && nowMillis < eveningWindowEnd) //处于晚间窗口。
        {
            return new Result(false, REASON_BLOCKED_EVENING_WINDOW, eveningWindowEnd); //禁止启动。
        } //if (nowMillis >= eveningWindowStart && nowMillis < eveningWindowEnd)

        // 第四步：检查早晨窗口 —— 早晨阈值时间点之后1小时。
        int morningHour = resolveMorningHour(context); //早晨阈值小时。
        int morningMinute = resolveMorningMinute(context); //早晨阈值分钟。

        long morningWindowStart = buildTimeOfToday(now, morningHour, morningMinute); //窗口起点就是早晨阈值。
        long morningWindowEnd = morningWindowStart + BLACKLIST_WINDOW_MINUTES * 60L * 1000L; //往后推1小时。

        // 处理跨零点：如果早晨阈值本身在晚上（如 23:30），窗口会跨到次日。
        if (morningWindowEnd - morningWindowStart < 0 || crossesMidnight(morningHour, morningMinute)) //跨零点。
        {
            // 跨零点时，窗口为 [今天阈值, 次日阈值+1h]。
            Calendar nextDay = (Calendar) now.clone(); //克隆。
            nextDay.add(Calendar.DAY_OF_YEAR, 1); //次日。
            morningWindowEnd = buildTimeOfToday(nextDay, morningHour, morningMinute)
                    + BLACKLIST_WINDOW_MINUTES * 60L * 1000L; //次日窗口结束点。
        } //if (morningWindowEnd - morningWindowStart < 0 || crossesMidnight(morningHour, morningMinute))

        if (nowMillis >= morningWindowStart && nowMillis < morningWindowEnd) //处于早晨窗口。
        {
            return new Result(false, REASON_BLOCKED_MORNING_WINDOW, morningWindowEnd); //禁止启动。
        } //if (nowMillis >= morningWindowStart && nowMillis < morningWindowEnd)

        // 第五步：黑名单应用，但在两个窗口之外，允许启动。
        return new Result(true, REASON_ALLOWED_OUTSIDE_WINDOW, 0L); //允许。
    } //public static Result check(Context context, String packageName, Integer shutDownHour, Integer shutDownMinute)

    /**
     * 判断早晨阈值时间是否会导致窗口跨过午夜。
     *
     * @param morningHour 早晨阈值小时。
     * @param morningMinute 早晨阈值分钟。
     * @return 会跨午夜返回 true。
     */
    private static boolean crossesMidnight(int morningHour, int morningMinute)
    {
        return (morningHour * 60 + morningMinute) + BLACKLIST_WINDOW_MINUTES > 24 * 60; //会跨午夜。
    } //private static boolean crossesMidnight(int morningHour, int morningMinute)

    /**
     * 构造今天某时某分的时间戳。
     *
     * @param reference 参考的 Calendar 对象，提供年月。
     * @param hour 小时。
     * @param minute 分钟。
     * @return 时间戳（毫秒）。
     */
    private static long buildTimeOfToday(Calendar reference, int hour, int minute)
    {
        Calendar calendar = (Calendar) reference.clone(); //克隆，避免污染原对象。
        calendar.set(Calendar.HOUR_OF_DAY, hour); //设置小时。
        calendar.set(Calendar.MINUTE, minute); //设置分钟。
        calendar.set(Calendar.SECOND, 0); //秒归零。
        calendar.set(Calendar.MILLISECOND, 0); //毫秒归零。
        return calendar.getTimeInMillis(); //返回时间戳。
    } //private static long buildTimeOfToday(Calendar reference, int hour, int minute)

    /**
     * 解析关机小时数。
     *
     * @param context 上下文。
     * @param shutDownHour 传入值，为 null 时从偏好设置读取。
     * @return 关机小时数。
     */
    private static int resolveShutDownHour(Context context, Integer shutDownHour)
    {
        if (null != shutDownHour) //有传入值。
        {
            return shutDownHour; //直接返回。
        } //if (null != shutDownHour)

        return AppPreferences.getShutDownHour(context); //从偏好设置读取。
    } //private static int resolveShutDownHour(Context context, Integer shutDownHour)

    /**
     * 解析关机分钟数。
     *
     * @param context 上下文。
     * @param shutDownMinute 传入值，为 null 时从偏好设置读取。
     * @return 关机分钟数。
     */
    private static int resolveShutDownMinute(Context context, Integer shutDownMinute)
    {
        if (null != shutDownMinute) //有传入值。
        {
            return shutDownMinute; //直接返回。
        } //if (null != shutDownMinute)

        return AppPreferences.getShutDownMinute(context); //从偏好设置读取。
    } //private static int resolveShutDownMinute(Context context, Integer shutDownMinute)

    /**
     * 解析早晨阈值小时。
     *
     * @param context 上下文。
     * @return 早晨阈值小时，未配置时返回默认值7。
     */
    private static int resolveMorningHour(Context context)
    {
        int hour = AppPreferences.getMorningHour(context); //从偏好设置读取。

        if (hour < 0 || hour > 23) //未配置或无效。
        {
            return DEFAULT_MORNING_HOUR; //返回默认值。
        } //if (hour < 0 || hour > 23)

        return hour; //返回配置值。
    } //private static int resolveMorningHour(Context context)

    /**
     * 解析早晨阈值分钟。
     *
     * @param context 上下文。
     * @return 早晨阈值分钟，未配置时返回默认值1。
     */
    private static int resolveMorningMinute(Context context)
    {
        int minute = AppPreferences.getMorningMinute(context); //从偏好设置读取。

        if (minute < 0 || minute > 59) //未配置或无效。
        {
            return DEFAULT_MORNING_MINUTE; //返回默认值。
        } //if (minute < 0 || minute > 59)

        return minute; //返回配置值。
    } //private static int resolveMorningMinute(Context context)

    /**
     * 判断当前是否处于关机时间（晚间时段）。
     *
     * @param context 上下文。
     * @param shutDownHour 关机小时数，传 null 则从偏好设置读取。
     * @param shutDownMinute 关机分钟数，传 null 则从偏好设置读取。
     * @return 处于关机时间返回 true。
     */
    public static boolean isShutDownTimeNow(Context context, Integer shutDownHour, Integer shutDownMinute)
    {
        Result result = checkShutdownPeriod(context, shutDownHour, shutDownMinute); //查询所处时段。
        return result; //直接返回。
    } //public static boolean isShutDownTimeNow(Context context, Integer shutDownHour, Integer shutDownMinute)

    /**
     * 判断当前处于哪个关机时段。
     *
     * @param context 上下文。
     * @param shutDownHour 关机小时数，传 null 则从偏好设置读取。
     * @param shutDownMinute 关机分钟数，传 null 则从偏好设置读取。
     * @return true 表示处于关机时段（晚间或早晨）。
     */
    private static boolean checkShutdownPeriod(Context context, Integer shutDownHour, Integer shutDownMinute)
    {
        int hour = resolveShutDownHour(context, shutDownHour); //关机小时。
        int minute = resolveShutDownMinute(context, shutDownMinute); //关机分钟。

        if (hour < 0 || hour > 23) //关机时间无效。
        {
            return false; //不处于关机时段。
        } //if (hour < 0 || hour > 23)

        Calendar now = Calendar.getInstance(); //当前时间。
        long nowMillis = now.getTimeInMillis(); //当前时间戳。

        // 晚间时段：从关机时间到次日早晨阈值之前，都是关机时间。
        long shutDownTime = buildTimeOfToday(now, hour, minute); //今日关机时间。
        int morningHour = resolveMorningHour(context); //早晨阈值小时。
        int morningMinute = resolveMorningMinute(context); //早晨阈值分钟。
        long morningTime = buildTimeOfToday(now, morningHour, morningMinute); //今日早晨阈值。

        if (shutDownTime < morningTime) //关机时间早于早晨阈值（常见情况，如 21:00 < 07:01 需要跨天处理）。
        {
            // 关机时间（21:00）在早晨阈值（07:01）之后，区间是 [关机时间, 次日早晨阈值)。
            if (nowMillis >= shutDownTime) //今天已过关机时间。
            {
                return true; //处于关机时段。
            } //if (nowMillis >= shutDownTime)

            if (nowMillis < morningTime) //今天还没到早晨阈值。
            {
                return true; //处于关机时段（还没起床）。
            } //if (nowMillis < morningTime)
        } //if (shutDownTime < morningTime)
        else //关机时间晚于早晨阈值（如 08:00 关机、07:01 起床）。
        {
            if (nowMillis >= shutDownTime || nowMillis < morningTime) //已过关机时间，或还没到早晨阈值。
            {
                return true; //处于关机时段。
            } //if (nowMillis >= shutDownTime || nowMillis < morningTime)
        } //else //关机时间晚于早晨阈值

        return false; //不处于关机时段。
    } //private static boolean checkShutdownPeriod(Context context, Integer shutDownHour, Integer shutDownMinute)

} //public class LaunchPolicy