package com.kayo.lib.tack.api;

import android.os.Bundle;

import com.kayo.lib.tack.api.binders.BundleBinder;

/**
 * KayoSun
 * 2019-01-29
 * 15:57
 * ----------
 */
public interface ITack {

    void inject(BundleBinder binder);
    void inject(Bundle bundle);
    void inject(BundleBinder binder,boolean nullable);
    void inject(Bundle bundle,boolean nullable);

}
