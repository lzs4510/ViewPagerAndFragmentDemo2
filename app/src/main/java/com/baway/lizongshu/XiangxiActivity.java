package com.baway.lizongshu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**Fragment1展示的界面监听跳转之后的URL信息
 * Created by 李宗书 on 2017/6/23.
 */

public class XiangxiActivity extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangxi);
        String s = getIntent().getStringExtra("aa");
        wv=(WebView)findViewById(R.id.wv);
        wv.loadUrl(s);

    }
}
