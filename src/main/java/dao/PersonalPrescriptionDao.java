package dao;

public interface PersonalPrescriptionDao {
    void addPersonalPrescription(long personalId, long prescriptionId, String personalPrescriptionType);
}
