package com.example.hotelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HotelActivity1 extends AppCompatActivity {

    Button btReser;
    ImageButton btGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel1);

        btReser = findViewById(R.id.buttonReser1);
        btGoBack = findViewById(R.id.buttonGoBackHotel1);

        btReser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "hotel1";
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                intent.putExtra("msg", str);
                startActivity(intent);
                startActivity(new Intent(HotelActivity1.this, ReservateActivity.class));

            }
        });
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotelActivity1.this, WelcomeActivity.class));
            }
        });

    }
}