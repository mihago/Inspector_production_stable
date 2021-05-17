package com.example.inspector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements My{
    public static final String type_of_accident="accident_type";
    public static  final String definition_of_accident="accident_definition";
    public static  final String autonumber_of_accident= "accident_autonumber";
    public static final String latitude="latitude";
    public static final String longitude="longitude";


    public Bundle bundle =new Bundle();


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView b=(BottomNavigationView) findViewById(R.id.bottom_navigation);
      MaterialToolbar appBarLayout=(MaterialToolbar) findViewById(R.id.topAppBar);
        //TODO: appbar layout navigation icon

        getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new AccidentFragment()).commit();
        appBarLayout.setNavigationOnClickListener(v-> {
            getSupportFragmentManager().popBackStack();

        });
        b.setOnNavigationItemSelectedListener(item -> {
            return true;


        });

}


    @Override
    public void addValue(String key, String value) {
       bundle.putString(key,value);
    }

    @Override
    public String getValue(String key) {
return bundle.getString(key);
    }
}