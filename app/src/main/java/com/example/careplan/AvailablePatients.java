package com.example.careplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AvailablePatients extends AppCompatActivity {
    private static final String LOG_TAG = AvailablePatients.class.getName();
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private RecyclerView mRecyclerView;
    private ArrayList<Patient> mItemList;
    private PatientAdapter mAdapter;

    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_patients);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mItemList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mAdapter = new PatientAdapter(this, mItemList);
        mRecyclerView.setAdapter(mAdapter);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("patient").orderBy("fullName").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mItemList.add(new Patient(document.getString("fullName"), document.getString("sex"), document.getString("contact"), document.getString("age"), document.getString("birthDay"), document.getString("doctorsName"), document.getString("description"), document.getString("nextAppointment"), String.valueOf(document.get("note"))));
                    }
                } else {
                    Toast.makeText(AvailablePatients.this, "An error has occoured, please try again later", Toast.LENGTH_LONG).show();
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void cancel(View view) { finish(); }
}