package com.kale.adminninebala.Model;

import java.util.List;

public class ResponseLoginManager {
	private String pesan;
	private List<DataManager> data;
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataManager> data){
		this.data = data;
	}

	public List<DataManager> getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return
			"ResponseLoginManager{" +
					"pesan = '" + pesan + '\'' +
					",data = '" + data + '\'' +
					",status = '" + status + '\'' +
					"}";
		}
}