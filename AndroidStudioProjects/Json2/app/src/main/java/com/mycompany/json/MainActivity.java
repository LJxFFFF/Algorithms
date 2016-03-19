package com.mycompany.json;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    //private TextView textJson;
    /*private static final String JSON = "{\"result\": 1,\"personData\": [{\"name\": \"LiuJx\",\"url\": \"http://www.baidu.com\",\"age\": 19,\"school\": \"SCUEC\"}]}\n" +
            "\t";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //textJson = (TextView)findViewById(R.id.textJson);
            }
        });
            StringBuilder json1 = new StringBuilder();
            json1.append("[");
            json1.append("{:'id'0,'name':'aaa','age':10},");
            json1.append("{'id':1,'name':'bbb','age':20},");
            json1.append("{'id':2,'name':'ccc','age':30},");
            json1.append("{'id':3,'name':'ddd','age':40}");
            json1.append("]");
            ArrayList<Person> persons = new ArrayList<Person>();
                try{
                JSONArray jsonArray = new JSONArray(json1.toString());
                for(int i = 0;i < jsonArray.length();i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Person person = new Person();
                    person.setId(i+"");
                    person.setName(jsonObject.getString("name"));
                    person.setAge(jsonObject.getString("age"));
                    persons.add(person);
                }
            }catch (Exception e){e.printStackTrace();}
            StringBuilder jsonOver = new StringBuilder();
            //for (Person person : persons) {
                jsonOver.append(persons.toString() + "\n");
            //}
        System.out.println(jsonOver);
        TextView tv = (TextView)findViewById(R.id.text_view);
        tv.setText(jsonOver.toString());
    }
}

   /* public void JSON(View v) {
        parseJson(JSON);
    }
private void parseJson(String strResult) {
        try {
            JSONObject jsonObj = new JSONObject(strResult).getJSONObject("result");
            String name = jsonObj.getString("name");
            int age = jsonObj.getInt("age");
            String school = jsonObj.getString("school");
            //textJson.setText("name:"+name + ", age:" + age + "school" + school);
        } catch (JSONException e) {
            System.out.println("Json parse error");
            e.printStackTrace();
        }
    }*/
