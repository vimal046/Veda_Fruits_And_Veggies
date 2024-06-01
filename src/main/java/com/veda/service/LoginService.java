package com.veda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veda.repo.LoginRepo;

@Service
public class LoginService {

	@Autowired
	private LoginRepo repo;

	public boolean loginAuthendication(String uName, String pwd) {
		boolean result = repo.loginAuthendication(uName, pwd);
		return result;
	}
}
