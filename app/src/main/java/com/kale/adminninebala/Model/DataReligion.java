package com.kale.adminninebala.Model;

/**
 * Created by Kale on 12/30/2018.
 */

public class DataReligion {
    private String religion_id, religion, all_religion = "All religion";
    public DataReligion(){
    }
    public DataReligion(String religion_id, String religion, String all_religion){
        this.religion_id = religion_id;
        this.religion = religion;
        this.all_religion = all_religion;
    }
    public String getReligion_id(){
        return religion_id;
    }
    public void setReligion_id(String religion_id){
        this.religion_id = religion_id;
    }

    public String getReligion(){
        return religion;
    }
    public void setReligion(String religion){
        this.religion = religion;
    }

    public  String getAll_religion(String string){
        return all_religion;
    }
    public void setAll_religion(String all_religion){
        this.all_religion = all_religion;
    }

}
