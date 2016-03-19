package com.mycompany.json;

import java.util.List;

/**
 * Created by Ljx on 2015/11/22.
 */
public class Result {

    private int result;

    private List<MainActivity.Person> personData;

    public List<MainActivity.Person> getPersonData() {
        return personData;
    }

    public int getResult() {
        return result;
    }

    public void setPersonData(List<MainActivity.Person> personData) {
        this.personData = personData;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
