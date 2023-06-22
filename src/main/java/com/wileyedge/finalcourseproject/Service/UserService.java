package com.wileyedge.finalcourseproject.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wileyedge.finalcourseproject.Dao.IDao;
import com.wileyedge.finalcourseproject.exceptions.EmissionInvalidException;
import com.wileyedge.finalcourseproject.exceptions.RegistrationFailedException;
import com.wileyedge.finalcourseproject.exceptions.UserNotFoundException;
import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.TravelConstants;
import com.wileyedge.finalcourseproject.model.TravelFactor;
import com.wileyedge.finalcourseproject.model.User;

@Service
public class UserService implements IService {
	
	@Autowired
	private IDao dao;

	@Override
	public String register(User user) throws RegistrationFailedException {
		int numRowsAffected = dao.save(user);
		if (numRowsAffected == 0) {
			throw new RegistrationFailedException();
		}
		return user.getUsername();
	}

	@Override
	public String login(Map<String, String> credentials) throws UserNotFoundException {
		User u = dao.getUserByCredentials(credentials.get("username"), credentials.get("password"));
		if (u == null) {
			throw new UserNotFoundException();
		}
		return u.getUsername();
	}

	@Override
	public User getUserProfile(String username) throws UserNotFoundException {
		User u = dao.getUserByUsername(username);
		if (u == null) {
			throw new UserNotFoundException();
		}
		return u;
	}

	@Override
	public void editUserProfile(String username, User newInfo) throws UserNotFoundException {
		int numRowsAffected = dao.editUserbyUsername(username, newInfo);
		if (numRowsAffected == 0) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<Map<String, String>> getActivitiesOnDay(String username, Date date) throws UserNotFoundException {
		List<Map<String, String>> result = new ArrayList<>();
		List<CarbonConsumption> activities = dao.getAllCarbonConsumptionByDay(username, date);
		if (activities == null) {
			throw new UserNotFoundException();
		}
		Map<String, String> activityAndCarbon = null;
		String activity = null;
		for (CarbonConsumption cc : activities) {
			activityAndCarbon = new HashMap<>();
			activity = cc.getKmDriven() + "km Journey by " + cc.getTravelType();
			activityAndCarbon.put("activity", activity);
			activityAndCarbon.put("carbonAmount", cc.getCo2Emission().toString());
			result.add(activityAndCarbon);
			activityAndCarbon = null;
			activity = null;
		}
		
		return result;
	}

	@Override
	public Map<String, Object> getEmissionsDuringPeriod(String username, Date dateStart, Date dateEnd) throws UserNotFoundException {
	    List<CarbonConsumption> activities = dao.getAllCarbonConsumptionDuringPeriod(username, dateStart, dateEnd);
	    if (activities == null) {
	        throw new UserNotFoundException();
	    }

	    Long bySmallCar = 0L, byHeavyCar = 0L, byTrain = 0L, byPlane = 0L, total = 0L;
	    Map<String, Long> dateToTotalMap = new LinkedHashMap<>(); // Maintain insertion order

	    for (CarbonConsumption cc : activities) {
	        if (cc.getTravelType() == TravelFactor.Small_Car) {
	            bySmallCar += cc.getCo2Emission();
	        } else if (cc.getTravelType() == TravelFactor.Heavy_Car) {
	            byHeavyCar += cc.getCo2Emission();
	        } else if (cc.getTravelType() == TravelFactor.Trains) {
	            byTrain += cc.getCo2Emission();
	        } else if (cc.getTravelType() == TravelFactor.Planes) {
	            byPlane += cc.getCo2Emission();
	        }
	        total += cc.getCo2Emission();

	     // Add data to time series
	        String dateLabel = new SimpleDateFormat("yyyy-MM-dd").format(cc.getDate());
	        dateToTotalMap.put(dateLabel, dateToTotalMap.getOrDefault(dateLabel, 0L) + cc.getCo2Emission());
	    }

	    // Sort the dateToTotalMap by date
	    Map<String, Long> sortedDateToTotalMap = new TreeMap<>(dateToTotalMap);

	    // Convert the map to two lists: one for labels and one for data
	    List<String> timeSeriesLabels = new ArrayList<>(sortedDateToTotalMap.keySet());
	    List<Long> timeSeriesData = new ArrayList<>(sortedDateToTotalMap.values());
	    
	    // Calculate the cumulative sum for timeSeriesData
	    for (int i = 1; i < timeSeriesData.size(); i++) {
	        timeSeriesData.set(i, timeSeriesData.get(i) + timeSeriesData.get(i - 1));
	    }

	    Map<String, Object> result = new HashMap<>();
	    result.put("bySmallCar", bySmallCar);
	    result.put("byHeavyCar", byHeavyCar);
	    result.put("byTrain", byTrain);
	    result.put("byPlane", byPlane);
	    result.put("total", total);
	    result.put("timeSeriesLabels", timeSeriesLabels);
	    result.put("timeSeriesData", timeSeriesData);

	    return result;
	}





	@Override
	public void addNewEmissionsEntry(String username, CarbonConsumption entry) throws EmissionInvalidException {
		int numRowsAffected = dao.addActivity(entry);
		if (numRowsAffected == 0) {
			throw new EmissionInvalidException();
		}
	}
	
	
	/*
	@Override
	public List<User> retrieveAllUsers() {
		System.out.println("Inside retrieveAllUsers() of UserService");
		List<User> users = dao.findAll();
		return users;
	}

	@Override
	public User save(User user) {
		System.out.println("user save service");
		User u = dao.save(user);
		return u;
	}

	@Override
	public CarbonConsumption addUserActivity(CarbonConsumption cc) {
		TravelFactor mode = TravelFactor.Small_Car;
		//CarbonConsumption activity1 = new CarbonConsumption(1,new Date(),mode,10);
		dao.addActivity(cc);
		return cc;
	}
	*/

}
