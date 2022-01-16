package com.example.hotelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HotelActivity6 extends AppCompatActivity {

    Button btReser;
    ImageButton btGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel6);

        btReser = findViewById(R.id.buttonReser6);
        btGoBack = findViewById(R.id.buttonGoBackHotel6);

        btReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity6.this, BookActivity6.class));

            }
        });
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity6.this, WelcomeActivity.class));
            }
        });
    }
}