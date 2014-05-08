package com.example.support;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

public class Exercise implements java.io.Serializable{
	private int exer_id;
	private String exer_name;
	private String date;
	private double latitude;
	private double longitude;
	
	//constructor
	public Exercise(int exer_id, String exer_name, String date,
			double latitude, double longitude) {
		super();
		this.exer_id = exer_id;
		this.exer_name = exer_name;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	//getters and setters
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * prints text to view based on TextView text, Context con
	 * @param text
	 * @param con
	 */
	public void printtoView(TextView text, Context con){
		text.append("Exercise Name: " + this.exer_name + "\n");
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(con, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);
		text.append("Address: " + address + "\n");
		text.append("City: " + city + "\n");
		text.append("Country: " + country + "\n\n");
	}
	
	@Override
	/**
	 * converts exercise into readable string format
	 */
	public String toString() {
		return "Exercise: " + this.exer_name + "\n" +
				"Latitude: " + this.latitude + "\n" +
				"Longitude: " + this.longitude + "\n";
	}
		
}