package com.wileyedge.finalcourseproject.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	private static List<User> users = new ArrayList<User>();
	static {
		users.add(new User("jack_02","jack@gmail.com",88997765, new Date(), "Rhodes Ave, New York City - 100", "secret"));
	}
	
	public UserDao() {
		System.out.println("Object of UserDao created");
	}
	
	// save a new user 
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public User save(User user) {	
		String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, 
				user.getUsername(),
				user.getAddress(),
				user.getDob(),
				user.getEmail(),
				user.getMobile(),
				user.getPassword(),				
				user.getUserid());
		return user;
	}

	@Override
	// Given a user add an activity
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addActivity(CarbonConsumption activity) {
		String sql = "INSERT INTO Carbon_Consumption VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, 
				activity.getCo2EmissionID(),
				activity.getUserID(),
				activity.getDate(),
				activity.getTravelType(),
				activity.getKmDriven(),
				activity.getCo2Emission());
	}
	
	// Get user by userid
	public User getUserById(int uId) {
		String sql = "SELECT * FROM User WHERE id=?";
		return template.queryForObject(sql, new Object[] {uId}, new UserRowMapper());	
	}
	
	
	// Edit user by userid
	public User editUserbyId(int uid) {	
		return null;
	}
	
	//getActivitiesOnDay
	public List<CarbonConsumption> emissionsOnDay(Date currentDate){
		String sql = 
				"SELECT FROM Carbon_Consumption "
				+ "WHERE date="+currentDate;
		List<CarbonConsumption> cc = template.query(sql, new CO2ConsumptionRowMapper());
		return cc;
	}
	
	// return emissions during period
	public List<CarbonConsumption> emissionsDuringPeriod(Date start, Date end){
		String sql = 
				"SELECT FROM Carbon_Consumption "
				+ "WHERE Carbon_Consumption.date BETWEEN "
						+start+ " AND "+end;
		List<CarbonConsumption> cc = template.query(sql, new CO2ConsumptionRowMapper());
		return cc;
	}

	@Override
	public User getUserByUsername(String uname) {
		String sql = "SELECT * FROM User WHERE username=?";
		return template.queryForObject(sql, new Object[] {uname}, new UserRowMapper());	
	}

	@Override
	public User getUserByCredentials(Map<String, String> credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editUserbyUsername(String username, User newInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CarbonConsumption> getAllCarbonConsumptionByDay(String username, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarbonConsumption> getAllCarbonConsumptionDuringPeriod(String username, Date dateStart, Date dateEnd) {
		// TODO Auto-generated method stub
		return null;

	}

}
