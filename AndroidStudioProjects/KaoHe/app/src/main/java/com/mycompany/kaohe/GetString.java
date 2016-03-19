package com.mycompany.kaohe;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ljx on 2015/12/10.
 */
public class GetString {
    public static String ReadText(){

        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "data.txt");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = text.toString();
        return json;
    }
}
