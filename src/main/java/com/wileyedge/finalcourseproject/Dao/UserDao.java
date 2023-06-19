package com.wileyedge.finalcourseproject.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

@Repository
public class UserDao implements IDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
//	private static List<User> users = new ArrayList<User>();
//	static {
//		users.add(new User("jack_02","jack@gmail.com",88997765, new Date(), "Rhodes Ave, New York City - 100", "secret"));
//	}
	
	public UserDao() {
		System.out.println("Object of UserDao created");
	}
	
	// save a new user 
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int save(User user) {	
		String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?)";
		return template.update(sql, 
					user.getUsername(),
					user.getAddress(),
					user.getDob(),
					user.getEmail(),
					user.getMobile(),
					user.getPassword(),				
					user.getUserid());
	}

	@Override
	// Given a user add an activity
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addActivity(CarbonConsumption activity) {
		String sql = "INSERT INTO Carbon_Consumption VALUES (?, ?, ?, ?, ?, ?)";
		return template.update(sql, 
					activity.getCo2EmissionID(),
					activity.getCo2Emission(),
					activity.getDate(),
					activity.getKmDriven(),
					activity.getTravelType().getValue(),
					activity.getUsername());
					
	}
	
	// Get user by userid
	public User getUserById(int uId) {
		String sql = "SELECT * FROM User WHERE id=?";
		return template.queryForObject(sql, new UserRowMapper(), uId);	
	}
	
	//getActivitiesOnDay
	public List<CarbonConsumption> getAllCarbonConsumptionByDay(String username, Date currentDate){
		String sql = "SELECT * FROM Carbon_Consumption WHERE username=? AND date=?";
		List<CarbonConsumption> cc = template.query(sql, new CO2ConsumptionRowMapper(), username, currentDate);
		return cc;
	}
	
	// return emissions during period
	public List<CarbonConsumption> getAllCarbonConsumptionDuringPeriod(String username, Date start, Date end){
		String sql = "SELECT * FROM Carbon_Consumption WHERE username=? AND date BETWEEN ? AND ?";
		List<CarbonConsumption> cc = template.query(sql, new CO2ConsumptionRowMapper(), username, start, end);
		return cc;
	}

	@Override
	public User getUserByUsername(String uname) {
		String sql = "SELECT * FROM User WHERE username=?";
		return template.queryForObject(sql, new UserRowMapper(), uname);	
	}

	@Override
	public User getUserByCredentials(String uname, String password) {
		String sql = "SELECT * FROM User WHERE username=? AND password=?";
		return template.queryForObject(sql, new UserRowMapper(), uname, password);
	}

	@Override
	public int editUserbyUsername(String username, User newInfo) {
		String sql = "UPDATE User SET email=?, mobile=?, dob=?, address=?, password=? WHERE username=?";
		return template.update(sql,
					newInfo.getEmail(),
					newInfo.getMobile(),
					newInfo.getDob(),
					newInfo.getAddress(),
					newInfo.getPassword(),
					username);
	}

}
