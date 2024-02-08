package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;

public interface CustomerService {
	public void addCustomer(Customer customer);

	public List<Customer> findALL();

	public void updateUser(Customer customer);

	public void deleteById(long id);

	Customer findByUsername(String username);
	Customer getCustomerByUsername(String username);

	public Optional<Customer> getUserByName(String username);
	
	public Optional<Customer> findUserById(long id);

}
