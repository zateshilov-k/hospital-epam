package dao;

import model.Prescription;
import model.PrescriptionType;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionDao {
    List<Prescription> getAllPrescriptionsByDiagnosisId(long diagnosisId);
    long addPrescription(long diagnosisId, long patientId, String description, PrescriptionType type) throws SQLException;
}
