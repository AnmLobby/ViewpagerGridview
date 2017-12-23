package com.example.viewpagergridview;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by CZY on 2017/6/23.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<Subject> subjectList;
    private LayoutInflater layoutInflater;

    //当前页索引
    private int currentIndex;

    //占满屏幕时每页展示的主题个数
    private int pageSize;

    public GridViewAdapter(Context context, List<Subject> subjectList, int currentIndex, int pageSize) {
        this.layoutInflater = LayoutInflater.from(context);
        this.subjectList = subjectList;
        this.currentIndex = currentIndex;
        this.pageSize = pageSize;
    }

    /**
     * 如果剩余数据能够完全占满当前页，则返回 pageSize
     * 如果不能，则返回剩余的数据个数
     */
    @Override
    public int getCount() {
        return subjectList.size() > (currentIndex + 1) * pageSize ? pageSize : (subjectList.size() - currentIndex * pageSize);
    }

    /**
     * 计算出正确的索引
     */
    @Override
    public Object getItem(int position) {
        return subjectList.get(position + currentIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + currentIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid_view, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
            viewHolder.iv_subject = (ImageView) convertView.findViewById(R.id.iv_subject);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int pos = position + currentIndex * pageSize;
        viewHolder.tv_subject.setText(subjectList.get(pos).getName());
//        viewHolder.iv_subject.setImageResource(subjectList.get(pos).getIcon());
        Glide.with(convertView.getContext())
                .load("http://bmob-cdn-15478.b0.upaiyun.com/2017/12/21/6d5426c440fdb9a980b00fad9847d38e.png")
                .into(viewHolder.iv_subject);
        return convertView;
    }

    private class ViewHolder {

        private TextView tv_subject;

        private ImageView iv_subject;

    }

}
