package com.kale.adminninebala.Model;

import java.util.ArrayList;

/**
 * Created by Kale on 12/16/2018.
 */

public class ResponsePromo {
    private String pesan;
    private ArrayList<DataPromo> data;
    private boolean status;

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setData(ArrayList<DataPromo> data){
        this.data = data;
    }

    public ArrayList<DataPromo> getData(){
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
                "ResponsePromo{" +
                        "pesan = '" + pesan + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
