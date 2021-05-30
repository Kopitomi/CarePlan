package com.example.careplan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import io.grpc.CallCredentials;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String  LOG_TAG = MainActivity.class.getName();


    private FirebaseFirestore firestore;
    EditText nextAppointmentET;
    EditText usernameET;
    RadioGroup sexRB;
    EditText emailET;
    EditText ageET;
    EditText birthET;
    Spinner docSpinner;
    EditText plenET;
    EditText progressionET;


    @Override
    protected void onPause() {
        super.onPause();


        Log.i(LOG_TAG,"onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firestore = FirebaseFirestore.getInstance();
        nextAppointmentET = findViewById(R.id.nextAppointmentEditText);
        usernameET = findViewById(R.id.UsernameEditText);
            sexRB = findViewById(R.id.SexEditText);
        emailET = findViewById(R.id.EmailEditText);
        ageET = findViewById(R.id.ageEditText);
        birthET = findViewById(R.id.birthEditText);
        docSpinner = findViewById(R.id.doctorNameEditText);
        plenET = findViewById(R.id.PlanEditText);
        progressionET = findViewById(R.id.progressEditText);

        sexRB.check(R.id.alap);
        docSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Orvosa_neve, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docSpinner.setAdapter(adapter);

    }
    public void regist(View view) {
        String doc = docSpinner.getSelectedItem().toString();
        String username = usernameET.getText().toString();
        String nextAppointment = nextAppointmentET.getText().toString();
        String email = emailET.getText().toString();
        String age = ageET.getText().toString();
        String birthDate = birthET.getText().toString();
        String plan = plenET.getText().toString();
        String progress = progressionET.getText().toString();

        int checkedSex = sexRB.getCheckedRadioButtonId();
        RadioButton radioButton = sexRB.findViewById(checkedSex);
        String sexType = radioButton.getText().toString();

        DocumentReference ref = firestore.collection("patient").document(username);
        Map<String,Object> patientData = new HashMap<>();
        patientData.put("nextAppointment", nextAppointment);
        patientData.put("fullName", username);
        patientData.put("sex", sexType);
        patientData.put("doctorsName", doc);
        //patientData.put("state", "folyamatban");
        patientData.put("contact", email);
        patientData.put("age", age);
        patientData.put("description", plan);
        patientData.put("birthDay", birthDate);
        patientData.put("contact", email);
        patientData.put("status", progress);
        patientData.put("nextAppointment", nextAppointment);
        ref.set(patientData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
               Log.i(LOG_TAG, "siker");
            }
        });

    }

    public void cancle(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedDoc = parent.getItemAtPosition(position).toString();
        Log.i(LOG_TAG, selectedDoc);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


}
