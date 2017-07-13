package com.baway.lizongshu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.lizongshu.R;
import com.baway.lizongshu.bean.Fg2News;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class Fragment2Adapter extends BaseAdapter {
     private ArrayList<Fg2News.DataBean> mlist;
           private Context mcontext;
           private LayoutInflater mlf;

      public Fragment2Adapter(Context mcontext,ArrayList<Fg2News.DataBean> list) {

          this.mcontext = mcontext;
          this.mlist=list;
      }
             @Override
             public int getCount() {

                 return mlist==null?0:mlist.size();
             }

             @Override
             public Object getItem(int position) {

                 return mlist.get(position);
             }

             @Override
             public long getItemId(int position) {

                 return position;
             }

             @Override
             public View getView(int position, View convertView, ViewGroup parent) {
                 ViewHolder vh;
               mlf=  LayoutInflater.from(mcontext);
              if (convertView==null){
                 convertView= mlf.inflate(R.layout.item2,null);
                  vh=new ViewHolder();
                vh.iv=(ImageView)convertView.findViewById(R.id.item2_imageview);
                vh.content=(TextView) convertView.findViewById(R.id.item2_content);
                vh.title=(TextView)  convertView.findViewById(R.id.item2_title);
                  convertView.setTag(vh);
              }else{
              vh=(ViewHolder)convertView.getTag();
              }
                 final Fg2News.DataBean news = mlist.get(position);
                 vh.title.setText(news.getNews_title());
                 vh.content.setText(news.getNews_summary());
                 Glide.with(mcontext).load(news.getPic_url()).into(vh.iv);

                 return convertView;
             }

             class ViewHolder{
                 ImageView iv;
                 TextView title;
                 TextView content;


             }
}
