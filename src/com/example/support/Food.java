package com.example.support;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

@ParseClassName("Food")

public class Food extends ParseObject implements java.io.Serializable{

	int food_id;
	 String food_name;
	 int food_calories;
	 String type;
	 String date;
	 double latitude;
	 double longitude;
	 File image;
	 
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}
	
	public Food(){
		
	}

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
	
	@Override
	public String toString() {
		return "Food Name: " + this.food_name + "\n" +
				"Calories: " + this.food_calories + "\n" +
				"Type: " + this.type + "\n" +
				"Latitude: " + this.latitude +"\n" +
				"Longitude " + this.longitude +"\n\n";
		
	}

	public void printtoView(TextView text, Context con){
		text.append("Food Name: " + this.food_name + "\n");
		text.append("Calories: " + this.food_calories + "\n");
		text.append("Type: " + this.type + "\n");
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
	
    public String getTitle() {
        return getString("title");
    }
 
    public void setTitle(String title) {
        put("title", title);
    }
 
    public ParseUser getAuthor() {
        return getParseUser("author");
    }
 
    public void setAuthor(ParseUser user) {
        put("author", user);
    }
 
    public String getRating() {
        return getString("rating");
    }
 
    public void setRating(String rating) {
        put("rating", rating);
    }
 
    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }
 
    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }
	
}
