package com.osymbah.aigovernancereadiness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AssessmentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quickcheck_ai.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "assessments";

    public AssessmentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "system_name TEXT NOT NULL," +
                "system_purpose TEXT NOT NULL," +
                "organization TEXT NOT NULL," +
                "created_at INTEGER NOT NULL," +
                "responses TEXT NOT NULL," +
                "security_score INTEGER NOT NULL," +
                "privacy_score INTEGER NOT NULL," +
                "fairness_score INTEGER NOT NULL," +
                "transparency_score INTEGER NOT NULL," +
                "accountability_score INTEGER NOT NULL," +
                "oversight_score INTEGER NOT NULL," +
                "overall_score INTEGER NOT NULL," +
                "readiness_level TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

    public long insert(AssessmentRecord record) {
        return getWritableDatabase().insert(TABLE, null, valuesFor(record));
    }

    public List<AssessmentRecord> getAll() {
        ArrayList<AssessmentRecord> records = new ArrayList<>();
        try (Cursor cursor = getReadableDatabase().query(
                TABLE, null, null, null, null, null, "created_at DESC")) {
            while (cursor.moveToNext()) {
                records.add(fromCursor(cursor));
            }
        }
        return records;
    }

    public AssessmentRecord getById(long id) {
        try (Cursor cursor = getReadableDatabase().query(
                TABLE, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null)) {
            if (cursor.moveToFirst()) {
                return fromCursor(cursor);
            }
        }
        return null;
    }

    private ContentValues valuesFor(AssessmentRecord record) {
        ContentValues values = new ContentValues();
        values.put("system_name", record.getSystemName());
        values.put("system_purpose", record.getSystemPurpose());
        values.put("organization", record.getOrganization());
        values.put("created_at", record.getCreatedAt());
        values.put("responses", record.getResponses());
        values.put("security_score", record.getSecurityScore());
        values.put("privacy_score", record.getPrivacyScore());
        values.put("fairness_score", record.getFairnessScore());
        values.put("transparency_score", record.getTransparencyScore());
        values.put("accountability_score", record.getAccountabilityScore());
        values.put("oversight_score", record.getOversightScore());
        values.put("overall_score", record.getOverallScore());
        values.put("readiness_level", record.getReadinessLevel());
        return values;
    }

    private AssessmentRecord fromCursor(Cursor cursor) {
        return new AssessmentRecord(
                cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("system_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("system_purpose")),
                cursor.getString(cursor.getColumnIndexOrThrow("organization")),
                cursor.getLong(cursor.getColumnIndexOrThrow("created_at")),
                cursor.getString(cursor.getColumnIndexOrThrow("responses")),
                cursor.getInt(cursor.getColumnIndexOrThrow("security_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("privacy_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("fairness_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("transparency_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("accountability_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("oversight_score")),
                cursor.getInt(cursor.getColumnIndexOrThrow("overall_score")),
                cursor.getString(cursor.getColumnIndexOrThrow("readiness_level")));
    }
}
