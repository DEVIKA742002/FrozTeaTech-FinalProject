package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "user_name")})
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;

    @NotBlank(message = "Customer name cannot be blank")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    @Column(name = "customer_name")
    private String customerName;

    @NotBlank(message = "Customer phone cannot be blank")
    @Pattern(regexp = "[0-9]+", message = "Customer phone must contain only digits")
    @Size(min = 10, max = 12, message = "Customer phone must be between 10 and 12 digits")
    @Column(name = "customer_phone")
    private String customerPhone;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Column(name = "user_name")
    private String username;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 6, message = "User password must be at least 6 characters")
    @Column(name = "user_password")
    private String userpassword;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

	public Customer() {
	}

	public Customer(long customerId, String customerName, String customerPhone, String username, String userpassword , String email) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.username = username;
		this.userpassword = userpassword;
		this.email= email;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", username=" + username + ", userpassword=" + userpassword + ", email=" + email
				+ "]";
	}
	
	
	
}
