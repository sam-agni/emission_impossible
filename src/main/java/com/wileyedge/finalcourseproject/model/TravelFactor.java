package com.wileyedge.finalcourseproject.model;

public enum TravelFactor{
	Small_Car(1),
	Heavy_Car(2),
	Trains(3),
	Planes(4);

	int t;
	TravelFactor(int i) {
		t = i;
	}
	public int getValue(){
		return t;
	}
	
	@Override
	public String toString() {
		String result = "";
		if (this == TravelFactor.Small_Car) {
			result = "Small Car";
		} else if (this == TravelFactor.Heavy_Car) {
			result = "Heavy Car";
		} else if (this == TravelFactor.Trains) {
			result = "Train";
		} else if (this == TravelFactor.Planes) {
			result = "Plane";
		}

		return result;
	}
	
}
