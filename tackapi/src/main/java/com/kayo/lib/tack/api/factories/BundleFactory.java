package com.kayo.lib.tack.api.factories;

import android.os.Bundle;

import com.kayo.lib.tack.api.converter.Converter;
import com.kayo.lib.tack.api.utils.BundleUtil;
import com.kayo.lib.tack.api.utils.Utils;

import java.lang.reflect.Type;

/**
 * KayoSun
 * 2019-01-26
 * 22:37
 * ----------
 */
public class BundleFactory extends AbsFactory<Bundle> {

    private Bundle mBundle;

    public BundleFactory() {
        this(null);
    }

    public BundleFactory(Bundle bundle) {
        this.mBundle = bundle;
        if (this.mBundle == null) {
            this.mBundle = new Bundle();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public BundleFactory setConverter(Converter converter) {
        return super.setConverter(converter);
    }

    @Override
    public BundleFactory put(String key, Object obj) {
        if (Utils.textIsEmpty(key) || obj == null){
            return this;
        }
        if (Utils.isBundleSupportType(obj.getClass())) {
            BundleUtil.objectToBundle(mBundle,key,obj);
        }else {
            if (mConverter != null) {
                String json = mConverter.convertToJson(obj);
                BundleUtil.putToBundle(mBundle,key,json);
            }
        }
        return this;
    }

    @Override
    public Object get(String key) {
        return mBundle.get(key);
    }

    @Override
    public <C> C get(String key, Class<C> type) {
        if (mConverter != null) {
            String json = mBundle.getString(key);
            Object entity = mConverter.convertToEntity(json, type);
            if (entity != null){
                return Utils.wrapCast(entity);
            }
        }
        return null;
    }

    @Override
    public <C> C get(String key, Type type) {
        if (mConverter != null) {
            String json = mBundle.getString(key);
            Object entity = mConverter.convertToEntity(json, type);
            if (entity != null){
                return Utils.wrapCast(entity);
            }
        }
        return null;
    }

    @Override
    public Bundle create() {
        return mBundle;
    }
}
