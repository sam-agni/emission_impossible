package com.wileyedge.finalcourseproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class CarbonConsumption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int co2EmissionID;
	
	@Column
	private String username;
	
	@Column
	private Date date;
	
	@Column
	private TravelFactor travelType;
	
	@Column
	private double kmDriven;
	
	@Column
	private Long co2Emission;
	
	public CarbonConsumption(int co2EmissionID, String userID, Date date, TravelFactor travelType, double kmDriven,
			long co2Emission) {
		super();
		this.co2EmissionID = co2EmissionID;
		this.username = userID;
		this.date = date;
		this.travelType = travelType;
		this.kmDriven = kmDriven;
		this.co2Emission = co2Emission;
	}
	//public static int idGenerator = 0;
	
	public CarbonConsumption() {
		
	}
	
	public CarbonConsumption(String userID, Date date, TravelFactor travelType, double kmDriven) {
		super();
		TravelConstants c = new TravelConstants();
		//this.co2EmissionID = idGenerator+1;
		this.username = userID;
		this.date = date;
		this.travelType = travelType;
		this.kmDriven = kmDriven;
		this.co2Emission = (long) (kmDriven * (c.constantsCalculator(travelType)));
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userID) {
		this.username = userID;
	}
	public int getCo2EmissionID() {
		return co2EmissionID;
	}
	public void setCo2EmissionID(int co2EmissionID) {
		this.co2EmissionID = co2EmissionID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TravelFactor getTravelType() {
		return travelType;
	}
	public void setTravelType(TravelFactor travelType) {
		this.travelType = travelType;
	}
	public double getKmDriven() {
		return kmDriven;
	}
	public void setKmDriven(double kmDriven) {
		this.kmDriven = kmDriven;
	}
	public Long getCo2Emission() {
		return co2Emission;
	}
	public void setCo2Emission(long co2Emission) {
		this.co2Emission = co2Emission;
	}
}
