package com.mycompany.weather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mycompany.weather.Method.CityJson;
import com.mycompany.weather.Method.HttpCon;
import com.mycompany.weather.Method.ImagerLoader;
import com.mycompany.weather.Method.WeatherAdapter;
import com.mycompany.weather.Method.WeatherJson;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView listView;
    TextView today_date, today_weather, today_max, today_min;
    ImageView today_img;
    String cityinfo;
    String WeatherData;
    String Id;
    String CityName;
    String result = "";
    WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.show);
        today_date = (TextView) findViewById(R.id.today_date);
        today_weather = (TextView) findViewById(R.id.today_weather);
        today_max = (TextView) findViewById(R.id.today_max);
        today_min = (TextView) findViewById(R.id.today_min);
        today_img = (ImageView) findViewById(R.id.today_img);

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
        listView.setAdapter(adapter);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0x123)
            {
                ArrayList<DailyForecast>  WeatherArraylist = WeatherJson.GetWeatherInfo(WeatherData);
                today_date.setText("Today");
                today_weather.setText(WeatherArraylist.get(0).txt_d.toString());
                today_max.setText(String.valueOf(WeatherArraylist.get(0).getMax()) + "℃");
                today_min.setText(String.valueOf(WeatherArraylist.get(0).getMin()) + "℃");
                String url = WeatherArraylist.get(0).condIconUrl;
                today_img.setTag(url);
                new ImagerLoader().showImageByThread(today_img, WeatherArraylist.get(0).condIconUrl);
                WeatherArraylist.remove(0);
                adapter = new WeatherAdapter(ShowActivity.this, WeatherArraylist);
                listView.setAdapter(adapter);
            }
        }
    };
}
