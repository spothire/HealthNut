package com.example.healthnut;

import java.util.Calendar;

import com.example.support.Exercise;

import DBLayout.ExerDbController;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

public class AddExercise extends ActionBarActivity {
	ExerDbController exerDb = new ExerDbController(this);
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_exercise);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		final Button add_exer = (Button) findViewById(R.id.add_exercise);
		final EditText exer = (EditText) findViewById(R.id.exercise);
		
        add_exer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
            	//unix timestamp
            	int id = (int) (System.currentTimeMillis() / 1000L);
            	
            	String name = exer.getText().toString();
            	
            	//create date
            	Calendar c = Calendar.getInstance(); 
            	int day = c.get(Calendar.DAY_OF_MONTH);
            	int month = c.get(Calendar.MONTH);
            	int year = c.get(Calendar.YEAR);

            	String dateStr = Integer.toString(day)+Integer.toString(month)+Integer.toString(year);

            	Exercise eObj = new Exercise(id, name, dateStr, 0.0, 0.0);
            	exerDb.insertExercise(eObj);
            	
            	Toast.makeText(getApplicationContext(), name + " " + id + " " + dateStr, Toast.LENGTH_SHORT).show();
            	
            	try {
            	    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            	    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            	    r.play();
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
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
		getMenuInflater().inflate(R.menu.add_exercise, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_add_exercise,
					container, false);
			return rootView;
		}
	}

}
