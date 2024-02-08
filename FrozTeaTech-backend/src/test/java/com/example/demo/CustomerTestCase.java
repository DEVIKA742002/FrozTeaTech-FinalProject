package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerTestCase {
	@InjectMocks
	CustomerServiceImpl customert;

	@Mock
	CustomerRepository customerdao;
	
	//Add Customer 
	
	@Test
	public void addCustomerTest() throws ParseException {
		Customer Cust = new Customer(3, "xyz", "9111658945", "xyz1", "12345", "aj");
		customert.addCustomer(Cust);
		verify(customerdao, times(1)).save(Cust);
	}

	//Find all customer 
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
	//Update user or customer 
	@Test
	public void updateUserTest() throws ParseException {
		Customer CustUpdate = new Customer(1, "xyz", "9111658945", "xyz1", "12345", "aj");
		customert.updateUser(CustUpdate);
		verify(customerdao, times(1)).save(CustUpdate);
	}
	
	//delete customer by customerID 
	@Test
	public void deleteByCustomerIdTest() throws ParseException {
		long cusid = 1;
		customert.deleteById(cusid);
		verify(customerdao, times(1)).deleteById(cusid);
	}
	
	//find customer by user name 
	@Test
	public void findUserByUsernameTest() throws ParseException {
		String uname = "atharv1";
		customert.findByUsername(uname);
		verify(customerdao, times(1)).findByUsername(uname);
	}
	
	//Find customer by ID 
	@Test
	public void findUserByIdTest() throws ParseException {
		long id1 = 1;
		customert.findUserById(id1);
		verify(customerdao, times(1)).findById(id1);
	}
	

}
