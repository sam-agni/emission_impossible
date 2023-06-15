package com.wileyedge.finalcourseproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
public class CarbonConsumption {
	@Id
	private int co2EmissionID;
	
	@Column
	private int userID;
	
	@Column
	private Date date;
	
	@Column
	private TravelFactor travelType;
	
	@Column
	private double kmDriven;
	
	@Column
	private long co2Emission;
	
	public CarbonConsumption(int co2EmissionID, int userID, Date date, TravelFactor travelType, double kmDriven,
			long co2Emission) {
		super();
		this.co2EmissionID = co2EmissionID;
		this.userID = userID;
		this.date = date;
		this.travelType = travelType;
		this.kmDriven = kmDriven;
		this.co2Emission = co2Emission;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public long getCo2Emission() {
		return co2Emission;
	}
	public void setCo2Emission(long co2Emission) {
		this.co2Emission = co2Emission;
	}
}
