package com.example.careplan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> implements Filterable {
    private ArrayList<Patient> mPatientData;
    private ArrayList<Patient> mPatientDataAll;
    private Context mContext;
    private int lastPosition = -1;


    public PatientAdapter(Context context, ArrayList<Patient> patientData) {
        this.mPatientData = patientData;
        this.mPatientDataAll = patientData;
        this.mContext = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_patient_card, parent, false));

    }

    @Override
    public void onBindViewHolder( PatientAdapter.ViewHolder holder, int position) {
        Patient currentPatient = mPatientData.get(position);
        holder.bindTo(currentPatient);
    }
    @Override
    public int getItemCount() {
        return mPatientData.size(); }

    @Override
    public Filter getFilter() {
        return patientFilter;
    }
    private Filter patientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Patient> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                results.count = mPatientDataAll.size();
                results.values = mPatientDataAll;

            }else {
                String FilterPattern = charSequence.toString().toLowerCase().trim();

                for (Patient patient : mPatientDataAll){
                    if (patient.getName().toLowerCase().contains(FilterPattern)){
                        filteredList.add(patient);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mPatientData = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{
    private TextView mNameText;
    private TextView mAgeText;
    private TextView mSexText;
    private TextView mDoctorsNameText;


    public ViewHolder(View itemView) {
        super(itemView);

        mNameText = itemView.findViewById(R.id.patientName);
        mAgeText = itemView.findViewById(R.id.age);
        mSexText = itemView.findViewById(R.id.patientSex);
        mDoctorsNameText = itemView.findViewById(R.id.doctorsName);

        itemView.findViewById(R.id.viewPatient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,PatientInfo.class);
                intent.putExtra("fullName",mNameText.getText().toString());
                mContext.startActivity(intent);
            }
        });

        //itemView.findViewById(R.id.)

    }

    public void bindTo(Patient currentPatient) {
        mNameText.setText(currentPatient.getName());
        mAgeText.setText(currentPatient.getAge());
        mSexText.setText(currentPatient.getSex());
        mDoctorsNameText.setText(currentPatient.getDoctorsName());

    }

};
};
