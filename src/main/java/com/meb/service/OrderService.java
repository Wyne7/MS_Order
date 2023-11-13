package com.meb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meb.dto.DataRequest;
import com.meb.dto.DataResponse;
import com.meb.dto.LogType;
import com.meb.dto.LoggerDTO;
import com.meb.dto.PaymentDTO;
import com.meb.firebase.FirebaseConfig;
import com.meb.kafkaproducerconsumer.KafkaProducer;
import com.meb.model.OrderModel;
import com.meb.persistence.OrderRepository;
import com.meb.serviceInterface.OrderServiceInterface;

import jakarta.transaction.Transactional;

@Service
public class OrderService implements OrderServiceInterface {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	KafkaProducer kafkaProducer;
	@Autowired
	private KafkaProducer KafkaProducer;
	
	 @Autowired
	    private KafkaTemplate<String,String>  kafkaTemplate;
	@Transactional
	public void saveOrder(DataRequest dto) {
		PaymentDTO res = new PaymentDTO();
		OrderModel orderModel = new OrderModel();
		orderModel.setOrederName(dto.getOrderDto().getOrederName());
		orderModel.setKeyGenerate(dto.getKey());
		orderModel.setSyskey(dto.getOrderDto().getSyskey());
		try {
			orderRepository.save(orderModel);
			KafkaProducer.sendOrderEvent(dto);
		} catch (Exception e) {
		}
	}
	
		@Transactional
	    public void delete(DataRequest dataRequest) {
	        OrderModel orderModel = orderRepository.reverse(dataRequest.getKey(),false);
	        ObjectMapper objectMapper = new ObjectMapper();
	        orderModel.setDeleteStatus(true);
			Map<String, Object> notification = new HashMap<String, Object>();
			notification.put("title", "Your notification title");
			notification.put("body", "Your notification body");
	        try {
	        	DataResponse Dres = new DataResponse();
				Dres.setDescription("Order Save Fail");
				Dres.setStatus(0);
	        	orderRepository.delete(orderModel);
	        	LoggerDTO logger=new LoggerDTO();
	        	logger.setId(0);
	        	logger.setLogType(LogType.ERROR);
	        	logger.setMessage("Order Process Fail!!");;
	        	kafkaProducer.Logger(logger);
	        	FirebaseConfig.sendNotification(notification, dataRequest.getDeviceToken(), Dres).thenAccept(result -> {

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
	        }

	    }
}
