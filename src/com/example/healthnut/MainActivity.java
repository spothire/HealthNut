package com.example.healthnut;

import add.Today;
import analyze.Analyze;

import analyze.appCalendar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

import com.example.support.Food;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//Parse Initialization 
	
		final Button view_today = (Button) findViewById(R.id.viewtoday);
		final Button calendar = (Button) findViewById(R.id.calendar);
		final Button analyze = (Button) findViewById(R.id.analyze);
		final Button social = (Button) findViewById(R.id.social_network);

        //Listening to button event
         view_today.setOnClickListener(new View.OnClickListener() {

              public void onClick(View arg0) {
                  //Starting a new Intent
                  Intent nextScreen = new Intent(getApplicationContext(),  Today.class);
                  startActivity(nextScreen);

              }
          }); 
         

         
         calendar.setOnClickListener(new View.OnClickListener() {

             public void onClick(View arg0) {
                 //Starting a new Intent
                 Intent nextScreen = new Intent(getApplicationContext(),  appCalendar.class);
                 startActivity(nextScreen);

             }
         }); 
         analyze.setOnClickListener(new View.OnClickListener() {

             public void onClick(View arg0) {
                 //Starting a new Intent
                 Intent nextScreen = new Intent(getApplicationContext(),  Analyze.class);
                 startActivity(nextScreen);

             }
         }); 
         social.setOnClickListener(new View.OnClickListener() {

             public void onClick(View arg0) {
                 //Starting a new Intent
                 Intent nextScreen = new Intent(getApplicationContext(),  SocialNetwork.class);
                 startActivity(nextScreen);

             }
         }); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
