package com.example.inspector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.MailTo;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        View v =  inflater.inflate(R.layout.fragment_contact, container, false);
        EditText FIO = (EditText) v.findViewById(R.id.CF_FIO);
        EditText Email = (EditText) v.findViewById(R.id.CF_email);
        EditText phone = (EditText) v.findViewById(R.id.CF_phone);
        FIO.setOnFocusChangeListener((v1, hasFocus) -> {
            if(!hasFocus) {
                if(Pattern.matches("^*\\t*",FIO.getText())){
                    FIO.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border));

                }
                else{
                   FIO.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
                }
            }
        });
        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(Pattern.matches("^[a-zA-z]+@[a-zA-z]+[.][a-zA-z]+$",Email.getText())){
                        Email.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border));

                    }
                    else{
                       Email.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
                    }
                }
            }
        });
       phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(Pattern.matches("^[+\\d]\\d{11}$",phone.getText())){
                        phone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border));

                    }
                    else{
                       phone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
                    }
                }
            }
        });
        Button CF_next = (Button) v.findViewById(R.id.CF_next);
        CF_next.setOnClickListener(v4->{
            if(Pattern.matches("^[a-zA-Z]{2,30}$",FIO.getText())==true){
                if(Pattern.matches("^[+\\d]\\d{11}$", phone.getText())==true){
                    My f=(My) getActivity();
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("plain/text");
                   intent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            new String[] { "mihago2004@gmail.com" });
                   intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                            "InspectorApp");
                   String text="Здравствуйте! Обнаружил следующее нарушение" +f.getValue(MainActivity.type_of_accident)+f.getValue(MainActivity.definition_of_accident)+
                           "\n"+
                           "на этом месте "+ f.getValue(MainActivity.latitude)+" "+f.getValue(MainActivity.longitude)+". \n"+
                          "Номер машины правонарушителя " +f.getValue(MainActivity.autonumber_of_accident)+"\n"+

                           "Мои контактные данные \n ФИО: "+FIO.getText().toString()+
                           "\nЭл.почта:" + Email.getText().toString()+
                           "\n Номер телефона: "+ phone.getText().toString()
                           ;
                   intent.putExtra(android.content.Intent.EXTRA_TEXT,
                          text);
                    ContactFragment.this.startActivity(Intent.createChooser(intent,"Отправка Письма"));



                }
                else{
                    phone.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
                }


            }
            else{
                FIO.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.border_red));
            }
        });





        // Inflate the layout for this fragment
        return v;
    }
}