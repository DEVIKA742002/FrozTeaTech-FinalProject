package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.MenuRepository;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MenuTestCase {
	@InjectMocks
	MenuServiceImpl menut;
	@Mock
	MenuRepository dao;

	@Before(value = "")
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	// Menu Controller
	
	
	//Find All Menu
	@Test
	public void findAllTest() throws ParseException {
		List<Menu> list = new ArrayList<Menu>();
		Menu itemOne = new Menu(3, "poha", 20.00, "xyz");
		Menu itemSecond = new Menu(4, "roti", 10.00, "xyz");
		Menu itemThird = new Menu(5, "rice", 50.00, "xyz");

		list.add(itemOne);
		list.add(itemSecond);
		list.add(itemThird);
		when(dao.findAll()).thenReturn(list);
		List<Menu> menuList = dao.findAll();
		assertEquals(3, menuList.size());
		verify(dao, times(1)).findAll();

	}

	@Test
	public void addItemTest() throws ParseException {
		Menu Aitem = new Menu(3, "poha", 20.00, "xyz");

		menut.addItem(Aitem);
		verify(dao, times(1)).save(Aitem);
	}

	@Test
	public void deleteByIdTest() throws ParseException {
		int id = 1;
		menut.deleteByItem(id);
		verify(dao, times(1)).deleteById(id);
	}

	@Test
	public void findByFnameTest() throws ParseException {
		String Fname1 = "tea";
		menut.findByFname(Fname1);
		verify(dao, times(1)).findByFnameIgnoreCase(Fname1);
	}

	@Test
	public void findByIdTest() throws ParseException {
		int id1 = 1;
		menut.findByMenuId(id1);
		verify(dao, times(1)).findById(id1);
	}

	@Test
	public void updateMenuTest() throws ParseException {
		Menu menuu = new Menu(1, "xyz", 90.0, "xyz1");
		menut.updateMenu(menuu);
		verify(dao, times(1)).save(menuu);
	}

}
