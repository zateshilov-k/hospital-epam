package dao;

import model.Diagnosis;

public interface DaoFactory extends AutoCloseable {

    PersonalDao getPersonalDao();
    DiagnosisDao getDiagnosisDao();
}