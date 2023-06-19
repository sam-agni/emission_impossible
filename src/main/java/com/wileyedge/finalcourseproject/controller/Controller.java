package com.wileyedge.finalcourseproject.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wileyedge.finalcourseproject.Service.IService;
import com.wileyedge.finalcourseproject.exceptions.EmissionInvalidException;
import com.wileyedge.finalcourseproject.exceptions.RegistrationFailedException;
import com.wileyedge.finalcourseproject.exceptions.UserNotFoundException;
import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

@RestController
public class Controller {

	@Autowired
	private IService service;
	
	@PostMapping(path = "/register", consumes = "application/json")
	public String register(@RequestBody User user) throws RegistrationFailedException {
		return service.register(user);
	}
	
	@PostMapping(path = "/login", consumes = "applicaition/json")
	public String login(@RequestBody Map<String, String> credentials) throws UserNotFoundException {
		return service.login(credentials);
	}
	
	@GetMapping(path = "/profile/{username}")
	public User getUserProfile(@PathVariable String username) throws UserNotFoundException {
		return service.getUserProfile(username);
	}
	
	@PostMapping(path = "/profile/edit/{username}", consumes = "application/json")
	public void editUserProfile(@PathVariable String username, @RequestBody User newInfo) throws UserNotFoundException {
		service.editUserProfile(username, newInfo);
	}
	
	@GetMapping(path = "/activities/{username}/{date}")
	public List<Map<String, String>> getActivitiesOnDay(@PathVariable String username, @PathVariable Date date) throws UserNotFoundException {
		return service.getActivitiesOnDay(username, date);
	}
	
	@GetMapping(path = "/emissions/{username}/{date_start}/{date_end}")
	public Map<String, String> getEmissionsDuringPeriod(@PathVariable String username, 
			@PathVariable Date dateStart, @PathVariable Date dateEnd) throws UserNotFoundException {
		return service.getEmissionsDuringPeriod(username, dateStart, dateEnd);
	}
	
	@PostMapping(path = "/emissions/{username}", consumes = "application/json")
	public void addNewEmissionsEntry(@PathVariable String username, @RequestBody CarbonConsumption entry) throws EmissionInvalidException {
		service.addNewEmissionsEntry(username, entry);
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
