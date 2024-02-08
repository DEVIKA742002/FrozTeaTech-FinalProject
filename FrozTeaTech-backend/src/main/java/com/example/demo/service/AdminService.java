package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Admin;
public interface AdminService {
	public List<Admin> findALL();
	public void updateAdmin(Admin admin);
	Admin findByUsername(String username);
	public void deleteById(long id);
}