package com.baway.lizongshu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baway.lizongshu.Activity3;
import com.baway.lizongshu.Httpurl;
import com.baway.lizongshu.R;
import com.baway.lizongshu.adapter.Fragment3Adapter;
import com.baway.lizongshu.bean.Fg3News;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class Fragment3 extends Fragment {
    private ListView lv;
    private Fragment3Adapter fa;
    private String urlpath="http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODI4ODU4NjAwLCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJGcm9tSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VyUGFnZSI6IkhvbWVQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkSG9tZVBhZ2UifQ%3D%3D";
    private ArrayList<Fg3News.DataBean.ComicsBean> mlist=new ArrayList<>();
        private Handler hand=new Handler(){
                @Override
                public void handleMessage(Message msg) {
              //当msg传值为1时，解析字符串并添加到集合，刷新数据
                 if (msg.what==1){
                     Gson gson=new Gson();
                     Fg3News news = gson.fromJson(msg.obj.toString(), Fg3News.class);
                     mlist.addAll(news.getData().getComics());
                   fa.notifyDataSetChanged();
                 }
                }
            };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        lv=(ListView)view.findViewById(R.id.list_view3);
        initdata();
        fa=new Fragment3Adapter(getActivity(),mlist);
        lv.setAdapter(fa);
        //listview的监听事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fg3News.DataBean.ComicsBean bean = mlist.get(position);
                Intent intent=new Intent(getContext(), Activity3.class);
                intent.putExtra("bb",bean.getUrl());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initdata() {
        new Thread(){
            @Override
            public void run() {
                String str = Httpurl.getUrlConnect(urlpath);
                Message msg=new Message();
                msg.what=1;
                msg.obj=str;
                hand.sendMessage(msg);

            }
        }.start();

    }
}
