package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kale on 12/16/2018.
 */

public class DataUser {

    @SerializedName("password")
    private String password;
    private String loginTime;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("username")
    private String userName;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("birthdate")
    private int birthdate;
    @SerializedName("mont_of_birth")
    private int montOfBirth;
    @SerializedName("year_of_birth")
    private int yearOfBirth;
    @SerializedName("gender")
    private String gender;

    @SerializedName("marital_status")
    private String maritalStatus;

    @SerializedName("religion")
    private String religion;


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

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return userId;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setBirthdate(int birthdate){
        this.birthdate = birthdate;
    }
    public int getBirthdate(){
        return birthdate;
    }

    public void setMontOfBirth(int montOfBirth){
        this.montOfBirth = montOfBirth;
    }
    public int getMontOfBirth(){
        return montOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }
    public int getYearOfBirth(){
        return yearOfBirth;
    }

    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }

    public void setMaritalStatus(String maritalStatus){
        this.maritalStatus = maritalStatus;
    }
    public String getMaritalStatus(){
        return maritalStatus;
    }

    public void setReligion(String religion){
        this.religion = religion;
    }
    public String getReligion(){
        return religion;
    }


    @Override
    public String toString(){
        return
                "DataUser{" +
                        "password = '" + password + '\'' +
                        ",login_time = '" + loginTime + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",nickname = '" + nickname + '\'' +
                        ",username = '" + userName + '\'' +
                        ",phone_number = '" + phoneNumber + '\'' +
                        ",email = '" + email + '\'' +
                        ",birthdate = '" + birthdate + '\'' +
                        ",mont_of_birth = '" + montOfBirth + '\'' +
                        ",year_of_birth = '" + yearOfBirth + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",marital_status = '" + maritalStatus + '\'' +
                        ",religion = '" + religion + '\'' +
                        "}";
    }

}
