package com.example.healthnut;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;

import com.example.support.Food;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
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
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SocialNetwork extends ActionBarActivity implements CreateNdefMessageCallback,
OnNdefPushCompleteCallback{
	LinearLayout imageGallery;
    NfcAdapter mNfcAdapter;
    TextView mInfoText;
    int[] array = new int[100];
    private static final int MESSAGE_SENT = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_network);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
        
		
		Log.i("SN", "starting NFC");
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Log.i("SN", "started NFC");
        if (mNfcAdapter == null) {
            mInfoText = (TextView) findViewById(R.id.mTextView);
            mInfoText.setText("NFC is not available on this device.");
        } else {
            // Register callback to set NDEF message
            mNfcAdapter.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
		
        Log.i("SN", "Getting Buttons");
		final Button main_menu = (Button) findViewById(R.id.main_menu);
		imageGallery=(LinearLayout)findViewById(R.id.linearImage);
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
			    
		    }
		    Log.i("SN", "Set images");
		
        main_menu.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(),  MainActivity.class);
                startActivity(nextScreen);

            }
        });
	}
	
	private String serialize(Food myObject){
		 String serializedObject = "";

		 // serialize the object
		 try {
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     ObjectOutputStream so = new ObjectOutputStream(bo);
		     so.writeObject(myObject);
		     so.flush();
		     serializedObject = bo.toString();
		 } catch (Exception e) {
		     System.out.println(e);
		     System.exit(1);
		 }
		 return serializedObject;
	}
	
    /**
     * Implementation for the CreateNdefMessageCallback interface
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	int index = imageGallery.indexOfChild(imageGallery.getFocusedChild());
    	int id = array[index];
    	Food thisfood = null;
    	
    	String serialFood = serialize(thisfood);
       
        String text = (serialFood);
        NdefMessage msg = new NdefMessage(NdefRecord.createMime(
                "application/com.example.android.beam", text.getBytes())
         /**
          * The Android Application Record (AAR) is commented out. When a device
          * receives a push with an AAR in it, the application specified in the AAR
          * is guaranteed to run. The AAR overrides the tag dispatch system.
          * You can add it back in to guarantee that this
          * activity starts when receiving a beamed message. For now, this code
          * uses the tag dispatch system.
          */
          //,NdefRecord.createApplicationRecord("com.example.android.beam")
        );
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
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social_network, menu);
		return true;
	}
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
        mInfoText.setText(new String(msg.getRecords()[0].getPayload()));
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
