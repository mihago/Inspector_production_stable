package com.example.inspector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements My{
    public static final String type_of_accident="accident_type";
    public static  final String definition_of_accident="accident_definition";
    public static  final String autonumber_of_accident= "accident_autonumber";
    public static final String latitude="latitude";
    public static final String longitude="longitude";
    public static final String photoarray="photoarray";


    public Bundle bundle =new Bundle();


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("my",bundle);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       Bundle r= savedInstanceState.getBundle("my");
       if(r!=null){
           getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new MediaFragment()).commit();


       }
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

    @Override
    public void addValue(String key, ArrayList<Bitmap> array) {
        for(int l=0;l<array.size();l++){
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            array.get(l).compress(Bitmap.CompressFormat.PNG, 100, stream);

            bundle.putByteArray(key+""+l,stream.toByteArray());
        }

    }

    public void setBundle(Bundle bundle1){
        this.bundle = bundle1;
    }
public Bundle getBundle(){
        return bundle;
    }
    @Override
    public ArrayList<Bitmap> getBitmaps(String key) {
       int length= Integer.parseInt(getValue(photoarray));
        ArrayList<Bitmap> array = new ArrayList<Bitmap>();
        for(int l=0;l< length;l++){
          byte[] data=  bundle.getByteArray(key+""+l);
          Log.d("ggg",data.toString());
          Bitmap bitmap =BitmapFactory.decodeByteArray(data,0, data.length);
            array.add(bitmap);
        }
        return array;
    }
}