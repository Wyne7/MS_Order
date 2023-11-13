package com.meb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meb.dto.DataRequest;
import com.meb.dto.OrderDTO;
import com.meb.dto.PaymentDTO;
import com.meb.kafkaproducerconsumer.KafkaProducer;
import com.meb.kafkaserviceinterface.OrderKafkaServiceInterface;
import com.meb.serviceInterface.OrderServiceInterface;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class OrderController {
	@Autowired
	OrderKafkaServiceInterface orderKafkaServiceInterface;
	@PostMapping(value = "/orderSave")
	public void saveOrder(@RequestBody DataRequest dto) {
		orderKafkaServiceInterface.OrderKafka(dto);
	}
}
