package com.seu.recen.refreshdemo;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zhangrenchen on 2016/12/6.
 */

public class RefreshAdapter extends RecyclerView.Adapter<RefreshAdapter.MyViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;
    private String[] mTitles;
    private LayoutInflater mLayoutInflater;
    private List<String> datas = new ArrayList<>();
    private Context mContext;


    public RefreshAdapter(List<String> datas, Context mContext) {
        mTitles = mContext.getResources().getStringArray(R.array.titles);
        this.datas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText("测试实例" + position);
    }

    @Override
    public int getItemCount() {
        return headView == null? datas.size():datas.size()+1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            text = (TextView) itemView.findViewById(R.id.text_view);

        }
    }

    public void setHeadView(View view) {
        headView = view;
        notifyItemInserted(0);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int pos = holder.getLayoutPosition();
        return headView == null ? pos : pos - 1;
    }
}
