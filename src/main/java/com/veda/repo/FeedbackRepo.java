package com.veda.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veda.entity.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

	@Procedure(name = "insertToFeedback")
	void insertToFeedback(@Param("name") String name, @Param("email") String email, @Param("review") String review);
	
	@Procedure(name = "fetchFeedback")
	List<Feedback> fetchFeedback();
}
