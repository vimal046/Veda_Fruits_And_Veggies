package com.veda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veda.entity.Feedback;
import com.veda.repo.FeedbackRepo;

import jakarta.transaction.Transactional;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepo repo;

	public void insertFeedback(Feedback obj) {

		String name = obj.getName();
		String email = obj.getEmail();
		String review = obj.getReview();

		repo.insertToFeedback(name, email, review);

	}

	@Transactional
	public List<Feedback> getFeedbackdata() {

		return repo.fetchFeedback();
	}
}
