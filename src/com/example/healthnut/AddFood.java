package com.example.healthnut;

import java.util.Calendar;

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
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class AddFood extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_food);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		final Button add = (Button) findViewById(R.id.food_add);
		final EditText food = (EditText) findViewById(R.id.food_input);
		final EditText notes = (EditText) findViewById(R.id.food_notes);
				
		
		
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                
            	Intent i = getIntent();
                // Receiving the Data
                String type = i.getStringExtra("type");
                Calendar c = Calendar.getInstance(); 
                String name = food.getText().toString();
                String note = notes.getText().toString();
                int id = c.get(Calendar.DATE) + c.get(Calendar.MONTH) + c.get(Calendar.YEAR);
                
                Toast.makeText(getApplicationContext(), name + " " + id + " " + note + " " + type, Toast.LENGTH_SHORT).show();
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
		getMenuInflater().inflate(R.menu.add_food, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_add_food,
					container, false);
			return rootView;
		}
	}

}
