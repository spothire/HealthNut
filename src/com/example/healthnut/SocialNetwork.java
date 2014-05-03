package com.example.healthnut;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.example.support.Food;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SocialNetwork extends ActionBarActivity{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_network);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
        
		
		
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		final Button post = (Button) findViewById(R.id.post);
		//final EditText day = (EditText) findViewById(R.id.day);
		//final EditText month = (EditText) findViewById(R.id.month);
		//final EditText year = (EditText) findViewById(R.id.year);
		
		
		/*imageGallery=(LinearLayout)findViewById(R.id.linearImage);
		Log.i("SN", "Got Buttons");
		//for(int i=0; i<lengthOfImageIdArray; i++){
		
			File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages"); 
			String filename = "image_001.jpg";
			File imageloc = new File(imagesFolder, filename);
			Uri fileUri = Uri.fromFile(imageloc);
		    ImageView image=new ImageView(SocialNetwork.this);
		  //  image.setBackgroundResource(yourArrayName[i]);
		    
		    
		    Log.i("SN", "Setting Images");
		    Log.i("SN", filename.substring(5, filename.length()-5));
		    int count = 0;
		    for (File f : imagesFolder.listFiles()) {
		    	
		        filename = f.getName();
		        imageloc = new File(imagesFolder, filename);
				fileUri = Uri.fromFile(imageloc);
			    image=new ImageView(SocialNetwork.this);
			    image.setImageURI(fileUri);
			    Log.i("SN", "arrays?");
			  //  array[count] = Integer.parseInt(filename.substring(6, filename.length()-4));
			    Log.i("SN", "problems?");
			    imageGallery.addView(image);
			    
		    }*/
		    
		
        main_menu.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View arg0) {
            	
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  MainActivity.class);
                
                startActivity(nextScreen);

            }
        });

	
    	post.setOnClickListener(new View.OnClickListener() {
    	
    		public void onClick(View arg0) {
    			int iday = 2;
    			int imonth = 4;
    			int iyear = 2014;
    			
    			/*if(day.getText()!=null)
    				iday = Integer.parseInt(day.getText().toString());
    			if(month.getText()!=null)
    				imonth = Integer.parseInt(month.getText().toString());
    			if(year.getText()!=null)
    				iyear = Integer.parseInt(year.getText().toString());*/
    			
        		String dateID = Integer.toString(iday)+Integer.toString(imonth)+Integer.toString(iyear);
    			//Starting a new Intent
    			Intent nextScreen = new Intent(getApplicationContext(),  NFC.class);
    			nextScreen.putExtra("date", dateID);
                Toast.makeText(getApplicationContext(), dateID, Toast.LENGTH_SHORT).show();
    			startActivity(nextScreen);

    		}
    	});
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social_network, menu);
		return true;
	}
    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam

    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
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
			View rootView = inflater.inflate(R.layout.fragment_social_network,
					container, false);
			return rootView;
		}
	}
	
}
