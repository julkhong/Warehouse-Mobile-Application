package com.example.fit2081lab1.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksRepository {
    private TaskDao taskDao;
    LiveData<List<Task>> tasks;

    public TasksRepository(Application app) {
        TasksDatabase db = TasksDatabase.getDBInstance(app);
        taskDao = db.taskDao();
        tasks = taskDao.getAllTasks();
    }
    LiveData<List<Task>> getAllTasksRepo(){
        return taskDao.getAllTasks();
    }
    void insertTaskRepo(Task task){
        TasksDatabase .dbWrite.execute(()-> taskDao.insertTask(task));
    }
    void deleteAllTasksRepo(){
        TasksDatabase.dbWrite.execute(()-> taskDao.deleteAllTask());
    }
    void deleteTaskByName(String myName){
        TasksDatabase.dbWrite.execute(()-> taskDao.deleteTaskByName(myName));
    }
}
