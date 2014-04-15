package com.example.healthnut;

public class Food {

	 int food_id;
	 String food_name;
	 int food_calories;
	 
	public Food(int id, String name, int calories){
		this.food_id = id;
		this.food_name = name;
		this.food_calories = calories;
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
	
}
