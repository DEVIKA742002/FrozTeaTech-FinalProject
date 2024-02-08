package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
		public List<Payment> findByOrderId(long orderId); //Admin
		List<Payment> findByCustomerCustomerId(long customerId);
		
}