package com.example.inspector;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.ArrayList;

public interface My {
    public void addValue(String key,String value);
    public Object getValue(String key);
    public  void addValue(String key, ArrayList<Bitmap> array);
    public  ArrayList<Bitmap> getBitmaps(String key);
    public void setBundle(Bundle bundle1);
    public Bundle getBundle();




}
