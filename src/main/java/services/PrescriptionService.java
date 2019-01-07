package services;

import dao.DaoFactory;
import model.*;

import java.sql.SQLException;

public class PrescriptionService {
    public void addPrescribePrescription(Personal personal, Patient patient, String diagnosisIdString,
                                         String description, String type, DaoFactory daoFactory) {
        PrescriptionType prescriptionType = PrescriptionType.valueOf(type.toUpperCase());
        long diagnosisId = Long.parseLong(diagnosisIdString);
        try {
            long prescriptionId = daoFactory.getPrescriptionDao().addPrescription(diagnosisId, patient.getPatientId(), description, prescriptionType);
            daoFactory.getPersonalPrescriptionDao().addPersonalPrescription(personal.getPersonalId(),prescriptionId,
                    PersonalPrescriptionType.PRESCRIBE.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
