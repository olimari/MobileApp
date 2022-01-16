package com.example.hotelo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    TextView name, surname, email, phone;
    ImageButton goBack;
    Button buttonReservation;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    Button resendCode;
    TextView verMessage;

    Button resetPasswordLocal;
    FirebaseUser user;

    ImageView profilePic;
    Button changeProfilePic;
    StorageReference storageReference;

    TextView instagram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        phone = findViewById(R.id.profilePhone);
        name = findViewById(R.id.profileName);
        surname = findViewById(R.id.profileSurname);
        email = findViewById(R.id.profileEmail);
        goBack = findViewById(R.id.buttonGoBack);
        buttonReservation = findViewById(R.id.buttonMyReservation);

        instagram = findViewById(R.id.insta);

        resetPasswordLocal = findViewById(R.id.buttonChangePassword);
        profilePic = findViewById(R.id.profilePic);
        changeProfilePic = findViewById(R.id.buttonEditProfile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profilePic);
            }
        });

        resendCode = findViewById(R.id.buttonEmailVer);
        verMessage = findViewById(R.id.tvEmailInfo);


        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();


        if(!user.isEmailVerified()){
            resendCode.setVisibility(View.VISIBLE);
            verMessage.setVisibility(View.VISIBLE);

            resendCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(),"E-mail weryfikacyjny został wysłany.",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "E-mail nie został wysłany.");
                        }
                    });
                }
            });
        }


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, (value, error) -> {
           // if(value.exists()) {
                phone.setText(value.getString("telefon"));
                name.setText(value.getString("imię"));
                surname.setText(value.getString("nazwisko"));
                email.setText(value.getString("email"));
            /*}else{
                Log.d("TAG","Dokument nie istnieje");
            }*/
        });

        resetPasswordLocal.setOnClickListener(v -> {
            final EditText resetPassword = new EditText(v.getContext());
            final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Zmiana hasła");
            passwordResetDialog.setMessage("Wpisz nowe hasło.");
            passwordResetDialog.setView(resetPassword);

            passwordResetDialog.setPositiveButton("Tak", (dialog, which) -> {
                String newPass = resetPassword.getText().toString();
                user.updatePassword(newPass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProfileActivity.this,"Zmiana hasła przebiegła pomyślnie.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,"Hasło nie zostało zmienione.",Toast.LENGTH_SHORT).show());
            });
            passwordResetDialog.setNegativeButton("Nie", (dialog, which) -> {
            });

            passwordResetDialog.create().show();

        });
        buttonReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ReservationActivity.class));
            }
        });

        changeProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otwieranie galerii w telefonie

                /*Intent i = new Intent(v.getContext(), EditActivity.class);
                i.putExtra("imię",name.getText().toString());
                i.putExtra("nazwisko",surname.getText().toString());
                i.putExtra("email",email.getText().toString());
                i.putExtra("telefon",phone.getText().toString());
                startActivity(i);*/

                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, WelcomeActivity.class));
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profilePic.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        //zapisywanie zdjecia profilowego do firebase
        StorageReference fileRef = storageReference.child(("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg"));
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profilePic);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this,"Nie udało się zmienić zdjęcia",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}