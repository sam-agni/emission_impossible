package com.wileyedge.finalcourseproject.Dao;

import java.sql.Date;
import java.util.List;

import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

public interface IDao {
	public int save(User user);
	public int addActivity(CarbonConsumption activity);
	public User getUserByUsername(String uname);
	public User getUserByCredentials(String uname, String password);
	public int editUserbyUsername(String username, User newInfo);
	public List<CarbonConsumption> getAllCarbonConsumptionByDay(String username, Date date);
	public List<CarbonConsumption> getAllCarbonConsumptionDuringPeriod(String username, Date dateStart, Date dateEnd);
}
