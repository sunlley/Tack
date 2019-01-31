package com.kayo.lib.tack.api.converter;

import com.kayo.lib.tack.api.utils.Utils;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-27
 * 00:19
 * ----------
 * FastJson
 */
public class FastJsonConverter implements Converter{
    static {
        try {
            //noinspection unused
            String ignore = com.alibaba.fastjson.JSON.class.getCanonicalName();
        } catch (Throwable t) {
            throw new RuntimeException("You should add fastjson into your dependencies list first", t);
        }
    }

    @Override
    public Object convertToEntity(String entity, Type type) {
        if (Utils.textIsEmpty(entity)){
            return null;
        }
        return com.alibaba.fastjson.JSON.parseObject(entity, type);
    }

    @Override
    public String convertToJson(Object entity) {
        return com.alibaba.fastjson.JSON.toJSONString(entity);
    }
}
