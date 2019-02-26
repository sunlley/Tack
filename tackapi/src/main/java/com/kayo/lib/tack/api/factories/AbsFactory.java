package com.kayo.lib.tack.api.factories;

import com.kayo.lib.tack.api.converter.Converter;

/**
 * KayoSun
 * 2019-01-26
 * 22:38
 * ----------
 */
public abstract class AbsFactory<T> implements Factory<T> {
    protected Converter mConverter;

    public AbsFactory() {
    }

    @Override
    public <F extends Factory> F setConverter(Converter converter) {
        this.mConverter = converter;
        return (F) this;
    }

}
