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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.grpc.CallCredentials;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String  LOG_TAG = MainActivity.class.getName();

    //List<String> category;
    private FirebaseFirestore firestore;
    private DocumentReference reference;
    EditText nextAppointmentET;
    EditText usernameET;
    RadioGroup sexRB;
    EditText emailET;
    EditText ageET;
    EditText birthET;
    Spinner docSpinner;
    Spinner catSpinner;
    EditText progressionET;
    EditText mondayET;
    EditText tuesdayET;
    EditText wednesdayET;
    EditText thursdayET;
    EditText fridayET;
    EditText saturdayET;
    EditText sundayET;
    EditText categoryET;

    List<String> category = new ArrayList<>();



    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG,"onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.i(LOG_TAG, String.valueOf(category.size()));

        firestore = FirebaseFirestore.getInstance();
        usernameET = findViewById(R.id.UsernameEditText);
        sexRB = findViewById(R.id.SexEditText);
        emailET = findViewById(R.id.EmailEditText);
        ageET = findViewById(R.id.ageEditText);
        birthET = findViewById(R.id.birthEditText);
        docSpinner = findViewById(R.id.doctorNameEditText);
        progressionET = findViewById(R.id.progressEditText);
        nextAppointmentET = findViewById(R.id.nextAppointmentEditText);
        mondayET = findViewById(R.id.MondayDescription);
        tuesdayET = findViewById(R.id.TuesdayDescription);
        wednesdayET = findViewById(R.id.WednesdayDescription);
        thursdayET = findViewById(R.id.ThursdayDescription);
        fridayET = findViewById(R.id.FridayDescription);
        saturdayET = findViewById(R.id.SaturdayDescription);
        sundayET = findViewById(R.id.SundayDescription);
        categoryET = findViewById(R.id.categoryEditText);
        catSpinner = findViewById(R.id.categorySpinner);
        sexRB.check(R.id.alap);

        docSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Orvosa_neve, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        docSpinner.setAdapter(adapter);



        category.add("diabetes");
        catSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, category);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter1);

//       category.add("category2");



    }
    public void regist(View view) {
        String doc = docSpinner.getSelectedItem().toString();
        String username = usernameET.getText().toString();
        String nextAppointment = nextAppointmentET.getText().toString();
        String email = emailET.getText().toString();
        String age = ageET.getText().toString();
        String birthDate = birthET.getText().toString();
        String progress = progressionET.getText().toString();
        String monday = mondayET.getText().toString();
        String tuesday = tuesdayET.getText().toString();
        String wednesday = wednesdayET.getText().toString();
        String thursday = thursdayET.getText().toString();
        String friday = fridayET.getText().toString();
        String saturday = saturdayET.getText().toString();
        String sunday = sundayET.getText().toString();
        String categoria = categoryET.getText().toString();
        String categorySpinner = catSpinner.getSelectedItem().toString();




        int checkedSex = sexRB.getCheckedRadioButtonId();
        RadioButton radioButton = sexRB.findViewById(checkedSex);
        String sexType = radioButton.getText().toString();

        DocumentReference ref = firestore.collection("patient").document(username);
        Date currentTime = Calendar.getInstance().getTime();
        Map<String,Object> patientData = new HashMap<>();
        if (categoria.matches("")){
            patientData.put("category", categorySpinner);
        } else {
            patientData.put("category", categoria);
            category.add(categoria);

        }
        patientData.put("nextAppointment", nextAppointment);
        patientData.put("created", currentTime);
        patientData.put("fullName", username);
        patientData.put("sex", sexType);
        patientData.put("doctorsName", doc);
        patientData.put("contact", email);
        patientData.put("age", age);
        patientData.put("birthDay", birthDate);
        patientData.put("contact", email);
        patientData.put("description", progress);
        patientData.put("nextAppointment", nextAppointment);
        patientData.put("monday", monday);
        patientData.put("tuesday", tuesday);
        patientData.put("wednesday", wednesday);
        patientData.put("thursday", thursday);
        patientData.put("friday", friday);
        patientData.put("saturday", saturday);
        patientData.put("sunday", sunday);
        patientData.put("note", Arrays.asList());



//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        ref.set(patientData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
               Log.i(LOG_TAG, "siker");
               Log.i(LOG_TAG, String.valueOf(category.size()));
                 nextAppointmentET.getText().clear();
                 usernameET.getText().clear();
                 emailET.getText().clear();
                 ageET.getText().clear();
                 birthET.getText().clear();
                 progressionET.getText().clear();
                 mondayET.getText().clear();
                 tuesdayET.getText().clear();
                 wednesdayET.getText().clear();
                 thursdayET.getText().clear();
                 fridayET.getText().clear();
                 saturdayET.getText().clear();
                 sundayET.getText().clear();
                 categoryET.getText().clear();
            }
        });

    }

    public void cancle(View view) {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedDoc = parent.getItemAtPosition(position).toString();
        Log.i(LOG_TAG, selectedDoc);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


}
