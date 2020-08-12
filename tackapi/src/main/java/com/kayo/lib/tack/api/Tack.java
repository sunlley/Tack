package com.kayo.lib.tack.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kayo.lib.tack.api.factories.BundleFactory;
import com.kayo.lib.tack.api.factories.IntentFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Kayo
 * 2018/12/22
 * 02:28
 */
public class Tack {

    /**
     * 注入
     * @param target
     */
    public static void bind(Object target){
        Log.i("Tack", "bind: Tack "+target.getClass().getSimpleName());
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

    public static void test(){
        Log.i("Tack", "test: Tack");
    }

    /**
     * 生产
     */
    public static BundleFactory createBundleFactory(){
        return createBundleFactory(null);
    }
    public static BundleFactory createBundleFactory(Bundle bundle){
        return new BundleFactory(bundle);
    }
    public static IntentFactory createIntentFactory(){
        return createIntentFactory(null);
    }
    public static IntentFactory createIntentFactory(Intent bundle){
        return new IntentFactory(bundle);
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
