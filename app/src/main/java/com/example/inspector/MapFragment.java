package com.example.inspector;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,GoogleMap.OnMyLocationClickListener,GoogleMap.OnMyLocationButtonClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean permissionDenied = false;
    public boolean isCameraMoved=false;
    GoogleMap map;
    double latitude=0;
    double longitude=0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Button MF_next = (Button) v.findViewById(R.id.MF_next);

     SupportMapFragment mapFragment;
        mapFragment = SupportMapFragment.newInstance();

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container,mapFragment).commit();

        mapFragment.getMapAsync(this);

        // Inflate the layout for this fragment
        MF_next.setOnClickListener(v3 ->{
            if(isCameraMoved==false){
            latitude=map.getCameraPosition().target.latitude;
            longitude =map.getCameraPosition().target.longitude;
            Toast.makeText(getContext(),"" +latitude+longitude,Toast.LENGTH_LONG).show();}

                My f=(My) getActivity();
                f.addValue(MainActivity.latitude, ""+ latitude);
                f.addValue(MainActivity.longitude, ""+ longitude);
                Toast.makeText(getContext(),"" +latitude+"   "+ longitude,Toast.LENGTH_LONG).show();


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new ContactFragment()).addToBackStack("ContactFragment").commit();





        });
        return v;
    }
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (map != null) {
                map.setMyLocationEnabled(true);
                map.setOnMyLocationButtonClickListener(this);
                map.setOnMyLocationClickListener(this);






            }
        } else {

            // Permission to access the location is missing. Show rationale and request permission
          PermissionUtils.requestPermission((AppCompatActivity) getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                 Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }
    @Override

    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;

        enableMyLocation();

    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        latitude=map.getCameraPosition().target.latitude;
        longitude =map.getCameraPosition().target.longitude;
        Toast.makeText(getContext(),"" +latitude+longitude,Toast.LENGTH_LONG).show();
        return false;

    }

    @Override
    public void onCameraMoveStarted(int i) {
        if(map.isMyLocationEnabled()){
            latitude=map.getCameraPosition().target.latitude;
            longitude =map.getCameraPosition().target.longitude;
            Toast.makeText(getContext(),"" +latitude+longitude,Toast.LENGTH_LONG).show();
            isCameraMoved=true;
        }else{

        }


    }
}