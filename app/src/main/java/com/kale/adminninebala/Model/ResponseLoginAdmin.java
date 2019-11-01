package com.kale.adminninebala.Model;

import java.util.List;

public class ResponseLoginAdmin {
	private String pesan;
	private List<DataAdmin> data;
	private boolean status;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataAdmin> data){
		this.data = data;
	}

	public List<DataAdmin> getData(){
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
			"ResponseLoginAdmin{" +
					"pesan = '" + pesan + '\'' +
					",data = '" + data + '\'' +
					",status = '" + status + '\'' +
					"}";
		}
}