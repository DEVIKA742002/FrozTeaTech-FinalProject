package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MenuRepository;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Menu;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository dao;

	@Override
	public List<Menu> findAll() {

		return dao.findAll();
	}

	@Override
	public Optional<Menu> findByMenuId(int id) {
		return this.dao.findById(id);
	}

	@Override
	public List<Menu> findByFname(String fname) {
		return dao.findByFnameIgnoreCase(fname);
	}

	@Override
	public Menu addItem(Menu menu) {
		menu.setImg("assets/images/"+menu.getImg());
		return dao.save(menu);
	}

	@Override
	public void deleteByItem(int id) {
		dao.deleteById(id);
		
	}

	@Override
	public void updateMenu(Menu menu) {
		this.dao.save(menu);
		
	}


}
