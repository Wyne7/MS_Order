package com.meb.serviceInterface;

import com.meb.dto.DataRequest;
import com.meb.dto.DataResponse;
import com.meb.dto.OrderDTO;
import com.meb.dto.PaymentDTO;

public interface OrderServiceInterface  {

    void saveOrder(DataRequest dto);
    void delete(DataRequest dto);
    
}