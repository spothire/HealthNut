package com.example.healthnut;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.support.Exercise;
import com.example.support.Food;

import DBLayout.SqlLiteController;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class appCalendar extends Activity {
	SqlLiteController dbController = new SqlLiteController(this);
	
	private TextView tvDisplayDate;
	private DatePicker dpResult;
	private Button btnChangeDate;
 
	private int year;
	private int month;
	private int day;
 
	static final int DATE_DIALOG_ID = 999;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
 
		setCurrentDateOnView();
		addListenerOnButton();
 
	}
 
	// display current date
	public void setCurrentDateOnView() {
 
		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		dpResult = (DatePicker) findViewById(R.id.dpResult);
 
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		//start of code to get from db
		StringBuilder sb = new StringBuilder().append(day).append(month).append(year);
		String searchDate = sb.toString();

		ArrayList<Food> foodList = dbController.getFoodByDate(searchDate);
		ArrayList<Exercise> exerList = dbController.getExerByDate(searchDate);
		
		String curDate = (new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" ")).toString();
		tvDisplayDate.setText("Current Date:"+curDate+"\n"+foodList.toString()+"\n"+exerList.toString());
 
		// set current date into datepicker
		dpResult.init(year, month, day, null);
 
	}
 
	public void addListenerOnButton() {
 
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
 
		btnChangeDate.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(DATE_DIALOG_ID);
 
			}
 
		});
 
	}
 
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, 
                         year, month,day);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener datePickerListener 
                = new DatePickerDialog.OnDateSetListener() {
 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
 
			StringBuilder sb = new StringBuilder().append(day).append(month).append(year);
			String searchDate = sb.toString();

			ArrayList<Food> foodList = dbController.getFoodByDate(searchDate);
			ArrayList<Exercise> exerList = dbController.getExerByDate(searchDate);
			
			String curDate = (new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" ")).toString();
			tvDisplayDate.setText("Current Date:"+curDate+"\n"+foodList.toString()+"\n"+exerList.toString());
 
			// set selected date into datepicker also
			dpResult.init(year, month, day, null);
 
		}
	};
 
}
