package com.kale.adminninebala.Http;


import com.kale.adminninebala.Model.ResponseGroup;
import com.kale.adminninebala.Model.ResponseLoginAdmin;
import com.kale.adminninebala.Model.ResponseLoginManager;
import com.kale.adminninebala.Model.ResponseManager;
import com.kale.adminninebala.Model.ResponsePromo;
import com.kale.adminninebala.Model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nandoseptianhusni on 11/13/17.
 */

public interface ApiService {


    @FormUrlEncoded
    @POST("loginadmin")
    Call<ResponseLoginAdmin> action_loginAdmin(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("loginmanager")
    Call<ResponseLoginManager> action_loginManager(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("broadcast")
    Call<ResponseUser> action_broadcast(
            @Field("age") String age,
            @Field("married") String married,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("getgroup")
    Call<ResponseGroup> action_group(
            @Field("group") String group
    );

    @FormUrlEncoded
    @POST("getmanager")
    Call<ResponseManager> action_manager(
            @Field("manager") String manager
    );

    @FormUrlEncoded
    @POST("getmanagergroup")
    Call<ResponseGroup> action_managergroup(
            @Field("group") String group
    );

    @FormUrlEncoded
    @POST("getmanagerpromo")
    Call<ResponsePromo> action_managerpromo(
            @Field("groupid") int groupid
    );
    @FormUrlEncoded
    @POST("addzipcode")
    Call<ResponseLoginAdmin> action_addZipcode(
            @Field("zipcode") String zipcode,
            @Field("description") String description
    );
    @FormUrlEncoded
    @POST("broadcastApp")
    Call<ResponseLoginAdmin> action_broadcastApp(
            @Field("content") String contentSMS,
            @Field("gender") String gender,
            @Field("marital") String married,
            @Field("religion") String religion,
            @Field("age") String age
    );

}
