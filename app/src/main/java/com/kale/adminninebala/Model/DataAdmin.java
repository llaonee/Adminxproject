package com.kale.adminninebala.Model;

import com.google.gson.annotations.SerializedName;

public class DataAdmin {

	@SerializedName("password")
	private String password;
	private String loginTime;
	@SerializedName("admin_id")
	private int adminId;
	@SerializedName("admin_name")
	private String adminName;
	@SerializedName("admin_nohp")
	private String adminNohp;
	@SerializedName("admin_email")
	private String adminEmail;
	@SerializedName("username")
	private String userName;

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

	public void getAdminId(int adminId){
		this.adminId = adminId;
	}
	public int getAdminId(){
		return adminId;
	}

	public void setAdminName(String adminName){
		this.adminName = adminName;
	}
	public String getAdminName(){
		return adminName;
	}

	public  void setAdminNohp(String adminNohp){
		this.adminNohp = adminNohp;
	}
	public  String getAdminNohp(){
		return adminNohp;
	}

	public void setAdminEmail(String adminEmail){
		this.adminEmail = adminEmail;
	}
	public  String getAdminEmail(){
		return adminEmail;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	public  String getUserName(){
		return userName;
	}

	@Override
 	public String toString(){
		return 
			"DataAdmin{" +
			"password = '" + password + '\'' + 
			",login_time = '" + loginTime + '\'' +
			",admin_id = '" + adminId + '\'' +
			",admin_name = '" + adminName + '\'' +
			",admin_nohp = '" + adminNohp + '\'' +
			",admin_email = '" + adminEmail + '\'' +
			",username = '" + userName + '\'' +
			"}";
		}
}
