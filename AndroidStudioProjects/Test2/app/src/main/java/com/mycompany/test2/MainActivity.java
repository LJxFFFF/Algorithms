package com.mycompany.test2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView response;
    HttpClient httpClient;
    String result = "";
    private HttpResponse httpResponse;
    private HttpEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpClient = new DefaultHttpClient();
        response = (TextView)findViewById(R.id.show);
    }
    public void GetCode(View v)
    {
        new Thread(){
            @Override
            public void run(){
                InputStream inputStream = null;
                try
                {
                    HttpGet get = new HttpGet("http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101121301");
                    httpResponse = httpClient.execute(get);
                    entity = httpResponse.getEntity();
                    inputStream = entity.getContent();

                    if(entity != null)
                    {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(inputStream));
                        String line = null;
                        while ((line = br.readLine()) != null)
                        {
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = line;
                            handler.sendMessage(msg);
                        }

                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }finally {
                    try {
                         inputStream.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0x123)
            {
                result = result + msg.obj.toString();
                response.setText(result);
            }
        }
    };
}


/*            @Override
            public void GetCode(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        HttpGet httpget = new HttpGet(url);
                        HttpClient httpClient = new DefaultHttpClient();
                        try {
                            HttpResponse httpResponse = httpClient.execute(httpget);
                            HttpEntity entity = httpResponse.getEntity();
                            if (entity != null) {
                                BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
                                String line = null;
                                while ((line = br.readLine()) != null) {
                                    Message msg = new Message();
                                    msg.what = 11;
                                    msg.obj = line;
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
*//*    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 11) {
                handler.sendMessage(msg);
                result = result + msg.obj.toString();
                text1.setText(result);
            }
        }
    };*//*
}*/
