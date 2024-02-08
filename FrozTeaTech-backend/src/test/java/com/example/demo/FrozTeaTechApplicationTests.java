package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.MenuRepository;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.service.CustomerServiceImpl;
import com.example.demo.service.MenuServiceImpl;
import com.example.demo.service.OrderServiceImpl;
import com.example.demo.service.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FrozTeaTechApplicationTests {

	@InjectMocks
	MenuServiceImpl menut;
	@InjectMocks
	CustomerServiceImpl customert;
	@InjectMocks
	OrderServiceImpl ordert;
	@InjectMocks
	PaymentServiceImpl payt;

	@Mock
	MenuRepository dao;
	@Mock
	CustomerRepository customerdao;
	@Mock
	OrdersRepository oderdao;
	@Mock
	PaymentRepository paydao;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

//Menu Controller
	@Test
	public void findAllTest() throws ParseException {
		List<Menu> list = new ArrayList<Menu>();
		Menu itemOne = new Menu(3, "poha", 20.00, "xyz");
		Menu itemSecond = new Menu(4, "roti", 10.00, "xyz");
		Menu itemThird = new Menu(5, "rice", 50.00, "xyz");

		list.add(itemOne);
		list.add(itemSecond);
		list.add(itemThird);
		when(dao.findAll()).thenReturn(list);
		List<Menu> menuList = dao.findAll();
		assertEquals(3, menuList.size());
		verify(dao, times(1)).findAll();

	}

	@Test
	public void addItemTest() throws ParseException {
		Menu Aitem = new Menu(3, "poha", 20.00, "xyz");

		menut.addItem(Aitem);
		verify(dao, times(1)).save(Aitem);
	}

	@Test
	public void deleteByIdTest() throws ParseException {
		int id = 1;
		menut.deleteByItem(id);
		verify(dao, times(1)).deleteById(id);
	}

	@Test
	public void findByFnameTest() throws ParseException {
		String Fname1 = "tea";
		menut.findByFname(Fname1);
		verify(dao, times(1)).findByFnameIgnoreCase(Fname1);
	}

	@Test
	public void findByIdTest() throws ParseException {
		int id1 = 1;
		menut.findByMenuId(id1);
		verify(dao, times(1)).findById(id1);
	}

	@Test
	public void updateMenuTest() throws ParseException {
		Menu menuu = new Menu(1, "xyz", 90.0, "xyz1");
		menut.updateMenu(menuu);
		verify(dao, times(1)).save(menuu);
	}

//CustomerController
	@Test
	public void findAllCustomerTest() throws ParseException {
		List<Customer> list1 = new ArrayList<Customer>();
		Customer cOne = new Customer(3, "xyz", "9111658945", "xyz1", "12345", "aj");
		Customer cSecond = new Customer(4, "abc", "9176589546", "abc1", "12345", "aj");
		Customer cThird = new Customer(5, "lkj", "9358754689", "lkj1", "12345", "aj");

		list1.add(cOne);
		list1.add(cSecond);
		list1.add(cThird);
		when(customerdao.findAll()).thenReturn(list1);
		List<Customer> customerList = customerdao.findAll();
		assertEquals(3, customerList.size());
		verify(customerdao, times(1)).findAll();

	}

	@Test
	public void findUserByIdTest() throws ParseException {
		long id1 = 1;
		customert.findUserById(id1);
		verify(customerdao, times(1)).findById(id1);
	}

	@Test
	public void findUserByUsernameTest() throws ParseException {
		String uname = "atharv1";
		customert.findByUsername(uname);
		verify(customerdao, times(1)).findByUsername(uname);
	}

	@Test
	public void addCustomerTest() throws ParseException {
		Customer Cust = new Customer(3, "xyz", "9111658945", "xyz1", "12345", "aj");
		customert.addCustomer(Cust);
		verify(customerdao, times(1)).save(Cust);
	}

	@Test
	public void deleteByCustomerIdTest() throws ParseException {
		long cusid = 1;
		customert.deleteById(cusid);
		verify(customerdao, times(1)).deleteById(cusid);
	}

	@Test
	public void updateUserTest() throws ParseException {
		Customer CustUpdate = new Customer(1, "xyz", "9111658945", "xyz1", "12345", "aj");
		customert.updateUser(CustUpdate);
		verify(customerdao, times(1)).save(CustUpdate);
	}

////OrderController
//	@Test
//	void testGetAllOrders() throws ParseException {
//
//		Order order1 = new Order();
//		order1.setOrderId(1L);
//		Order order2 = new Order();
//		order2.setOrderId(2L);
//
//		List<Order> OrderList = new ArrayList<>();
//		OrderList.add(order1);
//		OrderList.add(order2);
//
//		when(oderdao.findAll()).thenReturn(OrderList);
//
//		List<Order> resultOrderList = ordert.getAllOrders();
//
//		verify(oderdao, times(1)).findAll();
//		assertEquals(2, resultOrderList.size());
//		assertEquals(order1, resultOrderList.get(0));
//		assertEquals(order2, resultOrderList.get(1));
//	}
//
//	@Test
//	public void saveOrderTest() throws ParseException {
//		Customer customer = new Customer(1, "atharv", "7218945407", "atharv1", "12345", "aj");
//		Menu menu = new Menu(1, "tea", 10.0, "xyz");
//		Order order = new Order(1, customer, new Date(), new Date(), menu, 2, "pending");
//
//		when(oderdao.save(order)).thenReturn(order);
//
//		Order savedOrder = ordert.saveOrder(order, 0, 0);
//
//		assertNotNull(savedOrder);
//		assertEquals("pending", savedOrder.getStatus());
//		verify(oderdao, times(1)).save(order);
//	}
//
//	@Test
//	public void findOrderByIdTest() throws ParseException {
//		long id2 = 1;
//		ordert.getOrderById(id2);
//		verify(oderdao, times(1)).findById(id2);
//	}
//
//	@Test
//	public void deleteOrderTest() throws ParseException {
//		long orderId = 8L;
//
//		ordert.deleteOrder(orderId);
//
//		verify(oderdao, times(1)).deleteById(orderId);
//	}
//PaymentController

//	@Test
//	public void addPaymentTest() throws ParseException {
//		Order order = new Order();
//		order.setTotalPrice(100.0);
//
//		Customer customer = new Customer();
//
//		Optional<Order> optionalOrder = Optional.of(order);
//
//		when(oderdao.findById(anyLong())).thenReturn(optionalOrder);
//		when(customert.findUserById(anyLong())).thenReturn(Optional.of(customer));
//
//		Payment payment = new Payment();
//		Payment addedPayment = payt.addPayment(payment, 1L, 1L);
//
//		assertNotNull(addedPayment);
//		assertEquals("PAID", order.getStatus());
//		verify(paydao, times(1)).save(payment);
//	}

	@Test
	public void getAllPaymentsTest() throws ParseException {
		List<Payment> payments = new ArrayList<>();
		payments.add(new Payment());
		payments.add(new Payment());

		when(payt.getAllPayments()).thenReturn(payments);

		List<Payment> result = payt.getAllPayments();

		assertEquals(2, result.size());
		verify(paydao, times(1)).findAll();
	}

	@Test
	public void deletePaymentTest() throws ParseException {
		long paymentId = 1L;
		when(paydao.existsById(paymentId)).thenReturn(true);
		doNothing().when(paydao).deleteById(paymentId);

		payt.deletePayment(paymentId);

		verify(paydao, times(1)).deleteById(paymentId);
	}

//	@Test
//	public void getAllPaymentsByCustomerIdTest() throws ParseException {
//		List<Payment> payments = new ArrayList<>();
//		payments.add(new Payment());
//		payments.add(new Payment());
//
//		when(paydao.findByOrderId(anyLong())).thenReturn(payments);
//
//		List<Payment> result = payt.findPaymentsByCustomerId(1L);
//
//		assertEquals(2, result.size());
//		verify(paydao, times(1)).findByOrderId(1L);
//	}
}
