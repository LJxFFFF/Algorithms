package com.mycompany.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ljx on 2015/11/22.
 */
public class TestJson {

    public static void main(String[] args){
        Result result = new Result();
        result.setResult(1);
        List<MainActivity.Person> list = new ArrayList<MainActivity.Person>();
        result.setPersonData(list);

        MainActivity.Person person1 = new MainActivity.Person;

    }
}
