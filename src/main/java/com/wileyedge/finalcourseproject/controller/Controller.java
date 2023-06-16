package com.wileyedge.finalcourseproject.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.finalcourseproject.Service.IService;
import com.wileyedge.finalcourseproject.model.User;

@RestController
public class Controller {

	@Autowired
	private IService service;
	
	@PostMapping(path = "/register", consumes = "application/json")
	public Integer register(@RequestBody Map<String, String> userInfo) {
		return service.register(userInfo);
	}
	
	@PostMapping(path = "/login", consumes = "applicaition/json")
	public Integer login(@RequestBody Map<String, String> credentials) {
		return service.login(credentials);
	}
	
	@GetMapping(path = "/profile/{user_id}")
	public Map<String, String> getUserProfile(@PathVariable Integer userId) {
		return service.getUserProfile(userId);
	}
	
	@PostMapping(path = "/profile/edit/{user_id}", consumes = "application/json")
	public void editUserProfile(@PathVariable Integer userId, @RequestBody Map<String, String> newInfo) {
		service.editUserProfile(userId, newInfo);
	}
	
	@GetMapping(path = "/activities/{user_id}/{date}")
	public List<Map<String, String>> getActivitiesOnDay(@PathVariable Integer userId, @PathVariable Date date) {
		return service.getActivitiesOnDay(userId, date);
	}
	
	@GetMapping(path = "/emissions/{user_id}/{date_start}/{date_end}")
	public Map<String, String> getEmissionsDuringPeriod(@PathVariable Integer userId, 
			@PathVariable Date dateStart, @PathVariable Date dateEnd) {
		return service.getEmissionsDuringPeriod(userId, dateStart, dateEnd);
	}
	
	@PostMapping(path = "/emissions/{user_id}", consumes = "application/json")
	public void addNewEmissionsEntry(@PathVariable Integer userId, @RequestBody Map<String, String> entry) {
		service.addNewEmissionsEntry(userId, entry);
	}
	
	/*
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
	
	@PostMapping(path="/activities/{user_id}/{date}")
	public User listOfActivities(@Valid @RequestBody User user, @RequestBody Date date) {	
		System.out.println("Inside createUser");
		User u = service.save(user);
		return u;
	}	
	*/
	
}
