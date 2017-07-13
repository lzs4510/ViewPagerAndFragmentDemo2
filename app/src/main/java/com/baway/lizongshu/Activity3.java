package com.baway.lizongshu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by 李宗书 on 2017/6/24.
 */

public class Activity3 extends AppCompatActivity {
    private WebView wv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fg3xiangxi);

        wv=(WebView)findViewById(R.id.wv) ;
        String ss = getIntent().getStringExtra("bb");
        wv.loadUrl(ss);
    }
}
