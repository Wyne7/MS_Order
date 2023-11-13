package com.meb.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int syskey;
	private String orederName;
	private boolean deleteStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSyskey() {
		return syskey;
	}

	public void setSyskey(int syskey) {
		this.syskey = syskey;
	}
	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getOrederName() {
		return orederName;
	}

	public void setOrederName(String orederName) {
		this.orederName = orederName;
	}
    
}

