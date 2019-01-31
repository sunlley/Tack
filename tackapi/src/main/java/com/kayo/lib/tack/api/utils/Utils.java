package com.kayo.lib.tack.api.utils;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Kayo
 * 2018/12/22
 * 02:29
 */
public class Utils {

    public static String getPackageName(Class<?> clazz) {
        String classFullName = clazz.getCanonicalName();
        String[] split = classFullName.split("\\.");
        StringBuilder builder = new StringBuilder();
        int length = split.length - 1;
        for (int i = 0; i < length; i++) {
            builder.append(split[i]);
            if (i != length - 1) {
                builder.append(".");
            }
        }
        return builder.toString();

    }

    /* Used for generated code.*/
    public static <T> T wrapCast (Object data) {
        //noinspection unchecked
        return (T) data;
    }

    public static boolean isBaseType(Class<?> clz) {
        return clz == Integer.class
                || clz == int.class
                || clz == boolean.class
                || clz == Boolean.class
                || clz == byte.class
                || clz == Byte.class
                || clz == char.class
                || clz == Character.class
                || clz == float.class
                || clz == Float.class
                || clz == double.class
                || clz == Double.class
                || clz == long.class
                || clz == Long.class;

    }

    public static boolean isBundleSupportType(Class<?> clz) {
        return isBaseType(clz)
                || clz == String.class
                || clz == Size.class
                || clz == SizeF.class
                || clz == Bundle.class
                || clz == IBinder.class
                || clz == SparseArray.class
                || clz == ArrayList.class;

    }

    public static boolean textIsEmpty(CharSequence var){
        return var == null || var == "" || var.length() == 0;
    }
    public static boolean textNotEmpty(CharSequence var){
        return !textIsEmpty(var);
    }

}
