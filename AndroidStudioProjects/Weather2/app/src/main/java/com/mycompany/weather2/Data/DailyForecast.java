package com.mycompany.weather2.Data;

/**
 * Created by Ljx on 2016/1/30.
 */
public class DailyForecast {

    public String condIconUrl;
    public String sr;      //日出时间
    public String ss;      //日落时间
    public String dir;      //风向
    public String sc;       //风力等级
    public int spd;         //风速
    public String txt_d;    //白天天气
    public String txt_n;    //夜晚天气
    public String date;     //时间
    public double pop;       //降雨量
    public int max;
    public int min;

    public void setCondIconUrl(String condIconUrl) {
        this.condIconUrl = condIconUrl;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }

    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString(){
        return "时间" + this.date + "\n" + "日出时间" + this.sr + "\n" + "日落时间" + this.ss + "\n" +
                "白天天气" + this.txt_d + "\n" + "夜晚天气" + this.txt_n + "\n" + "风力等级" + this.sc + "\n" + "风向" + this.dir + "\n"
                + "风速" + this.spd  + "\n" +"降雨量" + this.pop;
    }

}
