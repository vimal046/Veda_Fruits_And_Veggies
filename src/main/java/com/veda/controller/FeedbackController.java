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

import com.veda.entity.Feedback;
import com.veda.service.FeedbackService;

@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService service;

	@PostMapping("/feedback")
	public ResponseEntity<?> insertFeedback(@RequestBody Feedback obj) {

		try {
			service.insertFeedback(obj);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("feedbackData")
	@ResponseBody
	public List<Feedback> getFeedbackData(){
		return service.getFeedbackdata();
	}
}
