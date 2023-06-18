package com.wileyedge.finalcourseproject.Dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

public interface IDao {
	public User save(User user);
	public void addActivity(CarbonConsumption activity);
	public User getUserByUsername(String uname);
	public User getUserByCredentials(Map<String, String> credentials);
	public void editUserbyUsername(String username, User newInfo);
	public List<CarbonConsumption> getAllCarbonConsumptionByDay(String username, Date date);
	public List<CarbonConsumption> getAllCarbonConsumptionDuringPeriod(String username, Date dateStart, Date dateEnd);
}
