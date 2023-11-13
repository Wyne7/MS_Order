package com.meb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class OrderModel extends Util
{
	public OrderModel() {}
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "syskey", nullable = true)
	private int syskey;
	private String orederName;
    private boolean deleteStatus ;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOrederName() {
		return orederName;
	}
	public void setOrederName(String orederName) {
		this.orederName = orederName;
	}
    
    
	
}
