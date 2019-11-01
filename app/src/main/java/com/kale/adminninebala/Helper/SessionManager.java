package com.kale.adminninebala.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by imastudio on 2/9/16.
 */
public class SessionManager {
	private static final String KEY_TOKEN = "tokenLogin";
	private static final String KEY_LOGIN = "isLogin";
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	int PRIVATE_MODE =0;    Context c;

	//0 agar cuma bsa dibaca hp itu sendiri
	String PREF_NAME="GojegClonePref";

	//konstruktor
	public SessionManager(Context c){
		this.c = c;
		pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	//membuat session login
	public void createLoginSession(String token){
		editor.putString(KEY_TOKEN, token);
		editor.putBoolean(KEY_LOGIN, true);
		editor.commit();
		//commit digunakan untuk menyimpan perubahan
	}
	//mendapatkan token
	public String getToken(){
		return pref.getString(KEY_TOKEN, "");
	}
	//cek login
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_LOGIN, false);
	}
	//logout user
	public void logout(){
		editor.clear();
		editor.commit();
	}

	public void setAdminName(String adminName){
		editor.putString("admin_name", adminName);
		editor.commit();
	}
	public String getAdminName(){
		return pref.getString("admin_name", "");
	}

	public void setAdminEmail(String adminEmail){
		editor.putString("admin_email", adminEmail);
		editor.commit();
	}
	public String getAdminEmail(){
		return pref.getString("admin_email", "");
	}

	public void setAdminNohp(String adminNohp){
		editor.putString("admin_nohp", adminNohp);
		editor.commit();
	}
	public String getAdminNohp(){
		return pref.getString("admin_nohp", "");
	}

	public void setAdminId(int adminId){
		editor.putInt("admin_id", adminId);
		editor.commit();
	}
	public int getAdminId(){
		return pref.getInt("admin_id", 0);
	}

	public void setIdManager(int idManager){
		editor.putInt("id_manager", idManager);
		editor.commit();
	}
	public int getIdManager(){
		return pref.getInt("id_manager", 0);
	}

	public void setManagerName(String managerName){
		editor.putString("manager_name", managerName);
		editor.commit();
	}
	public String getManagerName(){
		return pref.getString("manager_name", "");
	}

	public void setManagerGroup(String managerGroup){
		editor.putString("manager_group", managerGroup);
		editor.commit();
	}
	public String getManagerGroup(){
		return pref.getString("manager_group", "");
	}

}
