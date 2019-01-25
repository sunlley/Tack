package com.huoyuanjia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteF;
import com.kayo.lib.tack.annos.PasteL;
import com.kayo.lib.tack.annos.PasteS;
import com.kayo.lib.tack.api.Tack;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @PasteF
    float agrF;

    @PasteL(defaultVar = 6)
    float agrL;

    @Paste
    List<String> agr5;
    
    @PasteS(defaultVar = "abc")
    String agr1;

    @Paste("name")
    String agr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tack.bind(this);

    }
}





