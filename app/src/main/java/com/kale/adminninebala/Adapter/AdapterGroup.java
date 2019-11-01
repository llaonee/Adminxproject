package com.kale.adminninebala.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.kale.adminninebala.Model.DataGroup;
import com.kale.adminninebala.R;

import java.util.List;

/**
 * Created by Kale on 12/31/2018.
 */

public class AdapterGroup extends RecyclerView.Adapter<AdapterGroup.MyHolder> {
    List<DataGroup>data;
    String group;
    Context context;
    public AdapterGroup(Context context, List<DataGroup> data, String group) {
        this.data = data;
        this.context = context;
        this.group = group;
    }

    @Override
    public AdapterGroup.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_group, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterGroup.MyHolder holder, int position) {
        final DataGroup dataGroup = data.get(position);
        Glide.with(context)
                .load(dataGroup.getGroupImage())
                .into(holder.ivGroupimage);
        holder.ivGroupimage.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.tvGroupname.setText(dataGroup.getGroupName());
        holder.tvGroupdesc.setText(dataGroup.getGroupDesc());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView tvGroupname, tvGroupdesc;
        ImageView ivGroupimage;
        public MyHolder (View itemView){
            super(itemView);
            tvGroupname = itemView.findViewById(R.id.tvGroupname);
            tvGroupdesc = itemView.findViewById(R.id.tvGroupdesc);
            ivGroupimage = itemView.findViewById(R.id.ivGroupimage);

        }
    }
}
