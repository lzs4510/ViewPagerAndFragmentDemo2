package com.baway.lizongshu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baway.lizongshu.fragment.Fragment1;
import com.baway.lizongshu.fragment.Fragment2;
import com.baway.lizongshu.fragment.Fragment3;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mvp;
    private TextView tv1,tv2,tv3;
    private PagerAdapter pd;
    private ArrayList<TextView> list=new ArrayList<>();
    private Fragment f1,f2,f3;
    private ArrayList<Fragment> mlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initview();
    }
    private void initview() {
        mvp=(ViewPager)findViewById(R.id.vp);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        list.add(tv1);list.add(tv2);list.add(tv3);

        f1=new Fragment1();
        f2=new Fragment2();
        f3=new Fragment3();
        mlist.add(f1);mlist.add(f2);mlist.add(f3);

        FragmentManager fm = getSupportFragmentManager();
        pd=new com.baway.lizongshu.adapter.PagerAdapter(fm);
        mvp.setAdapter(pd);

        mvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<list.size();i++){
                    if (i==position){
                        list.get(i).setTextColor(Color.YELLOW);

                    }else{
                        list.get(i).setTextColor(Color.BLACK);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv1:
           mvp.setCurrentItem(0);
                break;
            case R.id.tv2:
                mvp.setCurrentItem(1);
                break;
            case R.id.tv3:
         mvp.setCurrentItem(2);
                break;
            case R.id.remen:
                Intent intent=new Intent(MainActivity.this,RemenActivity.class);
                startActivity(intent);

                break;
            case R.id.xinzuo:


                break;
            case R.id.qihuan:


                break;

        }
    }
}
