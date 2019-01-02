package dao;

import model.Diagnosis;
import model.Patient;

import java.util.List;

public interface DiagnosisDao {

    List<Diagnosis> getAllDiagnosesByPatientId(long patientId);

}
