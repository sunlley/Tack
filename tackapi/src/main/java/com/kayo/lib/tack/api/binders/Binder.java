package com.kayo.lib.tack.api.binders;

import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * KayoSun
 * 2019-01-13
 * 23:10
 * ----------
 * 数据绑定者
 */
public interface Binder {

     byte getByte(String key);
     byte getByte(String key,byte defValue);
     byte[] getBytes(String key);

     char getChar(String key);
     char getChar(String key,char defValue);
     char[] getChars(String key);

     short getShort(String key);
     short getShort(String key,short defValue);
     short[] getShorts(String key);

     int getInt(String key);
     int getInt(String key,int defValue);
     int[] getInts(String key);

     float getFloat(String key);
     float getFloat(String key,float defValue);
     float[] getFloats(String key);

     double getDouble(String key);
     double getDouble(String key,double defValue);
     double[] getDoubles(String key);

     long getLong(String key);
     long getLong(String key,long defValue);
     long[] getLongs(String key);

     String getString(String key);
     String getString(String key,String defValue);
     String[] getStrings(String key);

     Parcelable getParcelable(String key);
     Parcelable getParcelable(String key,Parcelable defValue);
     Parcelable[] getParcelables(String key);

     Serializable getSerializable(String key);
     Serializable getSerializable(String key,Serializable defValue);

     CharSequence getCharSequence(String key);
     CharSequence getCharSequence(String key,CharSequence defValue);
     CharSequence[] getCharSequences(String key,CharSequence[] defValue);

     Size getSize(String key);
     Size getSize(String key,Size defValue);

     SizeF getSizeF(String key);
     SizeF getSizeF(String key,SizeF defValue);

     ArrayList<Integer> getIntArrayList(String key);
     ArrayList<Parcelable> getParcelableArrayList(String key);
     ArrayList<String> getStringArrayList(String key);
     ArrayList<CharSequence> getCharSequenceArrayList(String key);
}
