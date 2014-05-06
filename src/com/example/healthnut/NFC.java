package com.example.healthnut;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.support.Food;

import DBLayout.SqlLiteController;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class NFC extends Activity implements CreateNdefMessageCallback,
        OnNdefPushCompleteCallback {
    NfcAdapter mNfcAdapter;
    ArrayList<Food> foodList;
    String nfcTag;
    TextView mInfoText;
    Spinner spin;
    SqlLiteController foodDb = new SqlLiteController(this);
    private static final int MESSAGE_SENT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        mInfoText = (TextView) findViewById(R.id.nfc_text);
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            mInfoText = (TextView) findViewById(R.id.nfc_text);
            mInfoText.setText("NFC is not available on this device.");
        }
        // Register callback to set NDEF message
        mNfcAdapter.setNdefPushMessageCallback(this, this);
        // Register callback to listen for message-sent success
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        final Button main_menu = (Button) findViewById(R.id.nfc_main);
        
        Intent i = getIntent();
        
        String dateID = i.getStringExtra("date");
        foodList = foodDb.getFoodByDate(dateID);
        
        spin = (Spinner) findViewById(R.id.spinner1);
       
        
    	List<String> list = new ArrayList<String>();


        
        
        for(int j =0; j< foodList.size(); j++){
        	list.add(foodList.get(j).getFood_name());
        }
        
        if(foodList.size()>0){
    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_spinner_item, list);
        	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
        Toast.makeText(this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        
        }
        
        
            

        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String Name = spin.getSelectedItem().toString();
                
                Food selectedFood = null;
                for(int i = 0; i<foodList.size();i++){
                	if(foodList.get(i).getFood_name() == Name ){
                		selectedFood = foodList.get(i);
                	}
                }
                nfcTag = selectedFood.toString();
                mInfoText.setText(nfcTag);
            }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
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

    /**
     * Implementation for the CreateNdefMessageCallback interface
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	
    	
    	/*String name = spin.getSelectedItem().toString();
    	Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
    	int select = 0;
    	for(int i = 0; i< foodList.size();i++){
    		if(name.equals(foodList.get(i).getFood_name())){
    			select = i;
    		}
    	}*/
    	
    	
        String text = nfcTag;
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMimeRecord(
                        "application/com.example.healthnut", text.getBytes())
         /**
          * The Android Application Record (AAR) is commented out. When a device
          * receives a push with an AAR in it, the application specified in the AAR
          * is guaranteed to run. The AAR overrides the tag dispatch system.
          * You can add it back in to guarantee that this
          * activity starts when receiving a beamed message. For now, this code
          * uses the tag dispatch system.
          */
          //,NdefRecord.createApplicationRecord("com.example.healthnut")
        });
        return msg;
    }

    /**
     * Implementation for the OnNdefPushCompleteCallback interface
     */
    @Override
    public void onNdefPushComplete(NfcEvent arg0) {
        // A handler is needed to send messages to the activity when this
        // callback occurs, because it happens from a binder thread
        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    /** This handler receives a message from onNdefPushComplete */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_SENT:
                Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
                break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
     
        String ndefinput = new String(msg.getRecords()[0].getPayload());
        mInfoText.setText(ndefinput);
       // Food newFood = deserialize(ndefinput);
    }

    /**
     * Creates a custom MIME type encapsulated in an NDEF record
     *
     * @param mimeType
     */
    public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
        byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
        NdefRecord mimeRecord = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
        return mimeRecord;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // If NFC is not available, we won't be needing this menu
        if (mNfcAdapter == null) {
            return super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nfc, menu);
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
}
