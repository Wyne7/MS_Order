package com.meb.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meb.model.OrderModel;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Integer>{

	OrderModel findByIdAndDeleteStatus(int id,boolean delestatus);

	@Query(value="select * from order_model where key_generate =?1 and delete_status=?2",nativeQuery=true)
	OrderModel reverse(String key,boolean b);
	
}
