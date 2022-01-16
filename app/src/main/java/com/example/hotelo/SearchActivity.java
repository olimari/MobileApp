package com.example.hotelo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    private ImageButton eProfile;
    private ImageButton  eSearch;
    private ImageButton eHome;
    private ImageButton eReservation;

    Button eSearchAll;

    EditText eSearLocation;
    private EditText eSearBeginning;
    private EditText eSearEnd;
    private EditText eSearNumberOfGuests;

    ImageButton goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        eProfile = findViewById(R.id.buttonProfile4);
        eSearch = findViewById(R.id.buttonSearch4);
        eHome = findViewById(R.id.buttonHome4);
        eReservation = findViewById(R.id.buttonReservation4);
        eSearchAll = findViewById(R.id.buttonSearchAll);

        eSearLocation = findViewById(R.id.editLocation);
        eSearBeginning = findViewById(R.id.editBeginning);
        eSearEnd= findViewById(R.id.editEnd);
        eSearNumberOfGuests= findViewById(R.id.editGuests);

        goBack = findViewById(R.id.buttonGoBack4);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, WelcomeActivity.class));
            }
        });
        eProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
            }
        });
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, SearchActivity.class));
            }
        });
        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, WelcomeActivity.class));
            }
        });
        eReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ReservationActivity.class));

            }
        });
        eSearchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = eSearLocation.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                intent.putExtra("message", str);
                startActivity(intent);

            }
        });

    }
}