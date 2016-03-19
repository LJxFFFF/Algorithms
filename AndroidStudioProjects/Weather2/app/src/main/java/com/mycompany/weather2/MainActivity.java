package com.mycompany.weather2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button urlConnectionBtn;
    EditText editText;
    String CityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUI();

        urlConnectionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CityName = editText.getText().toString();
                Intent newActivity = new Intent(MainActivity.this, ShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CityName", CityName);
                newActivity.putExtras(bundle);
                startActivity(newActivity);

            }
    /*        Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    if(msg.what == 0x123)
                    {
                        Log.d("msg.obj:", msg.obj.toString());
                        //result = result + msg.obj + "\n" + "\n" + "\n";
                        //showTextView.setText(result);
                    }
                }
            };*/
        });
    }
    public void initUI(){
        urlConnectionBtn = (Button) findViewById(R.id.Btn);
        editText = (EditText) findViewById(R.id.input);
    }
}
