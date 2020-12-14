package com.example.fit2081lab1.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {
    TasksRepository tasksRepository;
    LiveData<List<Task>> myTasks;

    public TasksViewModel(@NonNull Application app){
        super(app);

        tasksRepository = new TasksRepository(app);
        myTasks = tasksRepository.getAllTasksRepo();

    }
    public LiveData<List<Task>> getAllTasksVM(){
        return myTasks;
    }
    public void insertTaskVM(Task task){
        tasksRepository.insertTaskRepo(task);
    }
    public void deleteTaskByNameVM(String newName){
        tasksRepository.deleteTaskByName(newName);
    }
}
