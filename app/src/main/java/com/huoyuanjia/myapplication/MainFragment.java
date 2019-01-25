package com.huoyuanjia.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kayo.lib.tack.api.Tack;

/**
 * KayoSun
 * 2019-01-26
 * 02:01
 * ----------
 */
public class MainFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Tack.bind(this);
    }
}
