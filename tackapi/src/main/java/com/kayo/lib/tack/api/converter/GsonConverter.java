package com.kayo.lib.tack.api.converter;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-27
 * 00:19
 * ----------
 * Gson
 */
public class GsonConverter implements Converter{
    static {
        try {
            //noinspection unused
            String ignore = com.alibaba.fastjson.JSON.class.getCanonicalName();
        } catch (Throwable t) {
            throw new RuntimeException("You should add Gson into your dependencies list first", t);
        }
    }

    private Gson mGson = new Gson();
    public GsonConverter() {
    }

    @Override
    public Object convertToEntity(String entity, Type type) {
        return mGson.fromJson(entity,type);
    }

    @Override
    public String convertToJson(Object entity) {
        return mGson.toJson(entity);
    }
}
