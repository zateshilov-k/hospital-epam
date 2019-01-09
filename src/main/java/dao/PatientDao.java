package dao;

import model.Patient;

import java.util.List;

public interface PatientDao {
    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatient(long patientId);
}