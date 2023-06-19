package com.wileyedge.finalcourseproject.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wileyedge.finalcourseproject.exceptions.EmissionInvalidException;
import com.wileyedge.finalcourseproject.exceptions.RegistrationFailedException;
import com.wileyedge.finalcourseproject.exceptions.UserNotFoundException;
import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

public interface IService {
	
	public String register(User user) throws RegistrationFailedException;
	public String login(Map<String, String> credentials) throws UserNotFoundException;
	public User getUserProfile(String username) throws UserNotFoundException;
	public void editUserProfile(String username, User newInfo) throws UserNotFoundException;
	public List<Map<String, String>> getActivitiesOnDay(String username, Date date) throws UserNotFoundException;
	public Map<String, String> getEmissionsDuringPeriod(String username, Date dateStart, Date dateEnd) throws UserNotFoundException;
	public void addNewEmissionsEntry(String username, CarbonConsumption entry) throws EmissionInvalidException;
	
	/*
	public List<User> retrieveAllUsers();
	public User save(User user);
	public CarbonConsumption addUserActivity(CarbonConsumption cc);
	*/
}
