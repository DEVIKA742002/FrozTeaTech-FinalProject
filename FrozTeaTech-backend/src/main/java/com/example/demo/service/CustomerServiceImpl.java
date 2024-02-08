package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import com.example.demo.exception.UsernameAlreadyExistsException;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository dao;

	@Override
	public void addCustomer(Customer customer) {
		if (dao.existsByUsername(customer.getUsername())) {
            throw new UsernameAlreadyExistsException(customer.getUsername());
        }
		else {this.dao.save(customer);}
		

	}

	@Override
	public List<Customer> findALL() {
		return dao.findAll();
	}

	@Override
	public void updateUser(Customer customer) {
		this.dao.save(customer);

	}


	@Override
	public Customer findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public Optional<Customer> getUserByName(String username) {
		return this.dao.findByusername(username);
	}

	@Override
	public Optional<Customer> findUserById(long id) {
		return this.dao.findById(id);
		
	}

	@Override
	public void deleteById(long id) {
		dao.deleteById(id);
		
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return dao.findByUsername(username);
	}

	

}
