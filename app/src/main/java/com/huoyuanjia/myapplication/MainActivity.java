package com.huoyuanjia.myapplication;

import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kayo.lib.tack.annos.Paste;
import com.kayo.lib.tack.annos.PasteF;
import com.kayo.lib.tack.annos.PasteL;
import com.kayo.lib.tack.annos.PasteS;
import com.kayo.lib.tack.annos.Reject;
import com.kayo.lib.tack.api.Tack;
import com.kayo.lib.tack.api.converter.FastJsonConverter;

import java.io.Serializable;
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
        findViewById(R.id.v_text).setOnClickListener((v)->test());
        test();

    }

    @Reject
    private void test(){
        Log.i("MainActivity", "test: 测试数据");

        Bundle bundle = Tack.createBundleFactory()
                .setConverter(new FastJsonConverter())
                .put("test1", "abs")
                .put("test2", 2)
                .put("user",new User("小明",15))
                .create();
        System.out.println(bundle);

        User user = Tack.createBundleFactory(bundle)
                .get("user", User.class);
        System.out.println(user);


    }

}





