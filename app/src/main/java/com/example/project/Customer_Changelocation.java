package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Customer_Changelocation extends AppCompatActivity {
    private Toolbar actionbar_Choose;

    private Button btnregister;

    private Spinner spinnerCity;
    private Spinner spinnerDistrict;

    DatabaseReference databaseReference;

    ArrayList<String> arraylistCity;
    ArrayAdapter<String> arrayAdapter_City;
    ArrayList<String > arrayListİstanbul,arrayListAnkara,arrayListMuğla,arrayListBursa,arrayListChoose;
    ArrayAdapter<String> arrayAdapter_District;

    private FirebaseAuth auth;


    public void init(){
        actionbar_Choose = (Toolbar) findViewById(R.id.action_barchangelocation);
        setSupportActionBar(actionbar_Choose);
        getSupportActionBar().setTitle("Choose City");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelocation);
        init();
        spinnerCity = (Spinner) findViewById(R.id.spnCity);

        spinnerDistrict = (Spinner) findViewById(R.id.distirict);

        arraylistCity = new ArrayList<>();
        arraylistCity.add("Choose City");
        arraylistCity.add("İstanbul");
        arraylistCity.add("Ankara");
        arraylistCity.add("Muğla");
        arraylistCity.add("Bursa");


        arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(),R.layout.textview_orange,arraylistCity);

        spinnerCity.setAdapter(arrayAdapter_City);


        //========= District spinner  process starts=========
        arrayListChoose = new ArrayList<>();
        arrayListChoose.add("District");


        arrayListİstanbul = new ArrayList<>();
        arrayListİstanbul.add("Beşiktaş");
        arrayListİstanbul.add("Kadıköy");
        arrayListİstanbul.add("Avcılar");


        arrayListAnkara = new ArrayList<>();
        arrayListAnkara.add("Kızılay");
        arrayListAnkara.add("Ulus");
        arrayListAnkara.add("Bahçelievler");


        arrayListMuğla = new ArrayList<>();
        arrayListMuğla.add("Menteşe");
        arrayListMuğla.add("Marmaris");
        arrayListMuğla.add("Fethiye");

        arrayListBursa = new ArrayList<>();
        arrayListBursa.add("Nilüfer");
        arrayListBursa.add("Yenişehir");
        arrayListBursa.add("Mudanya");



        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapter_District = new ArrayAdapter<>(getApplicationContext(),R.layout.textview_pink,arrayListChoose);
                }

                if(position==1){
                    arrayAdapter_District = new ArrayAdapter<>(getApplicationContext(),R.layout.textview_pink,arrayListİstanbul);
                }
                if(position==2){
                    arrayAdapter_District = new ArrayAdapter<>(getApplicationContext(),R.layout.textview_pink,arrayListAnkara);
                }
                if(position==3){
                    arrayAdapter_District = new ArrayAdapter<>(getApplicationContext(),R.layout.textview_pink,arrayListMuğla);
                }
                if(position==4) {
                    arrayAdapter_District = new ArrayAdapter<>(getApplicationContext(), R.layout.textview_pink, arrayListBursa);
                }

                spinnerDistrict.setAdapter(arrayAdapter_District);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
