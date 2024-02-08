package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.*	;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_id", unique = true)
	    private Long orderId;

	    @ManyToOne(targetEntity = Customer.class, cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	    private Customer customer;

	    @Column(name = "order_date")
	    @Temporal(TemporalType.DATE)
	    private Date orderDate;

	    @Column(name = "order_time")
	    @Temporal(TemporalType.TIME)
	    private Date orderTime;

	    @ManyToOne(targetEntity = Menu.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
	    private Menu menu;

	    @Column(name = "quantity")
	    private int quantity;

	    @Column(name = "total_price")
	    private double totalPrice;

	    @NotBlank(message = "Order status cannot be blank")
	    @Column(name = "status")
	    private String status;



	public Order() {
	}

	public Order(long orderId, Customer customer, Date orderDate, Date orderTime, Menu menu, int quantity, String status) {
		this.orderId=orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.menu = menu;
		this.quantity = quantity;
		this.status = status;
		calculateTotalPrice(); 
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		calculateTotalPrice(); // Recalculate total price when menu item changes
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calculateTotalPrice(); // Recalculate total price when quantity changes
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	// Update the setter for totalPrice to also update the calculated total price
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Calculate total price based on menu item price and quantity
	public void calculateTotalPrice() {
		if (menu != null) {
			totalPrice = menu.getPrice() * quantity;
		} else {
			totalPrice = 0.0; 
		}
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", orderTime="
				+ orderTime + ", menu=" + menu + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", status="
				+ status + "]";
	}

}
