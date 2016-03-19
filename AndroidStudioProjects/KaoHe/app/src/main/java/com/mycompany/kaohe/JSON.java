package com.mycompany.kaohe;

import java.util.List;

/**
 * Created by Ljx on 2015/12/5.
 */
public class JSON {
    private List<DATA> datas;
    private String reason;
    private int status;

    public void setDatas(List<DATA> datas) {
        this.datas = datas;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DATA> getDatas() {
        return datas;
    }

    public String getReason() {
        return reason;
    }

    public int getStatus() {
        return status;
    }

}

