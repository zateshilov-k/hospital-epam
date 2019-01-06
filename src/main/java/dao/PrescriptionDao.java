package dao;

import model.Prescription;
import model.PrescriptionType;

import java.util.List;

public interface PrescriptionDao {
    List<Prescription> getAllPrescriptionsByDiagnosisId(long diagnosisId);
    void addPrescription(long diagnosisId, long patientId, String description, PrescriptionType type);
}
