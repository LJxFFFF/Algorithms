package com.mycompany.kaohe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.BoringLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<DATA> dataArrayList = new ArrayList<>();

    DATA data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = (ListView) findViewById(R.id.MyListView);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<>();


        String json = GetString.ReadText();

/*        ParseJson parseJson = new ParseJson();

        ArrayList<DATA> arrayList = parseJson.parsejson(json);

        System.out.println(arrayList.get(1).toString());*/

        try {
            JSONObject object = new JSONObject(json);
            JSON json1 = new JSON();
            String reason = object.getString("reason");
            int status = object.getInt("status");
            json1.setReason(reason);
            json1.setStatus(status);
            JSONArray Data = object.getJSONArray("data");
            for (int i = 0; i < Data.length(); i++) {

                data = new DATA();

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

                HashMap<String, String> map = new HashMap<>();
                map.put("ItemTitle", title);
                mylist.add(map);

            }
            SimpleAdapter mSchedule = new SimpleAdapter(this, mylist, R.layout.my_listitem, new String[]{"ItemTitle"},
                    new int[]{R.id.ItemTitle});
            list.setAdapter(mSchedule);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent newActivity = new Intent(MainActivity.this, TextActivity.class);
                    data = dataArrayList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("text", data.toString());
                    newActivity.putExtras(bundle);
                    startActivity(newActivity);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*    private  List<JSON> parsejson(String json){
        try {
            JSONObject object = new JSONObject(json);
            List<JSON> jsons = new ArrayList<JSON>();
            String Reason = object.getString("Reason");

            JSONArray Data = object.getJSONArray("data");

            for(int i = 0; i < Data.length(); i++){
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
                StringBuilder jsonOver = new StringBuilder();
            }
            return jsons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/