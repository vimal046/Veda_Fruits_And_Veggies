package com.veda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.veda.entity.Product;
import com.veda.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService service;
    
	@PostMapping("/add/product")
	public ResponseEntity<String> addProduct(@RequestParam("name") String name, @RequestParam("price") int price,
			@RequestParam("description") String description, @RequestParam("imgLoc") MultipartFile imageFile) {

		try {
			service.inserToProduct(name, price, description, imageFile);
			return ResponseEntity.ok("Product added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product");
		}
	}
	
	@GetMapping("/fetchProduct")
	@ResponseBody
	public List<Product> fetchProductData() {
		return service.getProductData();
	}
	
	@DeleteMapping("/deleteProduct")
	public ResponseEntity<String> deleteProduct(@RequestParam("id") int id, @RequestParam("imgLoc") String imgLoc) {

		try {
			service.deleteProduct(id, imgLoc);
			return ResponseEntity.ok("Product deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
		}

	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product obj) {

		try {
			service.updateProduct(obj);
			return ResponseEntity.ok("Product deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
		}
	}

	@GetMapping("/products")
	public String products(Model model) {
		List<Product> productList = new ArrayList<>();
		productList=service.getProductData();
		model.addAttribute("products", productList);
		return "shop";
	}
}
