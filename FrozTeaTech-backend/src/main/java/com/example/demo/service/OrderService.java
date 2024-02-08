package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Order;

public interface OrderService {
	public List<Order> getAllOrders();

	public Order getOrderById(Long orderId);

	public Order saveOrder(Order order,long customerId,int item_id);

	public void deleteOrder(Long orderId);
	
	public List<Order> findOrdersByCustomerId(long customerId);
}
