package com.example.hotelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    ImageButton eProfile;
    ImageButton eSearch;
    ImageButton eHome;
    ImageButton eReservation;

    TextView tvSearch;
    EditText etLocation;

    ImageButton btHotel1, btHotel2,btHotel3,btHotel4,btHotel5,btHotel6,btHotel7,btHotel8,btHotel9;

    ImageButton eLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        eProfile = findViewById(R.id.buttonProfile);
        eSearch = findViewById(R.id.buttonSearch);
        eHome = findViewById(R.id.buttonHome);
        eReservation = findViewById(R.id.buttonReservation);

        tvSearch = findViewById(R.id.textSearchMore);
        etLocation = findViewById(R.id.editTextLocation);

        btHotel1 = findViewById(R.id.hotel1);
        btHotel2 = findViewById(R.id.hotel2);
        btHotel3 = findViewById(R.id.hotel3);
        btHotel4 = findViewById(R.id.hotel4);
        btHotel5 = findViewById(R.id.hotel5);
        btHotel6 = findViewById(R.id.hotel6);
        btHotel7 = findViewById(R.id.hotel7);
        btHotel8 = findViewById(R.id.hotel8);
        btHotel9 = findViewById(R.id.hotel9);

        eLocation = findViewById(R.id.btWelcomeSearch);


        eProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, ProfileActivity.class));
            }
        });
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, SearchActivity.class));
            }
        });
        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, WelcomeActivity.class));
            }
        });
        eReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, ReservationActivity.class));

            }
        });
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, SearchActivity.class));
            }
        });

        btHotel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity1.class));

            }
        });
        btHotel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity2.class));

            }
        });
        btHotel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity3.class));

            }
        });
        btHotel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity4.class));

            }
        });
        btHotel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity5.class));

            }
        });
        btHotel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity6.class));

            }
        });
        btHotel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity7.class));

            }
        });
        btHotel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity8.class));

            }
        });
        btHotel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, HotelActivity9.class));

            }
        });

        eLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = etLocation.getText().toString();

                if(loc.isEmpty()) {
                    Toast.makeText(WelcomeActivity.this, "Proszę wpisać lokalizację.", Toast.LENGTH_LONG).show();

                }
                else {
                    startActivity(new Intent(WelcomeActivity.this, SearchResultActivity.class));
                }
            }
        });
    }
}