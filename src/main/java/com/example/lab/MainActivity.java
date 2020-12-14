package com.example.fit2081lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.ToggleButton;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    private EditText itemName;
    private EditText quantity;
    private EditText cost;
    private EditText description;
    private ToggleButton frozen;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolBar;
    private FloatingActionButton fabBtn;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private Button btn_add_item;
    ArrayList<Item> data = new ArrayList<>();
    ArrayList<String> itemInfo = new ArrayList<String>();

    private float x1,x2;
    static final int MIN_DISTANCE = 150;
    private static final String DEBUG_TAG = "WEEK10_TAG";
    private View myView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);
        itemName = (EditText) findViewById(R.id.itemID);
        quantity = (EditText) findViewById(R.id.quantityID);
        cost = (EditText) findViewById(R.id.costID);
        description = (EditText) findViewById(R.id.descriptionID);
        frozen = (ToggleButton) findViewById(R.id.frozenID);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolBar = findViewById(R.id.toolbar);
        fabBtn = findViewById(R.id.fab);
        myView = findViewById(R.id.activity_layout);
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = itemName.getText().toString();
                saveSharedPreferences();
                Snackbar.make(view, "'"+ name + "'" + " " + "has been succesfully added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        restoreSharedPreferences();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, new IntentFilter(SMSReceiverWeek4.SMS_FILTER));
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();
                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        x1 = event.getX();
                        return true;
                    case (MotionEvent.ACTION_MOVE) :
                        return true;
                    case (MotionEvent.ACTION_UP) :
                        x2 = event.getX();
                        double difference = x2 - x1;
                        if(Math.abs(difference)>MIN_DISTANCE){
                            if(difference>0){
                                addItemNav();
                                addItem_menu();
                            }
                            else if (difference<0){
                                clearItem_menu();
                            }
                        }
                        return true;
                    default :
                        return false;
                }
            }
        });


    }







    public void addItemNav(){
        String nav_itemName = itemName.getText().toString();
        String nav_quantity = quantity.getText().toString();
        String nav_cost = cost.getText().toString();
        String nav_description = description.getText().toString();
        String nav_frozen = frozen.getText().toString();
        itemInfo.add(nav_itemName);
        itemInfo.add(nav_quantity);
        itemInfo.add(nav_cost);
        itemInfo.add(nav_description);
        itemInfo.add(nav_frozen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id == R.id.option_add) {
            addItemNav();
            addItem_menu();
        } else if (id == R.id.option_clear) {
            clearItem_menu();
        }
        return true;
    }



    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();

            if (id == R.id.nav_add) {
                addItemNav();
                addItem_menu();

            } else if (id == R.id.nav_clear) {
                clearItem_menu();
            } else if (id == R.id.nav_list_all_item) {
                Intent myIntent = new Intent(MainActivity.this,subActivity.class);
                myIntent.putStringArrayListExtra("list1", itemInfo);
                startActivity(myIntent);
                itemInfo.clear();
            }
            // close the drawer
            drawerLayout.closeDrawers();
            // tell the OS
            return true;
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context,Intent intent){
            String myMsg = intent.getStringExtra(SMSReceiverWeek4.SMS_MSG_KEY);
            StringTokenizer myST = new StringTokenizer(myMsg,";");
            String sms_itemName = myST.nextToken();
            String sms_quantity = myST.nextToken();
            String sms_cost = myST.nextToken();
            String sms_description = myST.nextToken();
            String sms_frozen= myST.nextToken();
            if(sms_frozen.equals("true")){
                frozen.setChecked(true);
            }
            else{
                frozen.setChecked(false);
            }

            itemName.setText(sms_itemName);
            quantity.setText(sms_quantity);
            cost.setText(sms_cost);
            description.setText(sms_description);



        }


    }
    public void addItem_menu(){
        String name = itemName.getText().toString();

        Toast show_message = Toast.makeText(this,"'"+ name + "'" + " " + "has been succesfully added",
                Toast.LENGTH_SHORT);
        show_message.show();
        saveSharedPreferences();

    }
    public void clearItem_menu(){
        itemName.setText("");
        quantity.setText("");
        cost.setText("");
        description.setText("");
        frozen.setChecked(false);
        SharedPreferences mySp = getSharedPreferences("mySp file01",0);
        SharedPreferences.Editor editMYSP = mySp.edit();
        editMYSP.clear().apply();
    }

    public void addItem(View v)
    {   addItemNav();
        String name = itemName.getText().toString();

        Toast show_message = Toast.makeText(this,"'"+ name + "'" + " " + "has been succesfully added",
                Toast.LENGTH_SHORT);
        show_message.show();
        saveSharedPreferences();
    }
    public void clearButton(View v) {
        itemName.setText("");
        quantity.setText("");
        cost.setText("");
        description.setText("");
        frozen.setChecked(false);
        SharedPreferences mySp = getSharedPreferences("mySp file01",0);
        SharedPreferences.Editor editMYSP = mySp.edit();
        editMYSP.clear().apply();




    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("itemName_view",itemName.getText().toString());
        outState.putString("quantity_view",quantity.getText().toString());
        outState.putString("cost_view",cost.getText().toString());
        outState.putString("description_view",description.getText().toString());





    }
    @Override
    //only gets executed if inState != null so no need to check for null Bundle
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        itemName.setText(inState.getString("itemName_view"));
        quantity.setText(inState.getString("quantity_view"));
        cost.setText(inState.getString("cost_view"));
        description.setText(inState.getString("description_view"));






    }

    private void saveSharedPreferences(){
        SharedPreferences mySp = getSharedPreferences("mySp file01",0);
        SharedPreferences.Editor editMYSP = mySp.edit();
        editMYSP.putString("itemKey",itemName.getText().toString());
        editMYSP.putString("quantityKey",quantity.getText().toString());
        editMYSP.putString("costKey",cost.getText().toString());
        editMYSP.putString("descriptionKey",description.getText().toString());
        editMYSP.apply();
        if (frozen.isChecked())
        {
            editMYSP.putBoolean("frozenState", true);
            editMYSP.apply();
        }
        else{
            editMYSP.putBoolean("frozenState", false);
            editMYSP.apply();
        }



    }

    private void restoreSharedPreferences(){
        SharedPreferences mySp = getSharedPreferences("mySp file01",0);
        itemName.setText(mySp.getString("itemKey",""));
        quantity.setText(mySp.getString("quantityKey",""));
        cost.setText(mySp.getString("costKey",""));
        description.setText(mySp.getString("descriptionKey",""));
        frozen.setChecked(mySp.getBoolean("frozenState", false));



    }


}
