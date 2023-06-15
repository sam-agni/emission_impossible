package com.wileyedge.finalcourseproject.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(path="/users")
	public User createUser(@Valid @RequestBody User user) {	
		System.out.println("Inside createUser");
		User u = service.save(user);
		return u;
	}
	
//	@PostMapping(path="/activities/{user_id}/{date}")
//	public User listOfActivities(@Valid @RequestBody User user, @RequestBody Date date) {	
//		System.out.println("Inside createUser");
//		User u = service.save(user);
//		return u;
//	}	
	
}
