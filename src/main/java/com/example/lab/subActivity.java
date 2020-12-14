package com.example.fit2081lab1;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import android.content.Context;
import android.view.View;

import com.example.fit2081lab1.provider.Task;
import com.example.fit2081lab1.provider.TasksViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class subActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TasksAdapter adapter;
    private TasksViewModel mTaskViewModel;
    ArrayList<Task> data;
    Task myTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        Intent i = getIntent();
        //onRestore();
        ArrayList<String> new_itemInfo = i.getStringArrayListExtra("list1");
        if (!new_itemInfo.isEmpty()) {
            Task newTask = new Task(new_itemInfo.get(0), new_itemInfo.get(1), new_itemInfo.get(2), new_itemInfo.get(3), new_itemInfo.get(4));
            myTask = newTask;

        }
        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TasksAdapter(null);

        recyclerView.setAdapter(adapter);

        mTaskViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        mTaskViewModel.getAllTasksVM().observe(this, newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();

        });
        mTaskViewModel.insertTaskVM(myTask);

    }


}