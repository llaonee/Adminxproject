package com.kale.adminninebala.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kale.adminninebala.Model.DataGroup;
import com.kale.adminninebala.Model.DataManager;
import com.kale.adminninebala.R;

import java.util.List;

/**
 * Created by Kale on 1/2/2019.
 */

public class AdapterManager extends RecyclerView.Adapter<AdapterManager.MyHolder> {
    List<DataManager>data;
    String manager;
    Context context;
    public AdapterManager(Context context, List<DataManager> data, String manager) {
        this.data = data;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public AdapterManager.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_manager, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterManager.MyHolder holder, int position) {
        final DataManager dataManager = data.get(position);
        Glide.with(context)
                .load(dataManager.getManagerPicture())
                .into(holder.ivManager);
        holder.tvManager.setText(dataManager.getManagerName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder{
        ImageView ivManager;
        TextView tvManager;

        public MyHolder (View ItemView){
            super(ItemView);
            ivManager = ItemView.findViewById(R.id.ivManagerimage);
            tvManager = ItemView.findViewById(R.id.tvManagername);
        }
    }
}
