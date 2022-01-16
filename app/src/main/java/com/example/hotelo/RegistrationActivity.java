package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText eName;
    EditText eSurname;
    EditText ePassword;
    EditText eEmail;
    EditText ePhone;
    Button buttonRegister;
    ProgressBar progressBar;

    FirebaseFirestore fStore;
    String userID;

    final String TAG = "TAG";

    ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        eEmail = findViewById(R.id.editRegEmail);
        eName = findViewById(R.id.editRegName);
        eSurname = findViewById(R.id.editRegSurname);
        ePassword = findViewById(R.id.editRegPassword);
        ePhone = findViewById(R.id.editRegPhone);
        buttonRegister = findViewById(R.id.buttonRegister);
        goBack = findViewById(R.id.ibGoBack);

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

         */
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eEmail.getText().toString().trim();
                String password = ePassword.getText().toString().trim();
                String name = eName.getText().toString().trim();
                String surname = eSurname.getText().toString().trim();
                String phone = ePhone.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    eEmail.setError("Email jest wymagany!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ePassword.setError("Hasło jest wymagane!");
                    return;
                }
                if (password.length() < 6) {
                    ePassword.setError("Hasło musi składać się z co najmniej 6 znaków!");
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    eName.setError("Imię jest wymagane!");
                    return;
                }
                if (TextUtils.isEmpty(surname)) {
                    eSurname.setError("Nazwisko jest wymagane!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                            if(task.isSuccessful()){

                                FirebaseUser mUser = fAuth.getCurrentUser();
                                mUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(RegistrationActivity.this,"E-mail weryfikacyjny został wysłany.",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "E-mail nie został wysłany.");
                                    }
                                });


                                Toast.makeText(RegistrationActivity.this, "Konto zostało stworzone", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("imię",name);
                                user.put("nazwisko",surname);
                                user.put("email",email);
                                user.put("hasło",password);
                                user.put("telefon",phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "Zostalo stworzone konto dla uzytkownika" + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG,"onFailure: " + e.toString());
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                            }
                            else{
                                Toast.makeText(RegistrationActivity.this,"Błąd!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        });
            }
        });
    }
}