package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kale on 12/21/2018.
 */

public class DataManager {
    private String password;
    private String loginTime;
    @SerializedName("id_manager")
    private int idManager;
    @SerializedName("manager_name")
    private String managerName;
    @SerializedName("manager_email")
    private String managerEmail;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("manager_group")
    private String managerGroup;
    @SerializedName("username")
    private String userName;
    @SerializedName("manager_picture")
    private String managerPicture;

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setLoginTime(String loginTime){
        this.loginTime = loginTime;
    }
    public String getLoginTime(){
        return loginTime;
    }

    public void setIdManager(int idManager){
        this.idManager = idManager;
    }
    public int getIdManager(){
        return idManager;
    }

    public void setManagerName(String managerName){
        this.managerName = managerName;
    }
    public String getManagerName(){
        return managerName;
    }

    public void setManagerEmail(String managerEmail){
        this.managerEmail = managerEmail;
    }
    public String getManagerEmail(){
        return managerEmail;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setManagerGroup(String managerGroup){
        this.managerGroup = managerGroup;
    }
    public String getManagerGroup(){
        return managerGroup;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public  String getUserName(){
        return userName;
    }

    public void setManagerPicture(String managerPicture){
        this.managerPicture= managerPicture;
    }
    public  String getManagerPicture(){
        return managerPicture;
    }

    @Override
    public String toString(){
        return
                "DataManager{" +
                        "password = '" + password + '\'' +
                        ",login_time = '" + loginTime + '\'' +
                        ",id_manager = '" + idManager + '\'' +
                        ",manager_name = '" + managerName + '\'' +
                        ",manager_email = '" + managerEmail + '\'' +
                        ",phone_number = '" + phoneNumber + '\'' +
                        ",manager_group = '" + managerGroup + '\'' +
                        ",username = '" + userName + '\'' +
                        ",manager_picture = '" + managerPicture + '\'' +
                        "}";
    }


}
