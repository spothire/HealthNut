<<<<<<< HEAD:src/add/Today.java
package add;

import com.example.healthnut.MainActivity;
import com.example.healthnut.R;
import com.example.healthnut.R.id;
import com.example.healthnut.R.layout;
import com.example.healthnut.R.menu;

import DBLayout.SqlLiteController;
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

public class Today extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		/**button code **/
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		final Button exer = (Button) findViewById(R.id.add_exercise);
		final Button breakfast = (Button) findViewById(R.id.add_breakfast);
		final Button lunch = (Button) findViewById(R.id.add_lunch);
		final Button dinner = (Button) findViewById(R.id.add_dinner);
		final Button snacks = (Button) findViewById(R.id.add_other);
		final Button calendar = (Button) findViewById(R.id.calendar);
		
		
		calendar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  appCalendar.class);
                startActivity(nextScreen);

            }
        });
		
		breakfast.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "breakfast");
                startActivity(nextScreen);

            }
        });
		lunch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "lunch");
                startActivity(nextScreen);

            }
        });
		dinner.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "dinner");
                startActivity(nextScreen);

            }
        });
		snacks.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "snacks");
                startActivity(nextScreen);

            }
        });

		exer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  AddExercise.class);
                startActivity(nextScreen);
            }
        });
		
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
		getMenuInflater().inflate(R.menu.today, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_today,
					container, false);
			return rootView;
		}
	}

}
=======
package com.example.healthnut;

import DBLayout.SqlLiteController;
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

public class Today extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_today);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		/**button code **/
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		final Button exer = (Button) findViewById(R.id.add_exercise);
		final Button breakfast = (Button) findViewById(R.id.add_breakfast);
		final Button lunch = (Button) findViewById(R.id.add_lunch);
		final Button dinner = (Button) findViewById(R.id.add_dinner);
		final Button snacks = (Button) findViewById(R.id.add_other);
		
		breakfast.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "breakfast");
                startActivity(nextScreen);

            }
        });
		lunch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "lunch");
                startActivity(nextScreen);

            }
        });
		dinner.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "dinner");
                startActivity(nextScreen);

            }
        });
		snacks.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
            	Intent nextScreen = new Intent(getApplicationContext(),  AddFood.class);
                nextScreen.putExtra("type", "snacks");
                startActivity(nextScreen);

            }
        });

		exer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  AddExercise.class);
                startActivity(nextScreen);
            }
        });
		
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
		getMenuInflater().inflate(R.menu.today, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_today,
					container, false);
			return rootView;
		}
	}

}
>>>>>>> FETCH_HEAD:src/com/example/healthnut/Today.java
