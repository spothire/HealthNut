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

/**
 * SqlLiteController controls the input/output of information from two tables in our db:
 * Food and Exercise Tables
 * @author Connie
 *
 */
public class SqlLiteController extends SQLiteOpenHelper implements Serializable{
	private static final String LOGCAT = null;
	
	//create and print to log
	public SqlLiteController(Context context) {
		super(context, "androidsqlite.db", null,1);
		Log.d(LOGCAT,"Created");
	}

	@Override
	/**
	 * create food and exercise tables
	 */
	public void onCreate(SQLiteDatabase db) {
		String query;
		query = "CREATE TABLE Food ( FoodId INTEGER PRIMARY KEY, "
				+ "Name STRING, Calories INTEGER, Type STRING, Date STRING,"
				+ "Latitude REAL, Longitude REAL)";
		db.execSQL(query);
		Log.d(LOGCAT,"Food Table Created");
		
		query = "CREATE TABLE Exercise ( ExerId INTEGER PRIMARY KEY, "
				+ "Name STRING, Date STRING, Latitude REAL, Longitude REAL)";
		db.execSQL(query);
		Log.d(LOGCAT,"Exercise Table Created");
	}

	/**
	 * inserts food f
	 * @param f
	 */
	public void insertFood(Food f) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("FoodId", f.getFood_id());
		values.put("Name", f.getFood_name());
		values.put("Calories", f.getFood_calories());
		values.put("Type", f.getType());
		values.put("Date", f.getDate());
		values.put("Latitude", f.getLatitude());
		values.put("Longitude", f.getLongitude());
		
		db.insert("Food", null, values);
		db.close();
		
		Log.d(LOGCAT,"Food "+f.getFood_id()+" Inserted");
	}
	
	/**
	 * insert exercise e into table
	 * @param e
	 */
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
	 * get food by id
	 * @param id
	 * @return row with foodId =id as Food item
	 */
	public Food readFood(int id) {	
		SQLiteDatabase db = this.getReadableDatabase();
	    String selectQuery = "SELECT  * FROM Food WHERE FoodId"
	        + " = " + id;
	    Log.e("Database", selectQuery);
	    
	    Cursor c = db.rawQuery(selectQuery, null);
	    if (c != null) {
	        c.moveToFirst();
		    Food retFood = new Food(c.getInt(0), c.getString(1), 
					c.getInt(2), c.getString(3), c.getString(4),
					c.getDouble(5), c.getDouble(6));
		    return retFood;
	    }
	    else return null;
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
			Exercise ret = new Exercise(cursor.getInt(0), cursor.getString(1), 
					cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
			return ret;
		}
	
		return null;
	}
	
	/**
	 * get all food with same date string
	 * @param date
	 * @return
	 */
	public ArrayList<Food> getFoodByDate(String date) {
		ArrayList<Food> foodList = new ArrayList<Food>();
		String selQuery = "SELECT * FROM Food where Date='"+date+"'";
		Log.e("Database", selQuery);
	    
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selQuery, null);
		
		if (cursor.moveToFirst()) {
			do {
				Food f = new Food(cursor.getInt(0), cursor.getString(1), 
					cursor.getInt(2), cursor.getString(3), cursor.getString(4),
					cursor.getDouble(5), cursor.getDouble(6));
				//add food to arraylist
				foodList.add(f);
			} while (cursor.moveToNext());
		}
		
		return foodList;
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
				Exercise e = new Exercise(cursor.getInt(0), cursor.getString(1), 
					cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
				exerList.add(e);
			} while (cursor.moveToNext());
		}
		
		return exerList;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS Food";
		db.execSQL(query);		
		
		query = "DROP TABLE IF EXISTS Exercise";
		db.execSQL(query);
		onCreate(db);
	}
	
	/**
	 * delete food item based on id provided
	 * @param id
	 */
	public void deleteFood(int id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery = "DELETE FROM Food where FoodId='"+ id +"'";
		Log.d("query",deleteQuery);
		database.execSQL(deleteQuery);
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
