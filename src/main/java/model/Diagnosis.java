package model;

public class Diagnosis {

    private long diagnosisId;
    private String description;
    private Personal personalId;
    private Patient patientId;
    private boolean isHealthy;

    public long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Personal getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Personal personalId) {
        this.personalId = personalId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

}
