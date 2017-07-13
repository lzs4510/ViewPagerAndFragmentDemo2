package com.baway.lizongshu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baway.lizongshu.fragment.Fragment1;
import com.baway.lizongshu.fragment.Fragment2;
import com.baway.lizongshu.fragment.Fragment3;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new Fragment1();
                break;
            case 1:
                fragment=new Fragment2();
                break;
            case 2:
                fragment=new Fragment3();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
