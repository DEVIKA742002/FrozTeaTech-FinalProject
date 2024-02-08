package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
	public List<Order> findByOrderId(long orderId);
	List<Order> findByCustomerCustomerId(long customerId);
}
