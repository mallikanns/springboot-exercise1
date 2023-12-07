package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.jdbc.Expectations;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Book;
import com.example.demo.model.Product;

import ch.qos.logback.classic.Logger;

@RestController
public class ProductController {
	org.slf4j.Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	private static Map<Integer, Product> productRepo = new HashMap<>();
	   static {
	      Product honey = new Product( 1 , "Honey",2);
	      productRepo.put(honey.getId(), honey);
	      
	      Product almond = new Product(2, "Almond",3);
	      productRepo.put(almond.getId(), almond);
	   }
	   
	   @Autowired
	   private ProductRepository repo;
	   
//	   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	   @DeleteMapping("/products/{id}")
	   public ResponseEntity<Object> delete(@PathVariable("id") int id) { 
//	      productRepo.remove(id);
		   repo.deleteById(id);
	      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	   }
	   
//	   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	   @PutMapping("/products/{id}")
	   public Product updateProduct(@PathVariable("id") Integer id, @RequestBody  Product product) { 
	      Product oldProduct = productRepo.get(id);
		  oldProduct.setName(product.getName());
	      
	      return product;
	   }
	   
//	   @RequestMapping(value = "/products", method = RequestMethod.POST)
	   @PostMapping("/products")
	   public Product createProduct(@RequestBody Product product) {
//	      productRepo.put(product.getId(), product);
		   product.setId(0);
		   repo.save(product);
	      return product;
	   }
	   
	   
	   @GetMapping(value = "/products/{id}")
	   public Product getOProduct(@PathVariable Integer id) {
		   Product prod = productRepo.get(id);
		   
		   //get BookRestAPI
		   Book book = restTemplate.getForObject("http://localhost:8080/books"+prod.getBookid(), Book.class);
		   logger.warn("Book name is " + book.getName());
//		   if (prod == null) {
//			   throw new ProductNotFound(id);
//		   }
		   prod.getId();
		   
		   return prod;
	   }
	   
//	   @RequestMapping(value = "/products")
	   @GetMapping("/products")
	   public Collection<Product> getProduct() {
//		   repo.findAll();
	      return productRepo.values();
	   }
	   
//	   @GetMapping("/products")
//	   public Iterable<Product> getProduct() {
//		   return repo.findAll();
//	   }
}
