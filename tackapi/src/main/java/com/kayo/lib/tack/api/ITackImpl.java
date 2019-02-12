package com.kayo.lib.tack.api;

import android.os.Bundle;

import com.kayo.lib.tack.api.binders.BundleBinder;

/**
 * KayoSun
 * 2019-01-31
 * 23:20
 * ----------
 * 简易实现类
 */
public abstract class ITackImpl implements ITack {

    @Override
    public final void inject(Bundle bundle, boolean nullable) {
        if(bundle==null){return;};
        BundleBinder binder = new BundleBinder(bundle);
        this.inject(binder,nullable);
    }

    @Override
    public final void inject(BundleBinder binder) {
        if(binder==null){return;};
        this.inject(binder,true);
    }

    @Override
    public final void inject(Bundle bundle) {
        if(bundle==null){return;};
        this.inject(bundle,true);
    }
}
