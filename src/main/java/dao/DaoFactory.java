package dao;

public interface DaoFactory {

    PersonalDao getPersonalDao();

    PatientDao getPatientDao();

    DiagnosisDao getDiagnosisDao();

    PrescriptionDao getPrescriptionDao();

    PersonalPrescriptionDao getPersonalPrescriptionDao();
}