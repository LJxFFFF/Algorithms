package com.mycompany.weather.Method;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycompany.weather.DailyForecast;
import com.mycompany.weather.R;

import java.util.List;

/**
 * Created by Ljx on 2016/2/19.
 */
public class WeatherAdapter extends BaseAdapter {
    private List<DailyForecast> mlist;
    private LayoutInflater mInflater;

    public WeatherAdapter(Context context, List<DailyForecast> data) {
        mlist = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
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
        ViewHolder viewHolder = null;
        if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.vlist, null);
                viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.info = (TextView) convertView.findViewById(R.id.info);
                viewHolder.max = (TextView) convertView.findViewById(R.id.max);
                viewHolder.min = (TextView) convertView.findViewById(R.id.min);
                convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            String url = mlist.get(position).condIconUrl;
            viewHolder.img.setTag(url);
            new ImagerLoader().showImageByThread(viewHolder.img, mlist.get(position).condIconUrl);
            viewHolder.time.setText(mlist.get(position).date);
            viewHolder.max.setText(mlist.get(position).max + "℃");
            viewHolder.info.setText(mlist.get(position).txt_d);
            viewHolder.min.setText(mlist.get(position).min + "℃");
            return convertView;
    }

    class ViewHolder{
        public TextView time, info, max , min;//today_date, today_weather, today_max, today_min;
        public ImageView img;//today_img;
    }
}
