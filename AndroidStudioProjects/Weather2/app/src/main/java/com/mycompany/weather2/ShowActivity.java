package com.mycompany.weather2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.BaseAdapter;

import com.mycompany.weather2.Data.City;
import com.mycompany.weather2.Data.DailyForecast;
import com.mycompany.weather2.Method.CityJson;
import com.mycompany.weather2.Method.HttpCon;
import com.mycompany.weather2.Method.WeatherJson;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowActivity extends EuclidActivity {

    String cityinfo;
    String WeatherData;
    String Id;
    String CityName;
    ArrayList<DailyForecast>  WeatherArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        CityName = extras.getString("CityName");

        new Thread() {
            @Override
            public void run() {
                try {

                    cityinfo = HttpCon.getUrlResponse("https://api.heweather.com/x3/citylist?search=allchina&key=7df070b5cd6442a3b443b680bc645338");
                    ArrayList<City> CityArrayList = CityJson.GetCityInfo(cityinfo);

                    for (int i = 0; i < CityArrayList.size(); i++){
                        if (CityName.equals(CityArrayList.get(i).getCityname())){
                            Id = CityArrayList.get(i).getId();
                            WeatherData = HttpCon.getUrlResponse("https://api.heweather.com/x3/weather?cityid=" + Id + "&key=7df070b5cd6442a3b443b680bc645338");
                            Log.d("天气：", WeatherData);
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = WeatherData;
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0x123)
            {
                WeatherArraylist = WeatherJson.GetWeatherInfo(WeatherData);
                Log.d("xxx", WeatherData);
            }
        }
    };

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.anastasia,
                R.drawable.andriy,
                R.drawable.dmitriy,
                R.drawable.dmitry_96,
                R.drawable.ed,
                R.drawable.illya,
                R.drawable.kirill,
                R.drawable.konstantin,
                R.drawable.oleksii,
                R.drawable.pavel,
                R.drawable.weather101};
        String[] names = getResources().getStringArray(R.array.array_names);

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }
}
