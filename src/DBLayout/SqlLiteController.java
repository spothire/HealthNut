package DBLayout;

import java.util.HashMap;

import com.example.healthnut.Food;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteController extends SQLiteOpenHelper{
	private static final String LOGCAT = null;
	
	//create and print to log
	public SqlLiteController(Context context) {
		super(context, "androidsqlite.db", null,1);
		Log.d(LOGCAT,"Created");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query;
		query = "CREATE TABLE Food ( FoodId INTEGER PRIMARY KEY, "
				+ "Name STRING, Calories INTEGER)";
		db.execSQL(query);
		Log.d(LOGCAT,"Food Table Created");		
	}

	/**
	 * get food by id
	 * @param id
	 * @return row with foodId =id as Food item
	 */
	public Food readFood(int id) {
		HashMap<String, Integer> row = new HashMap<String, Integer>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM Food where FoodId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) { 
			Food retFood = new Food(cursor.getInt(1), cursor.getString(2), cursor.getInt(3));
			return retFood;
		}
	
		return null;
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS Food";
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

}
