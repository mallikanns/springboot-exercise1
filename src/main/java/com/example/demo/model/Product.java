package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	   private int id;
	   private String name;
	   private int bookid;
	}


//@Entity
//public class Product {
//	@Id
//	@GeneratedValue
//	private int id;
//	private String name;
//	
//	public Product() {
//		super();
//	}
//	
//	public Product(int id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//}