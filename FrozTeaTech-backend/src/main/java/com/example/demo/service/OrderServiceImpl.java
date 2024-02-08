package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrdersRepository ordersRepository;

	@Autowired
	public OrderServiceImpl(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}

	@Autowired
	CustomerService customerservice;
	@Autowired
	MenuService menuservice;

	@Override
	public List<Order> getAllOrders() {
		return ordersRepository.findAll();
	}

	@Override
	public Order getOrderById(Long orderId) {
		return ordersRepository.findById(orderId).orElse(null);
	}

	@Override
	public void deleteOrder(Long orderId) {
		ordersRepository.deleteById(orderId);

	}

	@Override
	public Order saveOrder(Order order, long customerId, int itemId) {
		// Check if customer exists
		Customer customer = customerservice.findUserById(customerId).orElse(null);
		if (customer == null) {
			throw new IllegalArgumentException("Invalid customer ID");
		}

		// Check if menu item exists
		Menu menu = menuservice.findByMenuId(itemId).orElse(null);
		if (menu == null) {
			throw new IllegalArgumentException("Invalid menu item ID");
		}

		order.setOrderDate(new Date()); // Set the current date as the order date
		order.setOrderTime(new Date()); // Set the current time as the order time
		// Calculate total price
		order.calculateTotalPrice();
		// Set initial order status
		order.setStatus("Not Paid");

		// Set customer and menu details
		order.setCustomer(customer);
		order.setMenu(menu);

		// Set total price in the entity to be saved in the database
		order.setTotalPrice(order.getTotalPrice());

		// Save the order
		return ordersRepository.save(order);
	}

	@Override
	public List<Order> findOrdersByCustomerId(long customerId) {
		return ordersRepository.findByCustomerCustomerId(customerId);
	}

}