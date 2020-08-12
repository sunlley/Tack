package com.huoyuanjia.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kayo.lib.tack.annos.Inject;
import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteS;
import com.kayo.lib.tack.api.Tack;

public class Main2Activity extends AppCompatActivity {
    @Paste("TestA")
    String arg1;

    @Inject
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Tack.bind(this);
        System.out.println("获取数据:"+arg1);
    }
}
