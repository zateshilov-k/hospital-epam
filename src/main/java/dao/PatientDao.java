package dao;

import model.Patient;

import java.util.List;


public interface PatientDao {

    void addPatient (String firstName, String lastName);
    void updatePatient(String firstName, String lastName);
    List<Patient> getAllPatients();
    Patient getPatient(long patientId);
}