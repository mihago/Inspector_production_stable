package com.example.inspector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView b=(BottomNavigationView) findViewById(R.id.bottom_navigation);
        //TODO: appbar layout navigation icon

        getSupportFragmentManager().beginTransaction().add(R.id.fragments_container,new AccidentFragment(),"accident").commit();


        b.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getSupportFragmentManager().beginTransaction().к
               return true;
            }
        });



        
    }
}