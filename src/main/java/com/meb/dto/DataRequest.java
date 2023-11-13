package com.meb.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deviceToken;
	private OrderDTO orderDto;
	private PaymentDTO paymentDto;
	private String key;
	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public OrderDTO getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDTO orderDto) {
		this.orderDto = orderDto;
	}

	public PaymentDTO getPaymentDto() {
		return paymentDto;
	}

	public void setPaymentDto(PaymentDTO paymentDto) {
		this.paymentDto = paymentDto;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
