package com.veda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.veda.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService service;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<String> adminLoginAuthendication(@RequestParam("uName") String uName, @RequestParam("pwd") String pwd,HttpServletRequest request) {
		boolean result=service.loginAuthendication(uName,pwd);
		
		
		if(result) {
			HttpSession session=request.getSession(true);
			session.setAttribute("username", uName);
			return ResponseEntity.ok().body("admin.html");
		}else {
			return ResponseEntity.status(HttpStatus.FOUND).header("Location","/error.html").body(null);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		
		return ResponseEntity.ok().body("index.html");
	}
}
