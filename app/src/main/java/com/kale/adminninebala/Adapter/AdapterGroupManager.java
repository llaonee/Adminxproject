package com.kale.adminninebala.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.kale.adminninebala.R;
import com.kale.adminninebala.Model.DataGroupManager;
/**
 * Created by Kale on 12/30/2018.
 */

public class AdapterGroupManager extends BaseAdapter{
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataGroupManager> item;

    public AdapterGroupManager(Activity activity, List<DataGroupManager> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_groupavaliable, null);

        TextView groupname = (TextView) convertView.findViewById(R.id.tvGroupname);

        DataGroupManager data;
        data = item.get(position);

        groupname.setText(data.getGroup_name());

        return convertView;
    }
}
