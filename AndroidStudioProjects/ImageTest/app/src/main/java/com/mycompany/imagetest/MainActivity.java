package com.mycompany.imagetest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        bt = (Button) findViewById(R.id.ClickButton);

        MyAdapter mAdapter = new MyAdapter(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "点击了第"+(position+1)+"行", Toast.LENGTH_SHORT).show();
        }
        });
    }
    private ArrayList<HashMap<String, Object>>getDate() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();

        for(int i = 0; i<30; i++)
        {

            HashMap<String, Object> map = new HashMap<>();
            map.put("ItemTitle", "第"+(i+1)+"行");
            map.put("ItemText",  "这是第"+(i+1)+"行");
            listItem.add(map);
        }
        return listItem;
    }
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return getDate().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Log.v("MyListViewBase", "getView" + position + " " + convertView);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();

                holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
                holder.text = (TextView) convertView.findViewById(R.id.ItemText);
                holder.bt = (Button) convertView.findViewById(R.id.ClickButton);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.title.setText(getDate().get(position).get("ItemTitle").toString());
            holder.text.setText(getDate().get(position).get("ItemText").toString());

            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("MyListViewBase", "你点击了按钮" + position);
                }
            });
            return convertView;
        }
    }
    public final class ViewHolder{
        public TextView title;
        public TextView text;
        public Button   bt;
    }
}
