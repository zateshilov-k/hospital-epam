package dao;

import model.Prescription;

import java.util.List;

public interface PrescriptionDao {
    List<Prescription> getAllPrescriptionsByDiagnosisId(long diagnosisId);

}
