package ar.edu.untref.cdm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static ar.edu.untref.cdm.util.DataBaseContstant.DATABASE_NAME;
import static ar.edu.untref.cdm.util.DataBaseContstant.DATABASE_VERSION;

public class SQLiteHelper extends SQLiteOpenHelper {

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSubject = "CREATE TABLE subject (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, code INTEGER, quarter INTEGER, year INTEGER);";
		db.execSQL(createSubject);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS subject");
		this.onCreate(db);
	}

}