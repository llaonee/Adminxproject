package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kale on 1/9/2019.
 */

public class DataPromo {
    @SerializedName("id_promo")
    private int idPromo;
    @SerializedName("promo_name")
    private String promoName;
    @SerializedName("promo_desc")
    private String promoDesc;
    @SerializedName("promo_date")
    private String promoDate;
    @SerializedName("promo_enddate")
    private String promoEnddate;
    @SerializedName("promo_image")
    private String promoImage;
    @SerializedName("group_id")
    private int groupId;
    @SerializedName("group_name")
    private String groupName;
    @SerializedName("group_image")
    private String groupImage;
    @SerializedName("id_manager")
    private int idManager;

    public void setIdPromo(int idPromo){
        this.idPromo = idPromo;
    }
    public int getIdPromo(){
        return idPromo;
    }

    public void setPromoName(String promoName){
        this.promoName = promoName;
    }
    public String getPromoName(){
        return promoName;
    }

    public void setPromoDesc(String promoDesc){
        this.promoDesc = promoDesc;
    }
    public String getPromoDesc(){
        return promoDesc;
    }

    public void setPromoDate(String promoDate){
        this.promoDate = promoDate;
    }
    public String getPromoDate(){
        return promoDate;
    }

    public String getPromoEnddate() {
        return promoEnddate;
    }

    public void setPromoEnddate(String promoEnddate) {
        this.promoEnddate = promoEnddate;
    }

    public void setPromoImage(String promoImage){
        this.promoImage = promoImage;
    }
    public String getPromoImage(){
        return promoImage;
    }

    public void setGroupId(int groupId){
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

    public void setGroupImage(String groupImage){
        this.groupImage = groupImage;
    }
    public String getGroupImage(){
        return groupImage;
    }

    public void setIdManager(int idManager){
        this.idManager = idManager;
    }
    public int getIdManager(){
        return idManager;
    }

    @Override
    public String toString(){
        return
                "DataPromo{" +
                        "id_promo = '" + idPromo + '\'' +
                        ",promo_name = '" + promoName + '\'' +
                        ",promo_desc = '" + promoDesc + '\'' +
                        ",promo_date = '" + promoDate + '\'' +
                        ",promo_enddate = '" + promoEnddate + '\'' +
                        ",promo_image = '" + promoImage + '\'' +
                        ",group_id = '" + groupId + '\'' +
                        ",group_name = '" + groupName + '\'' +
                        ",group_image = '" + groupImage + '\'' +
                        ",id_manager = '" + idManager + '\'' +
                        "}";
    }
}
