package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MenuRepository;
import com.example.demo.entity.Menu;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.MenuService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/restaurant")
public class MenuController {
    @Autowired
    MenuService service;
    @Autowired
    MenuRepository dao;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> findAll() {
        System.out.println("Received a request to get all menu items.");

        List<Menu> menuItems = this.service.findAll();

        System.out.println("Returning all menu items: " + menuItems);

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/menu/{item_id}")
    public ResponseEntity<Menu> getOrderById(@PathVariable int item_id) {
        System.out.println("Received a request to get menu item by ID: " + item_id);

        Optional<Menu> menu = service.findByMenuId(item_id);

        if (menu.isPresent()) {
            System.out.println("Returning menu item: " + menu.get());
            return new ResponseEntity<>(menu.get(), HttpStatus.OK);
        } else {
            System.out.println("Menu item not found for ID: " + item_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/menu/fname/{fname}")
    public ResponseEntity<List<Menu>> getMenuByFname(@PathVariable String fname) {
        System.out.println("Received a request to get menu items by fname: " + fname);

        List<Menu> menuItems = service.findByFname(fname);

        if (menuItems.isEmpty()) {
            System.out.println("No menu items found for fname: " + fname);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Returning menu items by fname: " + menuItems);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> addItem(@RequestBody Menu menu) {
        System.out.println("Received a request to add a menu item: " + menu);

        try {
            Menu addedMenu = service.addItem(menu);
            System.out.println("Menu item added successfully: " + addedMenu);
            return new ResponseEntity<>(addedMenu, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println("Failed to add menu item: " + ex.getMessage());
            throw new BadRequestException("Invalid data for adding menu item.");
        }
    }

    @PutMapping("/menu/update")
    public ResponseEntity<Map<String, String>> updateMenu(@RequestBody Menu e) {
        System.out.println("Received a request to update menu item: " + e);

        try {
            if (this.dao.findById(e.getItem_id()).isPresent()) {
                Menu existingMenu = this.dao.findById(e.getItem_id()).get();
                existingMenu.setFname(e.getFname());
                existingMenu.setPrice(e.getPrice());
                existingMenu.setImg(e.getImg());
                this.dao.save(existingMenu);

                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Menu item updated!!");
                System.out.println("Menu item updated successfully: " + existingMenu);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("status", "failed");
                response.put("message", "Menu item not found!!");
                System.out.println("Menu item not found for updating: " + e.getItem_id());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e1) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "failed");
            response.put("message", "Menu item not updated!!");
            System.out.println("Failed to update menu item: " + e1.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/menu/{item_id}")
    public ResponseEntity<Void> deleteByItem(@PathVariable int item_id) {
        System.out.println("Received a request to delete menu item by ID: " + item_id);

        try {
            service.deleteByItem(item_id);
            System.out.println("Menu item deleted successfully for ID: " + item_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("Menu item not found for deletion, ID: " + item_id);
            throw new NotFoundException("Menu item with ID " + item_id + " not found.");
        }
    }
}
