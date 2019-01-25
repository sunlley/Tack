package com.huoyuanjia.myapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteB;
import com.kayo.lib.tack.annos.PasteD;
import com.kayo.lib.tack.annos.PasteF;
import com.kayo.lib.tack.annos.PasteL;
import com.kayo.lib.tack.annos.PasteS;
import com.kayo.lib.tack.api.Tack;

import java.io.Serializable;
import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.v_text)
    TextView vText;

    @PasteS(defaultVar = "abs")
    String agr2;
    @PasteF
    float agr3;
    @PasteL(defaultVar = 6)
    float agrL;
    @PasteD
    Double agr4;
    @Paste
    List<String> agr5;
    @Paste
    ArrayMap<String,Integer> agr6;
    @Paste
    Object agr7;
    @PasteB
    byte argB;

    @Paste
    Parcelable[] parces;
    @Paste
    Serializable serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tack.bind(this);
//        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.v_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("TestA","测试数据");
                startActivity(intent);
            }
        });
//        Bundle extras = getIntent().getExtras();
//        extras.get()

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
