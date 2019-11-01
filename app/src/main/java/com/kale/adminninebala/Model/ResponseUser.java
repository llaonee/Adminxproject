package com.kale.adminninebala.Model;

import java.util.ArrayList;

/**
 * Created by Kale on 12/16/2018.
 */

public class ResponseUser {
    private String pesan;
    private ArrayList<DataUser> data;
    private boolean status;

    public void setPesan(String pesan){
        this.pesan = pesan;
    }

    public String getPesan(){
        return pesan;
    }

    public void setData(ArrayList<DataUser> data){
        this.data = data;
    }

    public ArrayList<DataUser> getData(){
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
                "ResponseUser{" +
                        "pesan = '" + pesan + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
