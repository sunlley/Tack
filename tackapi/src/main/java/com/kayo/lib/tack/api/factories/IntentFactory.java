package com.kayo.lib.tack.api.factories;

import android.content.Intent;
import android.os.Bundle;

import com.kayo.lib.tack.api.converter.Converter;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-26
 * 23:08
 * ----------
 */
public class IntentFactory extends AbsFactory<Intent> {

    private Intent mIntent;
    private BundleFactory bundleFactory;

    public IntentFactory() {
        this(null);
    }

    public IntentFactory(Intent intent) {
        this.mIntent = intent;
        if (this.mIntent == null) {
            this.mIntent = new Intent();
        }
        bundleFactory = new BundleFactory(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public IntentFactory setConverter(Converter converter) {
        return super.setConverter(converter);
    }

    @Override
    public IntentFactory put(String key, Object obj) {
         bundleFactory.put(key, obj);
         return this;
    }

    @Override
    public Object get(String key) {
        return bundleFactory.get(key);
    }

    @Override
    public <C> C get(String key, Class<C> type) {
        return bundleFactory.get(key, type);
    }

    @Override
    public <C> C get(String key, Type type) {
        return bundleFactory.get(key, type);
    }

    @Override
    public Intent create() {
        Bundle bundle = bundleFactory.create();
        mIntent.putExtras(bundle);
        return mIntent;
    }
}
