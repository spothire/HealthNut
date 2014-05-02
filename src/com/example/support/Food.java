package com.example.support;

public class Food {

	 int food_id;
	 String food_name;
	 int food_calories;
	 String type;
	 String date;
	 double latitude;
	 double longitude;	
	 
	public Food(int food_id, String food_name, int food_calories, String type,
			String date, double latitude, double longitude) {
		super();
		this.food_id = food_id;
		this.food_name = food_name;
		this.food_calories = food_calories;
		this.type = type;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	 
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getFood_calories() {
		return food_calories;
	}
	public void setFood_calories(int food_calories) {
		this.food_calories = food_calories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
