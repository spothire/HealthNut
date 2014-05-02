package DBLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.support.Exercise;
import com.example.support.Food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExerDbController extends SQLiteOpenHelper implements Serializable {
	private static final String LOGCAT = null;
	
	//create and print to log
	public ExerDbController(Context in) {
		super(in, "androidsqlite.db", null,1);
		Log.d(LOGCAT,"Created");
	}
	
	@Override
	/**
	 * create exercise db
	 */
	public void onCreate(SQLiteDatabase db) {
		String query;		
		query = "CREATE TABLE Exercise ( ExerId INTEGER PRIMARY KEY, "
				+ "Name STRING, Date STRING, Latitude REAL, Longitude REAL)";
		db.execSQL(query);
		Log.d(LOGCAT,"Exercise Table Created");
	}

	public void insertExercise(Exercise e) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("ExerId", e.getExer_id());
		values.put("Name", e.getExer_name());
		values.put("Date", e.getDate());
		values.put("Latitude", e.getLatitude());
		values.put("Longitude", e.getLongitude());
		
		db.insert("Exercise", null, values);
		db.close();
		
		Log.d(LOGCAT,"Exercise"+e.getExer_id()+" Inserted");
	}
	
	/**
	 * get exercise by id
	 */
	public Exercise readExer(int id) {
		HashMap<String, Integer> row = new HashMap<String, Integer>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM Exercise where ExerId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) { 
			Exercise ret = new Exercise(cursor.getInt(1), cursor.getString(2), 
					cursor.getString(3), cursor.getDouble(4), cursor.getDouble(5));
			return ret;
		}
	
		return null;
	}
	
	/**
	 * get all food with same date string
	 * @param date
	 * @return
	 */
	public ArrayList<Exercise> getExerByDate(String date) {
		ArrayList<Exercise> exerList = new ArrayList<Exercise>();
		String selQuery = "SELECT * FROM Exercise where Date='"+date+"'";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				Exercise e = new Exercise(cursor.getInt(1), cursor.getString(2), 
					cursor.getString(3), cursor.getDouble(4), cursor.getDouble(5));
				exerList.add(e);
			} while (cursor.moveToNext());
		}
		
		return exerList;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS Exercise";
		db.execSQL(query);
		onCreate(db);
	}
		
	/**
	 * delete exercise item based on id provided
	 * @param id
	 */
	public void deleteExer(int id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery = "DELETE FROM Exercise where ExerId='"+ id +"'";
		Log.d("query",deleteQuery);
		database.execSQL(deleteQuery);
	}
	
}
