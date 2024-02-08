package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Menu;

public interface MenuService {
	public List<Menu> findAll();
	public Optional<Menu> findByMenuId(int id);
	List<Menu> findByFname(String fname);
	public Menu addItem(Menu menu);
	public void deleteByItem(int id);
	public void updateMenu(Menu menu);
}
