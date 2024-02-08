package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.service.CustomerService;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.dao.PaymentRepository;
@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private CustomerService customerService;
	
	public PaymentServiceImpl(PaymentRepository paymentRepository,
			CustomerService customerService) {
		super();
		this.paymentRepository = paymentRepository;
		
		this.customerService = customerService;
	}
	@Override
	public Payment addPayment(Payment payment, long orderId, long customerId) {
		Order order = orderRepository.getById(orderId);
		payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setStatus("Paid");
		} else {

			order.setStatus("Not Paid");
		}
		Customer customer = customerService.findUserById(customerId).orElse(null);
		payment.setCustomer(customer);
    	return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}


	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId);		
		if (paymentRepository.existsById(paymentId)) {
		    paymentRepository.deleteById(paymentId);
		}
	}


	@Override
	public Optional<Payment> findPaymentById(long id) {
		return this.paymentRepository.findById(id);
	}
	@Override
	public List<Payment> getPaymentsByOrderId(long orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
	@Override
	public void deletePaymentsByOrderId(long orderId) {
		paymentRepository.findByOrderId(orderId).forEach(payment -> paymentRepository.delete(payment));
		
	}
//	@Override
//	public Optional<Payment> findBycustomerId(long customerId) {
//		return this.paymentRepository.findBycustomerId(customerId);
//	}
//	@Override
//	public List<Payment> findAllByCustomerId(long customerId) {
//		return paymentRepository.findByOrderId(customerId);
//	}
	@Override
	public List<Payment> findPaymentsByCustomerId(long customerId) {
		return paymentRepository.findByCustomerCustomerId(customerId);
	}

}
