package com.baway.lizongshu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.baway.lizongshu.adapter.RemenAdapter;
import com.baway.lizongshu.bean.RemenNews;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class RemenActivity extends AppCompatActivity {
    private ListView mlv;
    private ArrayList<RemenNews.DataBean.TopicsBean> mlist=new ArrayList<>();
    private RemenAdapter ra;
        private Handler hand=new Handler(){
                @Override
                public void handleMessage(Message msg) {
              //当msg传值为1时，解析字符串并添加到集合，刷新数据
                 if (msg.what==1){
                     Gson gson=new Gson();
                  RemenNews news= gson.fromJson(msg.obj.toString(), RemenNews.class);
                     mlist.addAll(news.getData().getTopics());
                     ra.notifyDataSetChanged();
                 }
                }
            };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remen);
        mlv=(ListView)findViewById(R.id.remenlist_view);

        initdata();
        ra=new RemenAdapter(this,mlist);
        mlv.setAdapter(ra);

    }

    private void initdata() {
        new Thread(){
            @Override
            public void run() {
             String str=   Httpurl.getUrlConnect("http://api.kkmh.com/v1/topic_new/discovery_module_list/219?offset=0&limit=20&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3OTE2MDE4MjIxLCJwcm9wZXJ0aWVzIjp7IiRvc192ZXJzaW9uIjoiNC4yLjIiLCJHZW5kZXJUeXBlIjoi5peg5rOV6I635Y-WIiwiVHJpZ2dlckl0ZW0iOjMsIlZpc2l0UGFnZU5hbWUiOiLng63pl6jlv4XnnIsiLCIkbGliX3ZlcnNpb24iOiIxLjYuMzQiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiR3aWZpIjp0cnVlLCIkbWFudWZhY3R1cmVyIjoic2Ftc3VuZyIsIlRyaWdnZXJJdGVtTmFtZSI6IueDremXqOW_heeciyIsIiRzY3JlZW5faGVpZ2h0Ijo1NzYsIkxpc3RUeXBlIjoi5pen54mIIiwiUHJvcGVydHlFdmVudCI6IlJlYWRMaXN0IiwiVHJpZ2dlck9yZGVyTnVtYmVyIjowLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6NDYsIiRzY3JlZW5fd2lkdGgiOjEwMjQsIiRvcyI6IkFuZHJvaWQiLCJUcmlnZ2VySXRlbVR5cGUiOjAsIlRyaWdnZXJQYWdlIjoiRmluZFBhZ2UiLCIkY2FycmllciI6IkNNQ0MiLCIkbW9kZWwiOiJHVC1QNTIxMCIsIiRhcHBfdmVyc2lvbiI6IjMuOC4xIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5Iiwib3JpZ2luYWxfaWQiOiJBOjkwNTEwNDI3NjM3NTUxMDkiLCJldmVudCI6IlJlYWRMaXN0In0%3D&style=2");
                Message msg=new Message();
                msg.what=1;
                msg.obj=str;
                hand.sendMessage(msg);
            }
        }.start();


    }
}
