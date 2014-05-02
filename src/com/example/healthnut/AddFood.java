package com.example.healthnut;

import java.io.File;
import java.util.Calendar;

import com.example.support.Food;

import DBLayout.FoodDbController;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;

public class AddFood extends ActionBarActivity {
	//public static final int MEDIA_TYPE_IMAGE = 1;
	FoodDbController foodDb = new FoodDbController(this);
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	ImageView mImage;
	Uri fileUri;
	int id;

	@Override
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  

		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	mImage = (ImageView) findViewById(R.id.mImageView);
                mImage.setImageURI(fileUri);
	            // Image captured and saved to fileUri specified in the Intent
	            //Toast.makeText(this, "Image saved to:\n" +
	              //       data.getData(), Toast.LENGTH_LONG).show();
	        } else if (resultCode == RESULT_CANCELED) {
	        	Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
	            // User cancelled the image capture
	        } else {
	        	Toast.makeText(this, "Problem", Toast.LENGTH_LONG).show();
	            // Image capture failed, advise user
	        }
	    }
	}
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

            	//unix timestamp
            	id = (int) (System.currentTimeMillis() / 1000L);
            	
            	//create date
            	Calendar c = Calendar.getInstance(); 
            	int day = c.get(Calendar.DAY_OF_MONTH);
            	int month = c.get(Calendar.MONTH);
            	int year = c.get(Calendar.YEAR);

            	String dateStr = Integer.toString(day)+Integer.toString(month)+Integer.toString(year);
            	
                // Receiving the Data
                String type = i.getStringExtra("type");
                String name = food.getText().toString();
                String cals_str = (notes.getText()).toString();
                int cals;
                
                //insert into db
                try {
                	cals = Integer.parseInt(cals_str);
                	Food f = new Food(id, name, cals, type, dateStr, 0.0, 0.0);
                	//((FoodDbController) getIntent().getSerializableExtra("Food Db")).insertFood(f);
                	foodDb.insertFood(f);
                	Toast.makeText(getApplicationContext(), name + " " + id + 
                			" " + cals_str + " " + type + " "+ dateStr, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException nfe) {
                	Toast.makeText(getApplicationContext(), "Please input integer in calories field.", Toast.LENGTH_SHORT).show();
                }
                
                
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages"); 
                String filename = "image_"+ id + ".jpg";
                File image = new File(imagesFolder, filename);
                fileUri = Uri.fromFile(image); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                intent.putExtra("location", fileUri.toString());
                // start the image capture Intent
                
            	try {
            	    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            	    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            	    r.play();
            	} catch (Exception e) {
            	    e.printStackTrace();
            	}
                
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
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
