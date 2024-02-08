package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminRepository;
import com.example.demo.entity.Admin;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;
    @Autowired
    AdminRepository dao;

    @GetMapping("/username/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
        System.out.println("Received a request to get admin by username: " + username);

        Admin admin = service.findByUsername(username);
        if (admin == null) {
            System.out.println("Admin not found for username: " + username);
            throw new CustomerNotFoundException("Customer with username " + username + " not found.");
        }

        System.out.println("Returning admin for username: " + admin);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(name = "id") int id) {
        System.out.println("Received a request to delete admin by ID: " + id);

        try {
            this.service.deleteById(id);
            System.out.println("Admin data deleted successfully for ID: " + id);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Admin data deleted!!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Failed to delete admin data for ID: " + id);
            Map<String, String> response = new HashMap<>();
            response.put("status", "failed");
            response.put("message", "Admin data not deleted!!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Admin adminData) {
        System.out.println("Received a login request for admin: " + adminData.getusername());

        Admin admin = service.findByUsername(adminData.getusername());

        if (admin.getAdminpassword().equals(adminData.getAdminpassword())) {
            System.out.println("Admin login successful: " + admin);

            Admin sendadmin = new Admin();
            sendadmin.setAdminId(admin.getAdminId());
            sendadmin.setAdminName(admin.getAdminName());
            sendadmin.setAdminPhone(admin.getAdminPhone());
            sendadmin.setusername(admin.getusername());

            return ResponseEntity.ok(sendadmin);
        } else {
            System.out.println("Admin login failed for: " + adminData.getusername());
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }

    @GetMapping("/viewadmin")
    public ResponseEntity<List<Admin>> findAll() {
        System.out.println("Received a request to get all admin data.");

        List<Admin> admins = this.service.findALL();

        System.out.println("Returning all admin data: " + admins);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
