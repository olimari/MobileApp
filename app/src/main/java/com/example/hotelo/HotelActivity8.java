package com.example.hotelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HotelActivity8 extends AppCompatActivity {

    Button btReser;
    ImageButton btGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel8);

        btReser = findViewById(R.id.buttonReser8);
        btGoBack = findViewById(R.id.buttonGoBackHotel8);

        btReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity8.this, BookActivity8.class));

            }
        });
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity8.this, WelcomeActivity.class));
            }
        });
    }
}