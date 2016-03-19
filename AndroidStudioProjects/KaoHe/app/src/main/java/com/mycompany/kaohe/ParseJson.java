package com.mycompany.kaohe;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ljx on 2015/12/10.
 */
public class ParseJson {
    public static ArrayList<DATA> parsejson(String json) {

        ArrayList<DATA> dataArrayList = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSON jsons = new JSON();
            String reason = object.getString("reason");
            int status = object.getInt("status");
            jsons.setReason(reason);
            jsons.setStatus(status);
            JSONArray Data = object.getJSONArray("data");
            for (int i = 0; i < Data.length(); i++) {

                DATA data = new DATA();

                JSONObject result = Data.getJSONObject(i);

                String content = result.getString("content");
                String time = result.getString("time");
                String title = result.getString("title");
                String url = result.getString("url");

                data.setContent(content);
                data.setTime(time);
                data.setTitle(title);
                data.setUrl(url);

                dataArrayList.add(data);

            }
            return dataArrayList;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
/*    public static ArrayList<DATA> parsejson(String json) {

        ArrayList<DATA> dataArrayList = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            JSON jsons = new JSON();
            String reason = object.getString("reason");
            int status = object.getInt("status");
            jsons.setReason(reason);
            jsons.setStatus(status);
            JSONArray Data = object.getJSONArray("data");
            for (int i = 0; i < Data.length(); i++) {

                DATA data = new DATA();

                JSONObject result = Data.getJSONObject(i);

                String content = result.getString("content");
                String time = result.getString("time");
                String title = result.getString("title");
                String url = result.getString("url");

                data.setContent(content);
                data.setTime(time);
                data.setTitle(title);
                data.setUrl(url);

                dataArrayList.add(data);

            }
                return dataArrayList;
        }
        catch(Exception e){
                e.printStackTrace();
            }
            return null;
    }
}*/
