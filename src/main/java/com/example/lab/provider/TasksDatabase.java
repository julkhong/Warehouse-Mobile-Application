package com.example.fit2081lab1.provider;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@Database(entities ={Task.class},version = 2)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();
    public static final String DATABASE_NAME="Items_db";

    private static volatile TasksDatabase instance;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService dbWrite = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static TasksDatabase getDBInstance(final Context context){
        if(instance == null) {
            synchronized (TasksDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext()
                            , TasksDatabase.class,
                            DATABASE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }


        return instance;
    }

}
