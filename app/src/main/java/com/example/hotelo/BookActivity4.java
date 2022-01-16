package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookActivity4 extends AppCompatActivity {
    private Button eReservate;

    private EditText eRooms;
    private EditText eAdults;
    private EditText eKids;
    private EditText eBeg;
    private EditText eEnd;

    FirebaseFirestore fStore;
    String reservationID = "reservationID4";
    String id;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book4);

        eReservate = findViewById(R.id.buttonResDone4);
        eRooms = findViewById(R.id.editRooms4);
        eAdults = findViewById(R.id.editAdults4);
        eKids = findViewById(R.id.editKids4);
        eEnd = findViewById(R.id.editResEnd4);
        eBeg = findViewById(R.id.editResBeg4);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        eReservate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String begin = eBeg.getText().toString();
                String end = eEnd.getText().toString();
                String numberOfRooms = eRooms.getText().toString();
                String numberOfAdults = eAdults.getText().toString();
                String numberOfKids = eKids.getText().toString();

                if(numberOfAdults.isEmpty() || numberOfKids.isEmpty() || numberOfRooms.isEmpty() || begin.isEmpty() || end.isEmpty()) {

                    Toast.makeText(BookActivity4.this, "Proszę uzupełnić luki.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (begin.length()!=8 || end.length()!=8){
                    Toast.makeText(BookActivity4.this, "Proszę poprawnie wprowadzić daty.", Toast.LENGTH_LONG).show();
                    return;
                }
                if (begin.equals(end)){
                    Toast.makeText(BookActivity4.this, "Proszę poprawnie wprowadzić daty.", Toast.LENGTH_LONG).show();
                    return;
                }

                id = fAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("reservations").document(reservationID);
                Map<String, Object> data1 = new HashMap<>();
                data1.put("nazwa", "Delta");
                data1.put("miejscowość", "Kraków");
                data1.put("adres", "Mazowiecka 6, 30-036 Kraków");
                data1.put("od", begin);
                data1.put("do", end);
                data1.put("pokoje", numberOfRooms);
                data1.put("dorośli", numberOfAdults);
                data1.put("dzieci", numberOfKids);
                data1.put("użytkownik",id);
                documentReference.set(data1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "Zarezerwowano: " + reservationID);
                        startActivity(new Intent(BookActivity4.this, ReservationActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG","onFailure: " + e.toString());
                    }
                });


                //Toast.makeText(BookActivity4.this, "Zarezerwowano", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(BookActivity4.this, ReservationActivity.class));



            }
        });
    }
}