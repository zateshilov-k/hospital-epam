package dao;

public interface DaoFactory extends AutoCloseable {

    PersonalDao getPersonalDao();
    PatientDao getPatientDao();
    DiagnosisDao getDiagnosisDao();
    PrescriptionDao getPrescriptionDao();
    PersonalPrescriptionDao getPersonalPrescriptionDao();
}