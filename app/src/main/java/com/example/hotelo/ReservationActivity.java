package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.atomic.AtomicReference;

public class ReservationActivity extends AppCompatActivity {


    private ImageButton eProfile;
    private ImageButton eSearch;
    private ImageButton eHome;
    private ImageButton eReservation;
    private ImageButton eBack;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    final String TAG = "TAG";
    String reservationID;
    TextView reservationStatus,reservationStatus2,reservationStatus3,reservationStatus4,reservationStatus5,reservationStatus6,reservationStatus7,reservationStatus8,reservationStatus9;
    String id;
    int number;
    TextView nbRes;
    ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        eProfile = findViewById(R.id.buttonProfile2);
        eSearch = findViewById(R.id.buttonSearch2);
        eHome = findViewById(R.id.buttonHome2);
        eReservation = findViewById(R.id.buttonReservation2);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        reservationStatus = findViewById(R.id.tvReservationStatus);
        reservationStatus2 = findViewById(R.id.tvReservationStatus2);
        reservationStatus3 = findViewById(R.id.tvReservationStatus3);
        reservationStatus4 = findViewById(R.id.tvReservationStatus4);
        reservationStatus5 = findViewById(R.id.tvReservationStatus5);
        reservationStatus6 = findViewById(R.id.tvReservationStatus6);
        reservationStatus7 = findViewById(R.id.tvReservationStatus7);
        reservationStatus8 = findViewById(R.id.tvReservationStatus8);
        reservationStatus9 = findViewById(R.id.tvReservationStatus9);


        nbRes = findViewById(R.id.tvNumberOfRes);

        goBack = findViewById(R.id.buttonGoBack2);



        eProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, ProfileActivity.class));
            }
        });
        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, SearchActivity.class));
            }
        });
        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, WelcomeActivity.class));
            }
        });
        eReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, ReservationActivity.class));

            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, WelcomeActivity.class));

            }
        });

        id = fAuth.getCurrentUser().getUid();


        fStore.collection("reservations").whereEqualTo("użytkownik", id).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    number++;
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    nbRes.setText("Liczba rezerwacji: " + number);
                                    if (number == 1){
                                        reservationStatus.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 2){
                                        reservationStatus2.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 3){
                                        reservationStatus3.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 4){
                                        reservationStatus4.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 5){
                                        reservationStatus5.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 6){
                                        reservationStatus6.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 7){
                                        reservationStatus7.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 8){
                                        reservationStatus8.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                    }
                                    if (number == 9){
                                        reservationStatus9.setText("Rezerwacja" + number+": Hotel:"+document.getString("nazwa")+", miejscowość:"
                                                +document.getString("miejscowość")+", adres:"+document.getString("adres")+", od:"+document.getString("od")+
                                                ", do:"+document.getString("do")+", pokoje:"+document.getString("pokoje")+", dzieci:"+
                                                document.getString("dzieci")+", dorośli:"+document.getString("dorośli"));
                                                //.setText("Rezerwacja" + number+": "+document.getData().toString());
                                    }

                                }
                            } else {
                                Log.d(TAG, "Błąd", task.getException());
                            }
                        }
                    });

        /*DocumentReference docRef = fStore.collection("reservations").document(reservationID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Rezerwacje: " + document.getData());
                    } else {
                        Log.d(TAG, "Brak");
                    }
                } else {
                    Log.d(TAG, "Błąd ", task.getException());
                }
            }
        });

         */

        /*AtomicReference<String> helper = null;
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, (value, error) -> {
            helper.set(value.getString("email"));
        });

        fStore.collection("reservations").whereEqualTo("email", helper).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Błąd ", task.getException());
                        }
                    }
                });

         */
    }
}