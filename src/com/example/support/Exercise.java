package com.example.support;

public class Exercise {
	int exer_id;
	String exer_name;
	
	public Exercise(int exer_id, String exer_name) {
		super();
		this.exer_id = exer_id;
		this.exer_name = exer_name;
	}
	
	public int getExer_id() {
		return exer_id;
	}
	public void setExer_id(int exer_id) {
		this.exer_id = exer_id;
	}
	public String getExer_name() {
		return exer_name;
	}
	public void setExer_name(String exer_name) {
		this.exer_name = exer_name;
	}
	
	
}
