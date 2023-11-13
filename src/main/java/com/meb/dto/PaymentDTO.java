package com.meb.dto;
import lombok.Data;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;   
	private int syskey;
	private String paymentType;
	private double total_price;
   private boolean delete_status;
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
public String getPaymentType() {
	return paymentType;
}
public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public boolean isDelete_status() {
	return delete_status;
}
public void setDelete_status(boolean delete_status) {
	this.delete_status = delete_status;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
   
}