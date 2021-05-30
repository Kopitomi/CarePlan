package com.example.careplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PatientInfo extends AppCompatActivity {
    private static final String LOG_TAG = AvailablePatients.class.getName();
    private Button btnUpdate;
    private FirebaseFirestore firestore;
    private DocumentReference reference;
    FirebaseUser user;

    String mShowPatientName;
    String mPatientName;
    String mPatientContact;
    String mPatientGender;
    String mPatientAge;
    String mPatientBirthDay;
    String mPatientDoctorsName;
    String mPatientDescription;
    String mPatientStatus;
    String mPatientNextAppo;


    TextView CurrentPatient;
    EditText patientName;
    EditText patientGender;
    EditText patientContact;
    EditText patientAge;
    EditText patientBirthD;
    EditText patientDoctorsName;
    EditText patientDescription;
    EditText patientStatus;
    EditText patientNextOpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        Intent intent = getIntent();
        mShowPatientName = intent.getStringExtra("fullName");
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null || mShowPatientName == null){
            finish();
        }

        CurrentPatient = findViewById(R.id.user);
        patientName = findViewById(R.id.UsernameEditText);
        patientGender = findViewById(R.id.SexEditText);

        patientContact = findViewById(R.id.EmailEditText);
        patientAge = findViewById(R.id.ageEditText);
        patientBirthD = findViewById(R.id.birthEditText);
        patientDoctorsName = findViewById(R.id.doctorNameEditText);
        patientDescription = findViewById(R.id.PlanEditText);
        patientStatus = findViewById(R.id.progressEditText);
        patientNextOpp = findViewById(R.id.nextAppointmentEditText);
        btnUpdate = findViewById(R.id.gombocska);


        firestore = FirebaseFirestore.getInstance();
        reference = firestore.collection("patient").document(mShowPatientName);
        getData();

    }

    private void getData() {
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    mShowPatientName = (String) documentSnapshot.get("fullName");
                    mPatientGender = (String) documentSnapshot.get("sex");
                    mPatientAge = (String) documentSnapshot.get("age");
                    mPatientContact = (String) documentSnapshot.get("contact");
                    mPatientDoctorsName = (String) documentSnapshot.get("doctorsName");
                    mPatientNextAppo = (String) documentSnapshot.get("nextAppointment");
                    mPatientStatus = (String) documentSnapshot.get("status");
                    mPatientBirthDay = (String) documentSnapshot.get("birthDay");
                    mPatientDescription = (String) documentSnapshot.get("description");

                    CurrentPatient.append(mShowPatientName);
                    patientName.append(mShowPatientName);
                    patientGender.append(mPatientGender);
                    patientContact.append(mPatientContact);
                    patientAge.append(mPatientAge);
                    patientBirthD.append(mPatientBirthDay);
                    patientDoctorsName.append(mPatientDoctorsName);
                    patientDescription.append(mPatientDescription);
                    patientStatus.append(mPatientStatus);
                    patientNextOpp.append(mPatientNextAppo);

                }else{
                    Toast.makeText(PatientInfo.this,"valami nem okés",Toast.LENGTH_LONG).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PatientInfo.this,"A failure has occurred",Toast.LENGTH_LONG).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
                SendEmail();
            }
        });
    }

    private void SendEmail() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto: " + patientContact.getText().toString()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "update");
        intent.putExtra(Intent.EXTRA_TEXT, "Előfordulhat, hogy adatlapját módósították");
        Log.i(LOG_TAG,"email sent");
        startActivity(intent);

    }

    private void UpdateData() {
        //String uName = patientName.getText().toString();
        String uAge = patientAge.getText().toString();
        String uBirthDay = patientBirthD.getText().toString();
        String uContact = patientContact.getText().toString();
        String uDescription = patientDescription.getText().toString();
        String uDoctorsName = patientDoctorsName.getText().toString();
        String uNextAppointment = patientNextOpp.getText().toString();
        String uSex = patientGender.getText().toString();
        String uStatus = patientStatus.getText().toString();
        //reference.update("fullName", uName);
        reference.update("age", uAge);
        reference.update("birthDay", uBirthDay);
        reference.update("contact", uContact);
        reference.update("description", uDescription);
        reference.update("doctorsName", uDoctorsName);
        reference.update("nextAppointment", uNextAppointment);
        reference.update("sex", uSex);
        reference.update("status", uStatus);
        Toast.makeText(PatientInfo.this,"Update történt",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, AvailablePatients.class);
        startActivity(intent);
    }

    public void delete(View view) {
        firestore.collection("patient").document(mShowPatientName).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(LOG_TAG, "Szopacs, töröltek." + mShowPatientName);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(LOG_TAG, "Hiba törlésnél.");
            }
        });
        Intent intent = new Intent(this, AvailablePatients.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
//        Intent intent = new Intent(this, AvailablePatients.class);
//        startActivity(intent);
    }
}