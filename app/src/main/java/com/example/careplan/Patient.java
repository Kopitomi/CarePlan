package com.example.careplan;

public class Patient {
    private String name;
    private String sex;
    private String contact;
    private String age;
    private String birthD;
    private String doctorsName;
    private String description;
    private String status;
    private String nextAppointment;
    //private boolean state;

    public Patient(String name, String sex, String contact, String age, String birthD, String doctorsName, String description, String status, String nextAppointment) {
        this.name = name;
        this.sex = sex;
        this.contact = contact;
        this.age = age;
        this.birthD = birthD;
        this.doctorsName = doctorsName;
        this.description = description;
        this.status = status;
        this.nextAppointment = nextAppointment;
        //this.state = state;
    }
    public String getName() {return name;}
    public String getSex() { return sex; }
    public String getContact() { return contact; }
    public String getAge() { return age; }
    public String getBirthD() { return birthD; }
    public String getDoctorsName() { return doctorsName; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getNextAppointment() { return nextAppointment; }
    //public boolean getState() { return state; }
}
