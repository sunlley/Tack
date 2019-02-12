package com.kayo.lib.tack.api.factories;

import com.kayo.lib.tack.api.converter.Converter;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-23
 * 23:12
 * ----------
 */
public interface Factory<T> {

    //设置变流器
    <F extends Factory>F  setConverter(Converter converter);

    Factory put(String key,Object obj);
    Object get(String key);
    <C> C get(String key,Class<C> type);
    <C> C get(String key, Type type);

    T create();
}
