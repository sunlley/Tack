package com.kayo.lib.tack.api;

import android.os.Bundle;

import com.kayo.lib.tack.api.binders.BundleBinder;

/**
 * KayoSun
 * 2019-01-24
 * 23:15
 * ----------
 * 注入接口
 */
public interface Inject {

    void inject(BundleBinder binder);
    void inject(Bundle bundle);
    void inject(BundleBinder binder,boolean nullable);
    void inject(Bundle bundle,boolean nullable);

}
