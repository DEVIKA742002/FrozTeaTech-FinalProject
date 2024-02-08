package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
public interface PaymentService {
	Payment addPayment(Payment payment,long orderId,long customerId);
	List<Payment> getAllPayments();
	public Optional<Payment> findPaymentById(long id);
	void deletePayment(long paymentId);
//	public Optional<Payment> findBycustomerId(long customerId);
//	public List<Payment> findAllByCustomerId(long customerId);
	 List<Payment> getPaymentsByOrderId(long orderId);
	 public void deletePaymentsByOrderId(long orderId);
	 public List<Payment> findPaymentsByCustomerId(long customerId);
}
