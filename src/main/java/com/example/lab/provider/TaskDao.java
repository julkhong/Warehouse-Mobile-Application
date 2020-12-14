package com.example.fit2081lab1.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Query("select * from Items")
    LiveData<List<Task>> getAllTasks();

    @Query("delete from Items")
    void deleteAllTask();

    @Query("delete from Items where Name = :newName")
    void deleteTaskByName(String newName);

}
