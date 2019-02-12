package com.huoyuanjia.myapplication;

import android.support.annotation.Keep;

/**
 * KayoSun
 * 2019-01-27
 * 01:03
 * ----------
 */
public class User {
    @Keep
    public String name;
    @Keep
    public int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:"+name+"   age:"+age;
    }
}
