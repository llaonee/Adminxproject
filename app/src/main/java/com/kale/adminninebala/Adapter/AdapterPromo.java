package com.kale.adminninebala.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kale.adminninebala.Model.DataPromo;
import com.kale.adminninebala.R;

import java.util.List;

/**
 * Created by Kale on 1/9/2019.
 */

public class AdapterPromo extends RecyclerView.Adapter<AdapterPromo.MyHolder> {
    Context context;
    List<DataPromo>data;
    int groupid;

    public AdapterPromo(Context context, List<DataPromo> data, int groupid) {
        this.data = data;
        this.context = context;
        this.groupid = groupid;
    }

    @Override
    public AdapterPromo.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_promo, parent, false);
        return new AdapterPromo.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final DataPromo dataPromo = data.get(position);
        Glide.with(context)
                .load(dataPromo.getGroupImage())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivGroup);
        Glide.with(context)
                .load(dataPromo.getPromoImage())
                .into(holder.ivPromo);
        holder.ivPromo.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.tvGroupName.setText(dataPromo.getGroupName());
        holder.tvPromoName.setText(dataPromo.getPromoName());
        holder.tvPromoDesc.setText(dataPromo.getPromoDesc());
        holder.tvPromoDate.setText(dataPromo.getPromoDate());
        holder.tvPromoEndDate.setText(dataPromo.getPromoEnddate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyHolder extends RecyclerView.ViewHolder{

        ImageView ivGroup, ivPromo;
        TextView tvGroupName, tvPromoName, tvPromoDesc, tvPromoDate, tvPromoEndDate;
        public MyHolder (View ItemView){
            super(ItemView);
            ivGroup = (ItemView).findViewById(R.id.ivGroup);
            ivPromo = (ItemView).findViewById(R.id.ivPromo);
            tvGroupName = (ItemView).findViewById(R.id.tvGroupName);
            tvPromoName = (ItemView).findViewById(R.id.tvPromoName);
            tvPromoDesc = (ItemView).findViewById(R.id.tvPromoDesc);
            tvPromoDate = (ItemView).findViewById(R.id.tvPromoDate);
            tvPromoEndDate = (ItemView).findViewById(R.id.tvEndPromoDate);
        }
    }
}
