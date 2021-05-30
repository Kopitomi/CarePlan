package com.example.careplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String  LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG_TAG,"onCreate");
    }

    public void register(View view) {
        Log.i(LOG_TAG,"register");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, DeletePatient.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, UpdatePatient.class);
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(this, AvailablePatients.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}