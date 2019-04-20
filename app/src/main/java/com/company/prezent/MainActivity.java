package com.company.prezent;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawer;
    Calendar cal = Calendar.getInstance();
    int time = cal.get(Calendar.HOUR_OF_DAY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);// use tool bar as the actionBar cause we removed the actionBar
        setSupportActionBar(toolbar);



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
            navigationView.setCheckedItem(R.id.nav_home);} //this allows to start the home activity imediately before we click any item

        switch (time){
            case 1: case 2: case 3: case 4: case 18: case 19: case 20: case 21: case 22: case 23: case 0:
                Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();

                drawer.setBackground(getDrawable(R.drawable.cool));
                break;
            case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12:
                Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
                drawer.setBackground(getDrawable(R.drawable.warm));
                break;
            case 13: case 14: case 15: case 16: case 17:
                Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
                drawer.setBackground(getDrawable(R.drawable.warm));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){

            case R.id.nav_timeTable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TimeTableFrag()).commit();
                break;

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
                break;

            case R.id.nav_addReminder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReminderFrag()).commit();
                break;




        }

        drawer.closeDrawer(GravityCompat.START );
        return true;
    }

    @Override
    public void onBackPressed() {// we dont want to leave the activity immediately and just close the drawer
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}
