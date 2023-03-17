package util;

public class TimeUtil {
    public static void ptTime(String tag, long ms) {
        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        if (ms >= 1000) {
            seconds = ms / 1000;
            ms = ms % 1000;
        }
        if (seconds >= 60) {
            minutes = seconds / 60;
            seconds = seconds % 60;
        }
        if (minutes >= 60) {
            hours = minutes / 60;
            minutes = minutes % 60;
        }
        System.out.println(String.format("%s -- Total cost time:[%d小时%d分钟%d秒%d毫秒]", tag != null ? tag : "", hours, minutes, seconds, ms));

    }
}
