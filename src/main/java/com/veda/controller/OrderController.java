package com.veda.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.veda.entity.FormObject;
import com.veda.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping("placeOrder")
	public ResponseEntity<?> insertOrder(@RequestBody FormObject formObj) {
		try {
			service.insertForm(formObj.getFormObj(), formObj.getItemObj());

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/orders")
	@ResponseBody
	public List<FormObject> getFormObject(){
		 List<FormObject> formList=	service.getFormObject();
		return formList;
	}
}
