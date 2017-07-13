package com.baway.lizongshu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.lizongshu.R;
import com.baway.lizongshu.bean.Fg3News;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/6/24.
 */

public class Fragment3Adapter extends BaseAdapter {
     private ArrayList<Fg3News.DataBean.ComicsBean> mlist;
           private Context mcontext;
           private LayoutInflater mlf;

      public Fragment3Adapter(Context mcontext,ArrayList<Fg3News.DataBean.ComicsBean> list) {

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
                 convertView= mlf.inflate(R.layout.item3,null);
                  vh=new ViewHolder();
                vh.iv=(ImageView)convertView.findViewById(R.id.image_view3);
                vh.content=(TextView) convertView.findViewById(R.id.name3);
                vh.title=(TextView)  convertView.findViewById(R.id.title3);
                  convertView.setTag(vh);
              }else{
              vh=(ViewHolder)convertView.getTag();
              }
                 Fg3News.DataBean.ComicsBean news = mlist.get(position);
                 vh.title.setText(news.getTitle());
                 vh.content.setText(news.getLabel_text());
                 Glide.with(mcontext).load(news.getCover_image_url()).into(vh.iv);

                 return convertView;
             }

             class ViewHolder{
                 ImageView iv;
                 TextView title;
                 TextView content;


             }
}
