import relations.Doctor;
import relations.Patient;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Doctor doctor1 = new Doctor();
        doctor1.id= 1;
        doctor1.name= "ahmed";
        doctor1.salary= 20000;

        Doctor doctor2 = new Doctor();
        doctor2.id= 2;
        doctor2.name= "osama";
        doctor2.salary= 25000;

        Patient patient1 = new Patient();
        patient1.id= 1;
        patient1.name= "ali";
        patient1.age= 20;

        Patient patient2 = new Patient();
        patient2.id= 1;
        patient2.name= "mohammed";
        patient2.age= 25;

        List<Patient> patients = new ArrayList<>();
        patients.add(patient1);
        patients.add(patient2);


        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);

        patient1.doctors = doctors;
        patient2.doctors = doctors;

        doctor1.patients = patients;
        doctor2.patients = patients;

        System.out.println(patient1.doctors);
        System.out.println(patient2.doctors);

        System.out.println(doctor1.patients);
        System.out.println(doctor2.patients);




    }
}