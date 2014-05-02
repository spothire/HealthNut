package com.example.healthnut;

import java.util.Calendar;

import com.example.support.Food;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class Analyze extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyze);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		
		final TextView food_view = (TextView) findViewById(R.id.food_vew);
		final TextView exercise_view = (TextView) findViewById(R.id.exercise_view);
		
		
		
		LocationManager locationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
	    String provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
	    double longitude = location.getLongitude();
		double latitude = location.getLatitude();
		
		
		Food testfood = new Food(100,  "Burger", 1000, "Lunch", "242014", latitude, longitude );
		
		testfood.printtoView(food_view, this);
		
		
		
		Calendar c = Calendar.getInstance(); 
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		
		String dateId = Integer.toString(day)+Integer.toString(month)+Integer.toString(year);
		
		Log.i("A", Integer.toString(day));
		Log.i("A", Integer.toString(month));
		Log.i("A", Integer.toString(year));
		Toast.makeText(getApplicationContext(), dateId, Toast.LENGTH_SHORT).show();
        
		//TODO Get database with day month year here
		main_menu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  MainActivity.class);
                startActivity(nextScreen);

            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.analyze, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_analyze,
					container, false);
			return rootView;
		}
	}

}
