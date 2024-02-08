package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OderEntityTestCase {
	@InjectMocks
	OrderServiceImpl ordert;

	@Mock
	OrdersRepository oderdao;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	// OrderController
	@Test
	void testGetAllOrders() throws ParseException {

		Order order1 = new Order();
		order1.setOrderId(1L);
		Order order2 = new Order();
		order2.setOrderId(2L);

		List<Order> OrderList = new ArrayList<>();
		OrderList.add(order1);
		OrderList.add(order2);

		when(oderdao.findAll()).thenReturn(OrderList);

		List<Order> resultOrderList = ordert.getAllOrders();

		verify(oderdao, times(1)).findAll();
		assertEquals(2, resultOrderList.size());
		assertEquals(order1, resultOrderList.get(0));
		assertEquals(order2, resultOrderList.get(1));
	}

	@Test
	public void saveOrderTest() throws ParseException {
		Customer customer = new Customer(1, "atharv", "7218945407", "atharv1", "12345", "aj");
		Menu menu = new Menu(1, "tea", 10.0, "xyz");
		Order order = new Order(1, customer, new Date(), new Date(), menu, 2, "pending");

		when(oderdao.save(order)).thenReturn(order);

		Order savedOrder = ordert.saveOrder(order, 1, 1);

		assertNotNull(savedOrder);
		assertEquals("pending", savedOrder.getStatus());
		verify(oderdao, times(1)).save(order);
	}

	@Test
	public void findOrderByIdTest() throws ParseException {
		long id2 = 1;
		ordert.getOrderById(id2);
		verify(oderdao, times(1)).findById(id2);
	}

	@Test
	public void deleteOrderTest() throws ParseException {
		long orderId = 8L;

		ordert.deleteOrder(orderId);

		verify(oderdao, times(1)).deleteById(orderId);
	}
}