package com.example.inspector;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView b=(BottomNavigationView) findViewById(R.id.bottom_navigation);
      MaterialToolbar appBarLayout=(MaterialToolbar) findViewById(R.id.topAppBar);
        //TODO: appbar layout navigation icon

        getSupportFragmentManager().beginTransaction().add(R.id.fragments_container,new AccidentFragment()).commit();
        appBarLayout.setNavigationOnClickListener(v-> {
            getSupportFragmentManager().popBackStack();

        });
        b.setOnNavigationItemSelectedListener(item -> {
            return true;


        });
}}