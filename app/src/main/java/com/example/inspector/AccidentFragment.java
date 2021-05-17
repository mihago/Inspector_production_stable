
        package com.example.inspector;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.service.autofill.RegexValidator;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;


        public class AccidentFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button AF_next;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccidentFragment() {
        // Required empty public constructor
    }


    public static AccidentFragment newInstance(String param1, String param2) {
        AccidentFragment fragment = new AccidentFragment();
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

        //Spinner accident_type = (Spinner) getView().findViewById(R.id.accident_type);
       // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
              //  R.array.accidents_array, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // accident_type.setAdapter(adapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_accident, container, false);


        Spinner accident_type = (Spinner) v.findViewById(R.id.accident_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.accidents_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accident_type.setAdapter(adapter);
        EditText autonumber= (EditText) v.findViewById(R.id.AF_autonumber);
        EditText definition = (EditText) v.findViewById(R.id.AF_definition);

        autonumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(Pattern.matches("^[а-яА-Я]\\d{3}[а-яА-Я]{2}$",autonumber.getText())){
                        autonumber.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border));

                    }
                    else{
                        autonumber.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
                    }
                }
            }
        });


        AF_next = (Button) v.findViewById(R.id.AF_next);
        AF_next.setOnClickListener(v3 ->{
            if(autonumber.getText().equals("")!=true&&Pattern.matches("^[а-яА-Я]\\d{3}[а-яА-Я]{2}$",autonumber.getText())==true){
                My f = (My) getActivity();
                f.addValue(MainActivity.definition_of_accident,(String) definition.getText().toString());
                f.addValue(MainActivity.autonumber_of_accident, (String) autonumber.getText().toString());

                f.addValue(MainActivity.type_of_accident, accident_type.getSelectedItem().toString() );

                Toast.makeText(getActivity(),accident_type.getSelectedItem().toString()+"   hnj",Toast.LENGTH_LONG).show();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new MediaFragment()).addToBackStack("MediaFragment").commit();
            }
            else{
                autonumber.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
            }

        });




        return v;

    }
}