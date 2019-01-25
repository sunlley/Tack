package com.kayo.lib.tack.api;

/**
 * Kayo
 * 2018/12/22
 * 02:29
 */
class Utils {

    static String getPackageName(Class<?> clazz){
        String classFullName = clazz.getCanonicalName();
        String[] split = classFullName.split("\\.");
        StringBuilder builder = new StringBuilder();
        int length = split.length-1;
        for (int i = 0; i < length; i++) {
            builder.append(split[i]);
            if (i != length-1){
                builder.append(".");
            }
        }
        return builder.toString();

    }

}
