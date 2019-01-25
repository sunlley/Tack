package com.huoyuanjia.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteS;
import com.kayo.lib.tack.api.Tack;

public class Main2Activity extends AppCompatActivity {
    @Paste("TestA")
    String arg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Tack.bind(this);
        System.out.println("获取数据:"+arg1);
    }
}
