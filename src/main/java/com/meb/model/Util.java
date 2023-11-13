package com.meb.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@MappedSuperclass
public class Util implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private String keyGenerate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyGenerate() {
		return keyGenerate;
	}

	public void setKeyGenerate(String keyGenerate) {
		this.keyGenerate = keyGenerate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
