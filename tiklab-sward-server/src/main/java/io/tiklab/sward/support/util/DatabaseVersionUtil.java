package io.tiklab.sward.support.util;

public class DatabaseVersionUtil {

    private static final String KEY = "SWARD_IS_PGSQL";

    public static boolean isPgsql() {
        return Boolean.parseBoolean(System.getProperty(KEY, "true")); // 默认 PostgreSQL
    }

    public static boolean isMysql() {
        return !isPgsql();
    }
}
