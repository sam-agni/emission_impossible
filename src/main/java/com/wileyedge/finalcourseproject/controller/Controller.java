package com.wileyedge.finalcourseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.finalcourseproject.Service.IService;
import com.wileyedge.finalcourseproject.model.User;

@RestController
public class Controller {

	@Autowired
	private IService service;
	
	@GetMapping(path = "/users")
	public List<User> fetchAllUsers(){
		System.out.println("Inside fetchAllUsers() of Controller");
		List<User> users = service.retrieveAllUsers();
		return users;		
	}
	
}
