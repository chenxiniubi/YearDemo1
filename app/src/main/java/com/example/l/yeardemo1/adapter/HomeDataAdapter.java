package com.example.l.yeardemo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.l.yeardemo1.R;
import com.example.l.yeardemo1.bean.Data;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeDataAdapter extends XRecyclerView.Adapter<HomeDataAdapter.MyViewHolder>{

    private Context context;
    private List<Data> mList = new ArrayList<>();

    public HomeDataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.home_data_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Random random = new Random();
        ViewGroup.LayoutParams layoutParams = myViewHolder.textView.getLayoutParams();
        layoutParams.height = random.nextInt(200) + 50;
        myViewHolder.textView.setLayoutParams(layoutParams);
        //图片拆分
        String images = mList.get(i).getImages();
        //得到一个图片
        String[] split = images.split("\\|");
        if (split.length > 0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            Glide.with(context).load(replace).into(myViewHolder.imageView);
        }
        //标题赋值
        myViewHolder.textView.setText(mList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加集合数据
     */
    public void addAll(List<Data> data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    /**
     * 清空数据 刷新数据
     */
    public void clearList() {
        mList.clear();
    }

    //定义内部类
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.home_data_img);
             textView = itemView.findViewById(R.id.home_data_title);
        }
    }
}
