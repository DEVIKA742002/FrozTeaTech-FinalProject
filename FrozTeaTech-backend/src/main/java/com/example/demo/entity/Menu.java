package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Menu_Table")
public class Menu {
	@Id
	@Column(name = "item_id")
	@Min(value = 1, message = "Item ID must be greater than or equal to 1")
	@Digits(integer = 10, fraction = 0, message = "Item ID should be a number")
	private int item_id;
	@NotBlank(message = "Item name cannot be blank")
	@Column(name = "fname")
	private String fname;

	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be a positive value")
	private Double price;

	@NotBlank(message = "Image URL cannot be blank")
	@Column(name = "img")
	private String img;

	public Menu() {

	}

	public Menu(int item_id, String fname, Double price, String img) {
		super();
		this.item_id = item_id;
		this.fname = fname;
		this.price = price;
		this.img = img;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setId(int item_id) {
		this.item_id = item_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Double getPrice() {
		return price != null ? price : 0.0;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Menu [item_id=" + item_id + ", fname=" + fname + ", price=" + price + ", img=" + img + "]";
	}

}
