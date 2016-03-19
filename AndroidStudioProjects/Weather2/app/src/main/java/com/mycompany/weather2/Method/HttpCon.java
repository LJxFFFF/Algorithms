package com.mycompany.weather2.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ljx on 2016/1/26.
 */
public class HttpCon {
    public static String getUrlResponse(String urlString) {
        HttpURLConnection connection = null;
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String inputLine = "";
            while ((inputLine = bufferedReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return resultData;
    }
}
