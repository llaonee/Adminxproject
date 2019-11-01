package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kale on 12/31/2018.
 */

public class DataGroup {
    @SerializedName("group_id")
    private int groupId;
    @SerializedName("group_name")
    private String groupName;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("group_desc")
    private String groupDesc;
    @SerializedName("group_image")
    private String groupImage;
    @SerializedName("status_manager")
    private int statusManager;
    @SerializedName("manager_name")
    private String managerName;

    public void setGroupId(int userId){
        this.groupId = groupId;
    }
    public int getGroupId(){
        return groupId;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getGroupName(){
        return groupName;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setGroupDesc(String groupDesc){
        this.groupDesc = groupDesc;
    }
    public String getGroupDesc(){
        return groupDesc;
    }

    public void setGroupImage(String groupImage){
        this.groupImage = groupImage;
    }
    public String getGroupImage(){
        return groupImage;
    }

    public void setStatusManager(int statusManager){
        this.statusManager = statusManager;
    }
    public int getStatusManager(){
        return statusManager;
    }

    public void setManagerName(String managerName){
        this.managerName = managerName;
    }
    public String getManagerName(){
        return managerName;
    }

    @Override
    public String toString(){
        return
                "DataGroup{" +
                        "group_id = '" + groupId + '\'' +
                        ",group_name = '" + groupName + '\'' +
                        ",alamat = '" + alamat + '\'' +
                        ",group_desc = '" + groupDesc + '\'' +
                        ",group_image = '" + groupImage + '\'' +
                        ",status_manager = '" + statusManager + '\'' +
                        ",manager_name = '" + managerName + '\'' +
                        "}";
    }

}
