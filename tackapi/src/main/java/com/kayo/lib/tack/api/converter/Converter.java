package com.kayo.lib.tack.api.converter;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-26
 * 22:30
 * ----------
 * 数据变流器
 */
public interface Converter {

    Object convertToEntity(String entity, Type type);

    String convertToJson(Object entity);

}
