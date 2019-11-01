package com.kale.adminninebala.Model;

import java.util.List;

/**
 * Created by Kale on 12/25/2018.
 */

public class ResponseZipcode {
    private String pesan;
    private List<DataZipcode> data;
    private boolean status;

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setData(List<DataZipcode> data){
        this.data = data;
    }

    public List<DataZipcode> getData(){
        return data;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "ResponseZipcode{" +
                        "pesan = '" + pesan + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
