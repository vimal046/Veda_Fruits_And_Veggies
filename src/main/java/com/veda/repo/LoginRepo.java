package com.veda.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veda.entity.Product;

@Repository
public interface LoginRepo extends JpaRepository<Product, Integer>{

	@Procedure(name = "loginAuthendication")
	boolean loginAuthendication(@Param("UserName") String uName,@Param("Password") String pwd);
}
