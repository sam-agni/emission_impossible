package com.wileyedge.finalcourseproject.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.springframework.jdbc.core.RowMapper;

import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.TravelConstants;
import com.wileyedge.finalcourseproject.model.TravelFactor;


public class CO2ConsumptionRowMapper implements RowMapper<CarbonConsumption> {

	@Override
	public CarbonConsumption mapRow(ResultSet rs, int rowNum) throws SQLException {
		int ccid = rs.getInt("co2EmissionID");
		String username = rs.getString("username");
		Date date = rs.getDate("date");
		String tf = rs.getString("travelType");
		Double kmdriven = rs.getDouble("kmDriven");
		Long co2Emission = rs.getLong("co2Emission");
		
		TravelConstants cc = new TravelConstants();
		TravelFactor factor = cc.stringConvert(tf);
		CarbonConsumption c = new CarbonConsumption(ccid, username, date, factor, kmdriven,co2Emission);
		return c;
	}

}
