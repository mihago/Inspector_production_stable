package com.example.inspector;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


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


        AF_next = (Button) v.findViewById(R.id.AF_next);
        AF_next.setOnClickListener(v3 ->{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new MediaFragment(),"media").commit();

        });

        return v;

    }
}