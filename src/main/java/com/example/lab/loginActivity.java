package com.example.fit2081lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = (EditText)findViewById(R.id.name);
        Password = (EditText) findViewById(R.id.password);

    }

    public void onClick(View v){
        boolean check = false;
        if(Name.getText().toString().equals("admin")){
            if(Password.getText().toString().equals("admin123")){
                check = true;
                Toast.makeText(getApplicationContext(),"Welcome admin",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        }
        if(!check){
            Toast.makeText(getApplicationContext(),"sign in failed",Toast.LENGTH_SHORT).show();
        }


    }
}
