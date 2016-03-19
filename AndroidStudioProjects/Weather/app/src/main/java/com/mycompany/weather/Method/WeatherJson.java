package com.mycompany.weather.Method;

import com.mycompany.weather.DailyForecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ljx on 2016/1/30.
 */
public class WeatherJson {
    public static ArrayList<DailyForecast> GetWeatherInfo(String weather) {
        ArrayList<DailyForecast> dataArrayList = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(weather);
            JSONArray weatherdata = object.getJSONArray("HeWeather data service 3.0");
            JSONObject object1 = new JSONObject();
            object1 = weatherdata.getJSONObject(0);
            JSONArray daily_foreacst = object1.getJSONArray("daily_forecast");
            //JSONArray suggestion = weatherdata.getJSONArray(6);

            for (int i = 0; i < daily_foreacst.length(); i++) {
                DailyForecast dailyForecast = new DailyForecast();

                JSONObject result = daily_foreacst.getJSONObject(i);

                String date = result.getString("date");
                double pop = result.getDouble("pop");

                dailyForecast.setDate(date);
                dailyForecast.setPop(pop);

                JSONObject astro = result.getJSONObject("astro");
                JSONObject cond = result.getJSONObject("cond");
                JSONObject tmp = result.getJSONObject("tmp");
                JSONObject wind = result.getJSONObject("wind");

                String sr = astro.getString("sr");
                String ss = astro.getString("ss");
                dailyForecast.setSr(sr);
                dailyForecast.setSs(ss);

                String condIconUrl = cond.getString("code_d");
                String txt_d = cond.getString("txt_d");
                String txt_n = cond.getString("txt_n");
                dailyForecast.setCondIconUrl(condIconUrl);
                dailyForecast.setTxt_d(txt_d);
                dailyForecast.setTxt_n(txt_n);

                int max_tmp = tmp.getInt("max");
                int min_tmp = tmp.getInt("min");
                dailyForecast.setMax(max_tmp);
                dailyForecast.setMin(min_tmp);

                String dir = wind.getString("dir");
                String sc = wind.getString("sc");
                int spd = wind.getInt("spd");
                dailyForecast.setDir(dir);
                dailyForecast.setSc(sc);
                dailyForecast.setSpd(spd);

                dataArrayList.add(dailyForecast);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataArrayList;
    }
}
