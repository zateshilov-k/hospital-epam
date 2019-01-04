package dao;

import model.Diagnosis;
import model.Patient;

import java.util.List;

public interface DiagnosisDao {

    List<Diagnosis> getAllDiagnosesByPatientId(long patientId);

    void addDiagnosis(long patientId, long personalId, String description);

    void updateDiagnosis(long diagnosisId, String description, boolean isHealthy);
}
