package DBLayout;

import java.util.HashMap;

import com.example.support.Exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExerDbController extends SQLiteOpenHelper {
	private static final String LOGCAT = null;
	
	//create and print to log
	public ExerDbController(Context context) {
		super(context, "androidsqlite.db", null,1);
		Log.d(LOGCAT,"Created");
	}
	
	@Override
	/**
	 * create exercise db
	 */
	public void onCreate(SQLiteDatabase db) {
		String query;		
		query = "CREATE TABLE Exercise ( ExerId INTEGER PRIMARY KEY, "
				+ "Name STRING, Latitude REAL, Longitude REAL)";
		db.execSQL(query);
		Log.d(LOGCAT,"Exercise Table Created");
	}

	public void insertExercise(Exercise e) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("ExerId", e.getExer_id());
		values.put("Name", e.getExer_name());
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
			Exercise ret = new Exercise(cursor.getInt(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
			return ret;
		}
	
		return null;
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
