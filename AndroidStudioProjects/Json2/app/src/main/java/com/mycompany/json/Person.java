package com.mycompany.json;

/**
 * Created by Ljx on 2015/11/27.
 */
public class Person {
    private String id;
    private String name;
    private String age;
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    @Override
    public String toString() {
        return "\n" + "ID：" + this.id + "\n姓名：" + this.name + "\n年龄：" + this.age + "\n" ;
    }
}
