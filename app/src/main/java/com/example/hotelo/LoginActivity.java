package com.example.hotelo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText eEmail, ePassword;
    Button buttonLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView tvCreate;
    TextView tvResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.editEmail);
        ePassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin2);
        progressBar = findViewById(R.id.progressBar);
        tvCreate = findViewById(R.id.tvRegister2);
        tvResetPassword = findViewById(R.id.textResetPassword);
        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = eEmail.getText().toString().trim();
                String password = ePassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    eEmail.setError("Email jest wymagany!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ePassword.setError("Has??o jest wymagane!");
                    return;
                }
                if (password.length() < 6) {
                    ePassword.setError("Has??o musi sk??ada?? si?? z co najmniej 6 znak??w!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logowanie zako??czy??o si?? pomy??lnie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"B????d!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        tvCreate.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
        tvResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset has??a");
                passwordResetDialog.setMessage("Wpisz sw??j email, aby otrzyma?? link resetuj??cy has??o.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Link resetuj??cy has??o zosta?? wys??any", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"B????d! Link resetuj??cy has??o NIE zosta?? wys??any.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
             }
        });
    }
}