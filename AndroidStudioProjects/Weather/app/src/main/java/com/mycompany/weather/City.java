package com.mycompany.weather;

/**
 * Created by Ljx on 2016/1/26.
 */
public class City {
    public String cityname;
    public String id;
    public String prov;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }
    @Override
    public String toString() {
        return "城市：" + this.cityname;// + " " + "ID：" + this.id + " " + "省份：" + this.prov + "\n";
    }
}
