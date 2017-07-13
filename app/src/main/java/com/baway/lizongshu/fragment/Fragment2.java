package com.baway.lizongshu.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baway.lizongshu.Httpurl;
import com.baway.lizongshu.R;
import com.baway.lizongshu.Sqlhelp;
import com.baway.lizongshu.adapter.Fragment2Adapter;
import com.baway.lizongshu.bean.Fg2News;
import com.google.gson.Gson;

import java.util.ArrayList;

import me.maxwin.view.XListView;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class Fragment2 extends Fragment implements XListView.IXListViewListener{
    private XListView  mxv;
    private int page=1;
    private String urlpath="http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    private Fragment2Adapter fd;
    private Sqlhelp sh;
    private SQLiteDatabase db;
    private   long ii;
    private ArrayList<Fg2News.DataBean> mlist=new ArrayList<>();
        private Handler hand=new Handler(){
                @Override
                public void handleMessage(Message msg) {
              //当msg传值为1时，解析字符串并添加到集合，刷新数据
                 if (msg.what==1){
                     Gson gson=new Gson();
                     Fg2News news = gson.fromJson(msg.obj.toString(), Fg2News.class);
                     mlist.addAll(news.getData());
                    fd.notifyDataSetChanged();

                     stop();
                     //把数据存到数据库
                     cun();
                 }
                }
        };




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        mxv=(XListView)view.findViewById(R.id.x_listview);
        mxv.setPullLoadEnable(true);
        mxv.setXListViewListener(this);

        sh=new Sqlhelp(getContext());
         db = sh.getWritableDatabase();

        initdata();
        fd=new Fragment2Adapter(getActivity(),mlist);
        mxv.setAdapter(fd);
        return view;

    }
   //加载网络数据
    private void initdata() {
     new Thread(){
         @Override
         public void run() {
             String str = Httpurl.getUrlConnect(urlpath + page);
             Message msg=new Message();
             msg.what=1;
             msg.obj=str;
             hand.sendMessage(msg);
         }
     }.start();

    }
    //把数据存到数据库的方法
    private void cun() {
      for (Fg2News.DataBean qq:mlist) {
          ContentValues values=new ContentValues();
          values.put("title",qq.getNews_title());
          values.put("content",qq.getNews_summary());
          ii = db.insert("user", null, values);

      }
        if (ii>0){
            Toast.makeText(getContext(),"插入成功",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"插入失败",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRefresh() {
        page=1;
        mlist.clear();
        initdata();

    }

    @Override
    public void onLoadMore() {
        page++;
        initdata();

    }
    private void stop() {
        mxv.stopRefresh();
        mxv.stopLoadMore();
        mxv.setRefreshTime("刚刚");
    }

}
