package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminRepository;
import com.example.demo.entity.Admin;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository dao;
	@Override
	public List<Admin> findALL() {
		return dao.findAll();
	}

	@Override
	public void updateAdmin(Admin admin) {
		this.dao.save(admin);
		
	}

	@Override
	public Admin findByUsername(String username) {
		return dao.findAdminByUsername(username);
	}

	@Override
	public void deleteById(long id) {
		dao.deleteById(id);		
	}

}
