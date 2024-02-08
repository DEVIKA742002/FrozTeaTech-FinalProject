package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService service;
    @Autowired
    CustomerRepository dao;
    @Autowired
    EmailService emailservice;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> singup(@RequestBody Customer customer) {
        System.out.println("Received a signup request for customer: " + customer.getUsername());

        this.service.addCustomer(customer);

        System.out.println("Customer registered: " + customer);
        
        emailservice.sendEmail(customer.getEmail(), "SignUp Email", "SignUP is Successful in Restaurant Management System as a Customer.\nYour username is: " + customer.getUsername());

        Map<String, String> response = new HashMap<String, String>();
        response.put("status", "success");
        response.put("message", "User registered!!");
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/viewcustomer")
    public ResponseEntity<List<Customer>> findAll() {
        System.out.println("Received a request to get all customer data.");

        List<Customer> customers = this.service.findALL();

        System.out.println("Returning all customer data:");
        for (Customer customer : customers) {
            System.out.println(customer); 
        }

        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        System.out.println("Received a request to get customer by ID: " + id);

        Optional<Customer> customerOptional = dao.findById(id);

        if (customerOptional.isPresent()) {
            System.out.println("Returning customer for ID: " + id);
            return ResponseEntity.ok(customerOptional.get());
        } else {
            System.out.println("Customer not found for ID: " + id);
            throw new CustomerNotFoundException("Customer with Id " + id + " not found.");
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
        System.out.println("Received a request to get customer by username: " + username);

        Customer customer = service.getCustomerByUsername(username);
        if (customer == null) {
            System.out.println("Customer not found for username: " + username);
            throw new CustomerNotFoundException("Customer with username " + username + " not found.");
        }

        System.out.println("Returning customer for username: " + customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Customer e) {
        System.out.println("Received a request to update customer data: " + e);

        try {
            if (this.dao.findById(e.getCustomerId()).isPresent()) {
                Customer existingCus = this.dao.findById(e.getCustomerId()).get();
                existingCus.setCustomerName(e.getCustomerName());
                existingCus.setCustomerPhone(e.getCustomerPhone());
                existingCus.setUsername(e.getUsername());
                existingCus.setUserpassword(e.getUserpassword());
                existingCus.setEmail(e.getEmail());
                this.dao.save(existingCus);

                Map<String, String> response = new HashMap<String, String>();
                response.put("status", "success");
                response.put("message", "User data updated!!");
                System.out.println("Customer data updated successfully: " + existingCus);
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
            } else {
                Map<String, String> response = new HashMap<String, String>();
                response.put("status", "failed");
                response.put("message", "User data not found!!");
                System.out.println("Customer data not found for updating: " + e.getCustomerId());
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e1) {
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "failed");
            response.put("message", "User data not updated!!");
            System.out.println("Failed to update customer data: " + e1.getMessage());
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(name = "id") int id) {
        System.out.println("Received a request to delete customer data by ID: " + id);

        try {
            this.service.deleteById(id);
            System.out.println("Customer data deleted successfully for ID: " + id);
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "success");
            response.put("message", "User data deleted!!");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Failed to delete customer data for ID: " + id);
            Map<String, String> response = new HashMap<String, String>();
            response.put("status", "failed");
            response.put("message", "Customer data not deleted!!");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loginn")
    public ResponseEntity<Map<String, String>> login(@RequestParam("username") String username,
            @RequestParam("userpassword") String password) {
        System.out.println("Received a login request for customer: " + username);

        Optional<Customer> existingCustomer = this.service.getUserByName(username);
        Map<String, String> response = new HashMap<String, String>();
        if (existingCustomer.isPresent()) {
            if (existingCustomer.get().getUserpassword().equals(password)) {
                System.out.println("Customer login successful for: " + username);

                response.put("status", "success");
                response.put("message", "User authenticated");
                response.put("CustomerId", String.valueOf(existingCustomer.get().getCustomerId()));
                response.put("CustomerName", existingCustomer.get().getCustomerName());
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
            } else {
                System.out.println("Customer login failed due to incorrect password for: " + username);

                response.put("status", "Failed");
                response.put("message", "User password incorrect");
                return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
            }
        } else {
            System.out.println("Customer login failed because user does not exist for: " + username);

            response.put("status", "Failed");
            response.put("message", "User does not exist");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customerData) {
        System.out.println("Received a login request for customer: " + customerData.getUsername());

        Customer customer = service.findByUsername(customerData.getUsername());

        if (customer.getUserpassword().equals(customerData.getUserpassword())) {
            System.out.println("Customer login successful: " + customer);

            Customer sendcustomer = new Customer();
            sendcustomer.setCustomerId(customer.getCustomerId());
            sendcustomer.setCustomerName(customer.getCustomerName());
            sendcustomer.setCustomerPhone(customer.getCustomerPhone());
            sendcustomer.setUsername(customer.getUsername());
            sendcustomer.setEmail(customer.getEmail());

            return ResponseEntity.ok(sendcustomer);
        } else {
            System.out.println("Customer login failed for: " + customerData.getUsername());
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }
}
