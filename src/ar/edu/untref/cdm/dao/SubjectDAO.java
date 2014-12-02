package ar.edu.untref.cdm.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ar.edu.untref.cdm.database.SQLiteHelper;
import ar.edu.untref.cdm.domain.Subject;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_ID;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_NAME;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_CODE;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_QUARTER;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_YEAR;
import static ar.edu.untref.cdm.util.DataBaseContstant.KEY_TABLE;

public class SubjectDAO {

	private SQLiteHelper helper;
	public static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_CODE, KEY_QUARTER, KEY_YEAR };

	public SubjectDAO(Context context) {
		helper = new SQLiteHelper(context);
	}

	public void create(Subject subject) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, subject.getName());
		values.put(KEY_CODE, subject.getCode());
		values.put(KEY_QUARTER, subject.getQuarter());
		values.put(KEY_YEAR, subject.getYear());
		db.insert(KEY_TABLE, null, values);
		db.close();
	}

	public List<Subject> findSubjectsByYear(Integer year) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query(KEY_TABLE, COLUMNS, " year = ? ",
				new String[] { String.valueOf(year) }, null, null, null, null);
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject = null;
		if (cursor.moveToFirst()) {
			do {
				subject = new Subject();
				subject.setName(cursor.getString(1));
				subject.setCode(cursor.getInt(2));
				subject.setQuarter(cursor.getInt(3));
				subject.setYear(cursor.getInt(4));
				subjects.add(subject);
			} while (cursor.moveToNext());
		}
		return subjects;
	}

	// TODO (NANO) Refactorizar y usar Query de Android como en los metodos de
	// arriba.
	public Integer getYearAcademic() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT DISTINCT year FROM " + KEY_TABLE,
				null);
		Integer count = 0;
		if (cursor.moveToFirst()) {
			do {
				count = cursor.getCount();
			} while (cursor.moveToNext());
		}
		return count;
	}
}