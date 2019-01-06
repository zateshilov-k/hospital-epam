package dao;

import model.Patient;

import java.util.List;


public interface PatientDao {

    void addPatient (Patient patient);
    void updatePatient(String firstName, String lastName);
    List<Patient> getAllPatients();
    Patient getPatient(long patientId);
}