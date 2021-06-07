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
import com.google.firebase.firestore.FieldValue;
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
    String mPatientCategory;

    String mPatientNextAppo;

    String mPatientDutyMonday;
    String mPatientDutyTuesday;
    String mPatientDutyWednesday;
    String mPatientDutyThursday;
    String mPatientDutyFriday;
    String mPatientDutySaturday;
    String mPatientDutySunday;

    TextView CurrentPatient;
    EditText patientName;
    EditText patientGender;
    EditText patientContact;
    EditText patientAge;
    EditText patientBirthD;
    EditText patientDoctorsName;
    EditText patientDescription;
    EditText patientNextOpp;
    EditText PatientNote;
    EditText PatientCategory;

    EditText PatientDutyMonday;
    EditText PatientDutyTuesday;
    EditText PatientDutyWednesday;
    EditText PatientDutyThursday;
    EditText PatientDutyFriday;
    EditText PatientDutySaturday;
    EditText PatientDutySunday;

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
        PatientNote = findViewById(R.id.addNoteEditText);
        CurrentPatient = findViewById(R.id.user);
        patientName = findViewById(R.id.UsernameEditText);
        patientGender = findViewById(R.id.SexEditText);
        patientContact = findViewById(R.id.EmailEditText);
        patientAge = findViewById(R.id.ageEditText);
        patientBirthD = findViewById(R.id.birthEditText);
        patientDoctorsName = findViewById(R.id.doctorNameEditText);
        patientDescription = findViewById(R.id.progressEditText);
        PatientCategory = findViewById(R.id.categoryEditText);

        patientNextOpp = findViewById(R.id.nextAppointmentEditText);

        PatientDutyMonday = findViewById(R.id.MondayDescription);
        PatientDutyTuesday = findViewById(R.id.TuesdayDescription);
        PatientDutyWednesday = findViewById(R.id.WednesdayDescription);
        PatientDutyThursday = findViewById(R.id.ThursdayDescription);
        PatientDutyFriday = findViewById(R.id.FridayDescription);
        PatientDutySaturday = findViewById(R.id.SaturdayDescription);
        PatientDutySunday = findViewById(R.id.SundayDescription);

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
                    mPatientBirthDay = (String) documentSnapshot.get("birthDay");
                    mPatientDescription = (String) documentSnapshot.get("description");
                    mPatientCategory = (String) documentSnapshot.get("category");

                    mPatientDutyMonday = (String) documentSnapshot.get("monday");
                    mPatientDutyTuesday = (String) documentSnapshot.get("tuesday");
                    mPatientDutyWednesday = (String) documentSnapshot.get("wednesday");
                    mPatientDutyThursday = (String) documentSnapshot.get("thursday");
                    mPatientDutyFriday = (String) documentSnapshot.get("friday");
                    mPatientDutySaturday = (String) documentSnapshot.get("saturday");
                    mPatientDutySunday = (String) documentSnapshot.get("sunday");

                    CurrentPatient.append(mShowPatientName);
                    patientName.append(mShowPatientName);
                    patientGender.append(mPatientGender);
                    patientContact.append(mPatientContact);
                    patientAge.append(mPatientAge);
                    patientBirthD.append(mPatientBirthDay);
                    patientDoctorsName.append(mPatientDoctorsName);
                    patientDescription.append(mPatientDescription);
                    patientNextOpp.append(mPatientNextAppo);
                    PatientCategory.append(mPatientCategory);



                    PatientDutyMonday.append(mPatientDutyMonday);
                    PatientDutyTuesday.append(mPatientDutyTuesday);
                    PatientDutyWednesday.append(mPatientDutyWednesday);
                    PatientDutyThursday.append(mPatientDutyThursday);
                    PatientDutyFriday.append(mPatientDutyFriday);
                    PatientDutySaturday.append(mPatientDutySaturday);
                    PatientDutySunday.append(mPatientDutySunday);

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
                firestore = FirebaseFirestore.getInstance();
                reference = firestore.collection("patient").document(mShowPatientName);
                String note = PatientNote.getText().toString();
                reference.update("note", FieldValue.arrayUnion(note));
                UpdateData();
                //SendEmail();
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
        String uCategory = PatientCategory.getText().toString();

        String uMonday = PatientDutyMonday.getText().toString();
        String uTuesday = PatientDutyTuesday.getText().toString();
        String uWednesday = PatientDutyWednesday.getText().toString();
        String uThursday = PatientDutyThursday.getText().toString();
        String uFriday = PatientDutyFriday.getText().toString();
        String uSaturday = PatientDutySaturday.getText().toString();
        String uSunday = PatientDutySunday.getText().toString();

        //reference.update("fullName", uName);
        reference.update("age", uAge);
        reference.update("birthDay", uBirthDay);
        reference.update("contact", uContact);
        reference.update("description", uDescription);
        reference.update("doctorsName", uDoctorsName);
        reference.update("nextAppointment", uNextAppointment);
        reference.update("sex", uSex);
        reference.update("monday", uMonday);
        reference.update("tuesday", uTuesday);
        reference.update("wednesday", uWednesday);
        reference.update("thursday", uThursday);
        reference.update("friday", uFriday);
        reference.update("saturday", uSaturday);
        reference.update("sunday", uSunday);
        reference.update("category", uCategory);

        Toast.makeText(PatientInfo.this,"Update történt",Toast.LENGTH_LONG).show();
        finish();
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