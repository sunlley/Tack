package com.kayo.lib.tack.api;

import android.content.Intent;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Kayo
 * 2018/12/22
 * 02:28
 */
public class Tack {

    public static void bind(Object target){
        Class<?> aClass = target.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        if (classLoader == null) {
            return;
        }
        try {
            Class<?> loadClass = classLoader.loadClass(target.getClass().getCanonicalName() + "$$Tack");
            Constructor<?> constructor = loadClass.getConstructor(aClass);
            constructor.newInstance(target);
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            // do nothing is ok
        }

    }

    /**
     * 分发 onActivityResult
     * @param target
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void dispatchBackResult(Object target,int requestCode, int resultCode, @Nullable Intent data){

    }
}
