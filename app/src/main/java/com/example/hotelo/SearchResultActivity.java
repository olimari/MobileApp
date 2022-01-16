package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class SearchResultActivity extends AppCompatActivity {

    private ImageButton eProfile;
    private ImageButton  eSearch;
    private ImageButton eHome;
    private ImageButton eReservation;


    TextView noResults, searched;
    ImageButton btHotel1, btHotel2, btHotel3, btHotel4, btHotel5, btHotel6, btHotel7, btHotel8, btHotel9;

    ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        eProfile = findViewById(R.id.buttonProfile5);
        eSearch = findViewById(R.id.buttonSearch5);
        eHome = findViewById(R.id.buttonHome5);
        eReservation = findViewById(R.id.buttonReservation5);

        noResults = findViewById(R.id.tvNoResults);
        btHotel1 = findViewById(R.id.btSearchHotel1);
        btHotel2 = findViewById(R.id.btSearchHotel2);
        btHotel3 = findViewById(R.id.btSearchHotel3);
        btHotel4 = findViewById(R.id.btSearchHotel4);
        btHotel5 = findViewById(R.id.btSearchHotel5);
        btHotel6 = findViewById(R.id.btSearchHotel6);
        btHotel7 = findViewById(R.id.btSearchHotel7);
        btHotel8 = findViewById(R.id.btSearchHotel8);
        btHotel9 = findViewById(R.id.btSearchHotel9);
        searched = findViewById(R.id.tvSearched);

        goBack = findViewById(R.id.buttonGoBack3);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, SearchActivity.class));
            }
        });
        eProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, ProfileActivity.class));
            }
        });
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, SearchActivity.class));
            }
        });
        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, WelcomeActivity.class));
            }
        });
        eReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, ReservationActivity.class));

            }
        });
        btHotel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity1.class));
            }
        });
        btHotel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity2.class));
            }
        });
        btHotel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity3.class));
            }
        });
        btHotel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity4.class));
            }
        });
        btHotel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity5.class));
            }
        });
        btHotel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity6.class));
            }
        });
        btHotel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity7.class));
            }
        });
        btHotel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity8.class));
            }
        });
        btHotel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HotelActivity9.class));
            }
        });


        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        searched.setText(str);

        if (str.equalsIgnoreCase("warszawa")){
            btHotel1.setVisibility(View.VISIBLE);
            btHotel3.setVisibility(View.VISIBLE);
            return;
        }
        if (str.equalsIgnoreCase("balice")){
            btHotel2.setVisibility(View.VISIBLE);
            return;
        }
        if (str.equalsIgnoreCase("kraków")){
            btHotel4.setVisibility(View.VISIBLE);
            return;
        }
        if (str.equalsIgnoreCase("gdynia")){
            btHotel5.setVisibility(View.VISIBLE);
            btHotel6.setVisibility(View.VISIBLE);
            return;
        }
        if (str.equalsIgnoreCase("sopot")){
            btHotel7.setVisibility(View.VISIBLE);
            btHotel8.setVisibility(View.VISIBLE);
            return;
        }
        if (str.equalsIgnoreCase("mielno")){
            btHotel9.setVisibility(View.VISIBLE);
            return;
        }

        noResults.setVisibility(View.VISIBLE);


    }
}