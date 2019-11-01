package com.kale.adminninebala.Model;

/**
 * Created by Kale on 12/30/2018.
 */

public class DataGroupManager {
    private String group_id, group_name, group_desc, group_image, status_manager, manager_name;
    public DataGroupManager(){
    }
    public DataGroupManager(String group_id, String group_name, String group_desc, String group_image, String status_manager, String manager_name){
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_desc = group_desc;
        this.group_image = group_image;
        this.status_manager = status_manager;
        this.manager_name = manager_name;
    }
    public String getGroup_id(){
        return group_id;
    }
    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }

    public String getGroup_name(){
        return group_name;
    }
    public void setGroup_name(String group_name){
        this.group_name = group_name;
    }

    public String getGroup_desc(){
        return group_desc;
    }
    public void setGroup_desc(String group_desc){
        this.group_desc = group_desc;
    }

    public String getGroup_image(){
        return group_image;
    }
    public void setGroup_image(String group_image){
        this.group_image = group_image;
    }

    public String getStatus_manager(){
        return status_manager;
    }
    public void setStatus_manager(String status_manager){
        this.status_manager = status_manager;
    }

    public String getManager_name(){
        return manager_name;
    }
    public void setManager_name(String manager_name){
        this.manager_name = manager_name;
    }
}
