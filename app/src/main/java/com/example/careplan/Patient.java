package com.example.careplan;

import java.lang.reflect.Array;
import java.util.List;

public class Patient {
    private String name;
    private String sex;
    private String contact;
    private String age;
    private String birthD;
    private String doctorsName;
    private String description;
    private String category;

    private String mondayPlan;
    private String tuesdayPlan;
    private String wednesdayPlan;
    private String thursdayPlan;
    private String fridayPlan;
    private String saturdayPlan;
    private String sundayPlan;
    private String nextAppointment;
    private List<String> note;


    public Patient(List<String> note) {
        this.note = note;
    }

    public Patient(String mondayPlan, String tuesdayPlan, String wednesdayPlan, String thursdayPlan, String fridayPlan, String saturdayPlan, String sundayPlan) {
        this.mondayPlan = mondayPlan;
        this.tuesdayPlan = tuesdayPlan;
        this.wednesdayPlan = wednesdayPlan;
        this.thursdayPlan = thursdayPlan;
        this.fridayPlan = fridayPlan;
        this.saturdayPlan = saturdayPlan;
        this.sundayPlan = sundayPlan;
    }


    public Patient(String name, String sex, String contact, String age, String birthD, String doctorsName, String description,  String nextAppointment, String category) {
        this.name = name;
        this.sex = sex;
        this.contact = contact;
        this.age = age;
        this.birthD = birthD;
        this.doctorsName = doctorsName;
        this.description = description;
        this.category = category;
        this.nextAppointment = nextAppointment;

    }

    public String getName() {return name;}

    public String getSex() { return sex; }
    public String getContact() { return contact; }
    public String getAge() { return age; }
    public String getBirthD() { return birthD; }
    public String getDoctorsName() { return doctorsName; }
    public String getDescription() { return description; }
    public String getNextAppointment() { return nextAppointment; }
    public String getCategory() {
        return category;
    }
    public String getMondayPlan() {return mondayPlan;}
    public List<String> getNote() {
        return note;
    }

    public String getTuesdayPlan() {
        return tuesdayPlan;
    }

    public String getWednesdayPlan() {
        return wednesdayPlan;
    }

    public String getThursdayPlan() {
        return thursdayPlan;
    }

    public String getFridayPlan() {
        return fridayPlan;
    }

    public String getSaturdayPlan() {
        return saturdayPlan;
    }

    public String getSundayPlan() {
        return sundayPlan;
    }
}
