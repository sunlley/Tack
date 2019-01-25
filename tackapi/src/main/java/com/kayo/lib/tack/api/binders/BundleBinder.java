package com.kayo.lib.tack.api.binders;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * KayoSun
 * 2019-01-23
 * 23:12
 * ----------
 * Bundle数据绑定者
 */
public class BundleBinder implements Binder{

    private Bundle mBundle;

    public BundleBinder(Intent intent){
        if (intent != null){
            this.mBundle = intent.getExtras();
        }
    }

    public BundleBinder(Bundle bundle) {
        this.mBundle = bundle;
    }

    public Object get(String key){
        if (mBundle != null) {
            return mBundle.get(key);
        }
        return null;
    }

    public byte getByte(String key){
        return getByte(key,(byte)0);
    }
    public byte getByte(String key,byte defValue){
        if (mBundle != null) {
            return mBundle.getByte(key,defValue);
        }
        return defValue;
    }
    public byte[] getBytes(String key){
        if (mBundle != null) {
            return mBundle.getByteArray(key);
        }
        return null;
    }

    public char getChar(String key){
        return getChar(key, (char) 0);
    }
    public char getChar(String key,char defValue){
        if (mBundle != null) {
            return mBundle.getChar(key, defValue);
        }
        return defValue;
    }
    public char[] getChars(String key){
        if (mBundle != null) {
            return mBundle.getCharArray(key);
        }
        return null;
    }

    public short getShort(String key){
        return getShort(key, (short) 0);
    }
    public short getShort(String key,short defValue){
        if (mBundle != null) {
            return mBundle.getShort(key, defValue);
        }
        return defValue;
    }
    public short[] getShorts(String key){
        if (mBundle != null) {
            return mBundle.getShortArray(key);
        }
        return null;
    }

    public int getInt(String key){
        return getInt(key,0);
    }
    public int getInt(String key,int defValue){
        if (mBundle != null) {
            return  mBundle.getInt(key, defValue);
        }
        return  defValue;
    }
    public int[] getInts(String key){
        if (mBundle != null) {
            return mBundle.getIntArray(key);
        }
        return null;
    }

    public float getFloat(String key){
        return getFloat(key,0);
    }
    public float getFloat(String key,float defValue){
        if (mBundle != null) {
            return  mBundle.getFloat(key, defValue);
        }
        return  defValue;
    }
    public float[] getFloats(String key){
        if (mBundle != null) {
            return  mBundle.getFloatArray(key);
        }
        return  null;
    }

    public double getDouble(String key){
        return getDouble(key,0);
    }
    public double getDouble(String key,double defValue){
        if (mBundle != null) {
            return  mBundle.getDouble(key, defValue);
        }
        return  defValue;
    }
    public double[] getDoubles(String key){
        if (mBundle != null) {
            return  mBundle.getDoubleArray(key);
        }
        return null;
    }

    public long getLong(String key){
        return getLong(key,0);
    }
    public long getLong(String key,long defValue){
        if (mBundle != null) {
            return  mBundle.getLong(key, defValue);
        }
        return  defValue;
    }
    public long[] getLongs(String key){
        if (mBundle != null) {
            return  mBundle.getLongArray(key);
        }
        return null;
    }

    public String getString(String key){
        return getString(key,"");
    }
    public String getString(String key,String defValue){
        if (mBundle != null) {
            return  mBundle.getString(key, defValue);
        }
        return  defValue;
    }
    public String[] getStrings(String key){
        if (mBundle != null) {
            return  mBundle.getStringArray(key);
        }
        return  null;
    }

    public Parcelable getParcelable(String key){
        return getParcelable(key,null);
    }
    public Parcelable getParcelable(String key,Parcelable defValue){
        if (mBundle != null) {
            return  mBundle.getParcelable(key);
        }
        return  defValue;
    }
    public Parcelable[] getParcelables(String key){
        if (mBundle != null) {
            return mBundle.getParcelableArray(key);
        }
        return  null;
    }

    public Serializable getSerializable(String key){
        return getSerializable(key,null);
    }
    public Serializable getSerializable(String key,Serializable defValue){
        if (mBundle != null) {
            return  mBundle.getSerializable(key);
        }
        return  defValue;
    }

    public CharSequence getCharSequence(String key){
        return getCharSequence(key,"");
    }
    public CharSequence getCharSequence(String key,CharSequence defValue){
        if (mBundle != null) {
            return mBundle.getCharSequence(key,defValue);
        }
        return  defValue;
    }
    public CharSequence[] getCharSequences(String key,CharSequence[] defValue){
        if (mBundle != null) {
            return mBundle.getCharSequenceArray(key);
        }
        return  defValue;
    }

    public Size getSize(String key){
        return getSize(key,null);
    }
    public Size getSize(String key,Size defValue){
        if (mBundle != null) {
            return  mBundle.getSize(key);
        }
        return  defValue;
    }

    public SizeF getSizeF(String key){
        return getSizeF(key,null);
    }
    public SizeF getSizeF(String key,SizeF defValue){
        if (mBundle != null) {
            return  mBundle.getSizeF(key);
        }
        return  defValue;
    }

    public ArrayList<Integer> getIntArrayList(String key){
        if (mBundle != null) {
            return mBundle.getIntegerArrayList(key);
        }
        return null;
    }
    public ArrayList<Parcelable> getParcelableArrayList(String key){
        if (mBundle != null) {
            return mBundle.getParcelableArrayList(key);
        }
        return null;
    }
    public ArrayList<String> getStringArrayList(String key){
        if (mBundle != null) {
            return mBundle.getStringArrayList(key);
        }
        return null;
    }
    public ArrayList<CharSequence> getCharSequenceArrayList(String key){
        if (mBundle != null) {
            return mBundle.getCharSequenceArrayList(key);
        }
        return null;
    }

    public Bundle getBundle(){
        return mBundle;
    }





}
