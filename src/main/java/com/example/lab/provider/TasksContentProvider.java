package com.example.fit2081lab1.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.nio.file.attribute.PosixFileAttributes;

public class TasksContentProvider extends ContentProvider {
    TasksDatabase db;
    public static final String CONTENT_AUTHORITY = "fit2081.app.KHONG";
    public static final Uri CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    private static final int MULTIPLE_ROWS_TASKS = 1;
    private static final int SINGLE_ROW_TASKS = 2;

    private static final int  MULTIPLE_ROWS_USERS = 3;
    private static final int SINGLE_ROW_USERS = 4;

    private static final UriMatcher myMatcher = createUriMatcher();

    private static UriMatcher createUriMatcher(){
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(CONTENT_AUTHORITY, Task.TABLE_NAME, MULTIPLE_ROWS_TASKS);
        uriMatcher.addURI(CONTENT_AUTHORITY,Task.TABLE_NAME+ "/#", SINGLE_ROW_TASKS);

        uriMatcher.addURI(CONTENT_AUTHORITY, "Users", MULTIPLE_ROWS_TASKS);
        uriMatcher.addURI(CONTENT_AUTHORITY, "Users" + "/#", SINGLE_ROW_USERS);

        return uriMatcher;
    }

    public TasksContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count=0;
        int matchId = myMatcher.match(uri);

        switch (matchId){
            case MULTIPLE_ROWS_USERS:
                break;
            case SINGLE_ROW_USERS:
                String idStr = uri.getLastPathSegment();
                break;
        }




        count = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(Task.TABLE_NAME,selection,selectionArgs);


        return count;

    }

    @Override
    public String getType(Uri uri) {
        String returnType = null;
        int uriId = myMatcher.match(uri);
        switch(uriId){
            case MULTIPLE_ROWS_TASKS:
                returnType = "vnd.android.cursor.dit/tasks";
                break;
            case SINGLE_ROW_TASKS:
                returnType = "vnd.android.cursor.item/tasks";
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        return returnType;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri returnUri = null;
        long newId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Task.TABLE_NAME,0,values);
        returnUri = ContentUris.withAppendedId(CONTENT_URI,newId);


        return returnUri;

    }

    @Override
    public boolean onCreate() {
        db = TasksDatabase.getDBInstance(getContext());
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        int matchId = myMatcher.match(uri);

        switch (matchId){
            case MULTIPLE_ROWS_USERS:
                break;
            case SINGLE_ROW_USERS:
                break;
            case MULTIPLE_ROWS_TASKS:
                break;
            case SINGLE_ROW_TASKS:
                break;
        }
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Task.TABLE_NAME);
        String query = builder.buildQuery(projection,selection,null,null,sortOrder,null);
        cursor = db.getOpenHelper().getReadableDatabase().query(query,selectionArgs);


        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        count = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(Task.TABLE_NAME,0,values,selection,selectionArgs);

        return count;
    }
}
