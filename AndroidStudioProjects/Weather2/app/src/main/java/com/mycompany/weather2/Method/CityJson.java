package com.mycompany.weather2.Method;

import com.mycompany.weather2.Data.City;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ljx on 2016/1/26.
 */
public class CityJson {
    public static ArrayList<City> GetCityInfo(String city) {
        ArrayList<City> dataArrayList = new ArrayList<>();

        try{
            JSONObject object = new JSONObject(city);
            JSONArray City = object.getJSONArray("city_info");
            for (int i = 0; i < City.length(); i++) {

                City citys = new City();

                JSONObject result = City.getJSONObject(i);

                String cityname = result.getString("city");
                String id = result.getString("id");
                String prov = result.getString("prov");

                citys.setCityname(cityname);
                citys.setId(id);
                citys.setProv(prov);

                dataArrayList.add(citys);
            }
            return dataArrayList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
