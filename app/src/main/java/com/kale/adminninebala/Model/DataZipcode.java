package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kale on 12/25/2018.
 */

public class DataZipcode {
    @SerializedName("zipcode_id")
    private int zipcodeId;
    @SerializedName("zipcode")
    private int zipcode;
    @SerializedName("information")
    private String information;
    @SerializedName("available")
    private int available;

    public void setZipcodeId(int zipcodeId){
        this.zipcodeId = zipcodeId;
    }
    public int getZipcodeId(){
        return zipcodeId;
    }

    public void setZipcode(int zipcode){
        this.zipcode = zipcode;
    }
    public int getZipcode(){
        return zipcode;
    }

    public void setInformation(String information){
        this.information = information;
    }
    public String getInformation(){
        return information;
    }

    public void setAvailable(int available){
        this.available = available;
    }
    public int getAvailable(){
        return available;
    }

    @Override
    public String toString(){
        return
                "DataZipcode{" +
                        "zipcode_id = '" + zipcodeId + '\'' +
                        ",zipcode = '" + zipcode + '\'' +
                        ",information = '" + information + '\'' +
                        ",avaliable = '" + available + '\'' +
                        "}";
    }
}
