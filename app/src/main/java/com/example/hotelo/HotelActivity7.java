package com.example.hotelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HotelActivity7 extends AppCompatActivity {

    Button btReser;
    ImageButton btGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel7);

        btReser = findViewById(R.id.buttonReser7);
        btGoBack = findViewById(R.id.buttonGoBackHotel7);

        btReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity7.this, BookActivity7.class));

            }
        });
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity7.this, WelcomeActivity.class));
            }
        });
    }
}