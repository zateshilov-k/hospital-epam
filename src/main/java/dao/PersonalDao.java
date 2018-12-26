package dao;

import model.Patient;

public interface PersonalDao {

    long createPatient(Patient patient);

    Patient readPatientById(long patientId);

    Patient readPatientByFirstName(String firstName);

    long updatePatient(Patient patient);
}
