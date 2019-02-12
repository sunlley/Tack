package com.kayo.lib.tack.api.factories;

import android.os.Bundle;

import com.kayo.lib.tack.api.converter.Converter;
import com.kayo.lib.tack.api.converter.GsonConverter;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-26
 * 22:38
 * ----------
 */
public abstract class AbsFactory<T> implements Factory<T> {
    protected Converter mConverter;

    public AbsFactory() {
        mConverter = new GsonConverter();
    }

    @Override
    public <F extends Factory> F setConverter(Converter converter) {
        this.mConverter = converter;
        return (F) this;
    }

}
