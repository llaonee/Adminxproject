package com.kale.adminninebala.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kale.adminninebala.ActivityManager.DetailGroup;
import com.kale.adminninebala.Model.DataGroup;
import com.kale.adminninebala.R;

import java.util.List;

/**
 * Created by Kale on 1/7/2019.
 */

public class AdapterManagerGroup extends RecyclerView.Adapter<AdapterManagerGroup.MyHolder> {

    List<DataGroup>data;
    String group;
    int idmanager;
    Context context;
    public AdapterManagerGroup(Context context, List<DataGroup> data, String group, int idmanager) {
        this.data = data;
        this.context = context;
        this.group = group;
        this.idmanager = idmanager;
    }


    @Override
    public AdapterManagerGroup.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_group_manager, parent, false);
        return new AdapterManagerGroup.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterManagerGroup.MyHolder holder, int position) {
        final DataGroup dataGroup = data.get(position);
        Glide.with(context)
                .load(dataGroup.getGroupImage())
                .into(holder.ivGroup);
        holder.tvGroupname.setText(dataGroup.getGroupName());
        holder.glGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailGroup.class);
                i.putExtra("groupimage", dataGroup.getGroupImage());
                i.putExtra("groupid", dataGroup.getGroupId());
                i.putExtra("groupname", dataGroup.getGroupName());
                i.putExtra("idmanager", idmanager);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView tvGroupname;
        ImageView ivGroup;
        GridLayout glGroup;
        public MyHolder (View itemView){
            super(itemView);
            tvGroupname = itemView.findViewById(R.id.tvGroupname);
            ivGroup = itemView.findViewById(R.id.ivGroup);
            glGroup = itemView.findViewById(R.id.glGroup);

        }
    }
}