package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText profileName, profileSurname, profileEmail, profilePhone;
    ImageView profilePic;
    Button buttonSave;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent data = getIntent();
        String name = data.getStringExtra("imię");
        String surname = data.getStringExtra("nazwisko");
        String email = data.getStringExtra("email");
        String phone = data.getStringExtra("telefon");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        profileName = findViewById(R.id.editProfileName);
        profileSurname = findViewById(R.id.editProfileSurname);
        profileEmail = findViewById(R.id.editProfileEmail);
        profilePhone = findViewById(R.id.editProfilePhone);
        buttonSave = findViewById(R.id.buttonEditProfile);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditActivity.this, "Zdjęcie zostało zmienione.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profileName.getText().toString().isEmpty() || profileSurname.getText().toString().isEmpty() || profileEmail.getText().toString().isEmpty() || profilePhone.getText().toString().isEmpty()){
                    Toast.makeText(EditActivity.this, "Wszystkie pola muszą być uzupełnione.",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email = profileEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("imię", profileName.getText().toString());
                        edited.put("nazwisko",profileSurname.getText().toString());
                        edited.put("telefon",profilePhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditActivity.this, "Profil został zaktualizowany.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditActivity.this,"E-mail został zmieniony.",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditActivity.this, "Błąd.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        profileEmail.setText(email);
        profilePhone.setText(phone);
        profileName.setText(name);
        profileSurname.setText(surname);

        Log.d(TAG,name + " "+ surname +" "+email+" "+phone);
    }
}