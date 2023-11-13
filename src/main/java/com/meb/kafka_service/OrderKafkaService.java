package com.meb.kafka_service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meb.dto.DataRequest;
import com.meb.kafkaproducerconsumer.KafkaProducer;
import com.meb.kafkaserviceinterface.OrderKafkaServiceInterface;
import com.meb.serviceInterface.OrderServiceInterface;

@Service
public class OrderKafkaService implements OrderKafkaServiceInterface {


	@Autowired
	private OrderServiceInterface orderServiceInterface;

	public void OrderKafka(DataRequest dto) {
		String randomKey =	UUID.randomUUID().toString();
		dto.setKey(randomKey);
		
		orderServiceInterface.saveOrder(dto);
	}
}
