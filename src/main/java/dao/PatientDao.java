package dao;

import model.Patient;

import java.util.List;


public interface PatientDao {

    List<Patient> getAllPatients();
    Patient getPatient(long patientId);
}