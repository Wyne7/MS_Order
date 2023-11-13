package com.meb.kafkaproducerconsumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dto.DataRequest;
import com.meb.dto.DataResponse;
import com.meb.dto.LogType;
import com.meb.dto.LoggerDTO;
import com.meb.dto.OrderDTO;
import com.meb.dto.PaymentDTO;
import com.meb.firebase.FirebaseConfig;
import com.meb.service.OrderService;
import com.meb.serviceInterface.OrderServiceInterface;

@Service
public class KafkaConsumer {

	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private OrderServiceInterface service;

	@KafkaListener(topics = "order_topic", groupId = "payment")
	public void consumeOrderEvent(String message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			DataRequest requestDTO = objectMapper.readValue(message, DataRequest.class);
			kafkaTemplate.send("payment_topic", "payment_processed_message");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@KafkaListener(topics = "fail_topic", groupId = "payment")
	public void consumeFailEvent(String message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			DataRequest requestDTO = objectMapper.readValue(message, DataRequest.class);
			service.delete(requestDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@KafkaListener(topics = "success_topic", groupId = "payment")
	public void successTopic(String message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			DataRequest requestDTO = objectMapper.readValue(message, DataRequest.class);
			DataResponse Dres = new DataResponse();
			
			LoggerDTO loggerDTO=new LoggerDTO();
			loggerDTO.setId(0);
			loggerDTO.setLogType(LogType.INFO);
			loggerDTO.setMessage("Payment SuccessFul");
			kafkaProducer.Logger(loggerDTO);
			Dres.setDescription("Order Save Success");
			Dres.setStatus(0);
			Map<String, Object> notification = new HashMap<String, Object>();
			notification.put("title", "Your notification title");
			notification.put("body", "Your notification body");
        	FirebaseConfig.sendNotification(notification, requestDTO.getDeviceToken(), Dres).thenAccept(result -> {

				if (result != null) {

					System.out.println("Notification sent successfully: " + result);
				} else {

					System.out.println("Notification sending failed.");
				}
			}).exceptionally(ex -> {

				System.err.println("An error occurred: " + ex.getMessage());
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
