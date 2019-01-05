package dao.h2;

import dao.*;
import model.Prescription;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;

public class H2DaoFactory implements DaoFactory {
    final private PersonalDao personalDao;
    final private DiagnosisDao diagnosisDao;
    final private PatientDao patientDao;
    final private PrescriptionDao prescriptionDao;
    public H2DaoFactory(DataSource dataSource, DateTimeFormatter dateTimeFormatter) {
        personalDao = new H2PersonalDao(dataSource);
        diagnosisDao = new H2DiagnosisDao(dataSource, dateTimeFormatter);
        patientDao = new H2PatientDao(dataSource);
        prescriptionDao = new H2PrescriptionDao(dataSource,dateTimeFormatter);
    }

    @Override
    public PersonalDao getPersonalDao() {
        return personalDao;
    }

    @Override
    public PatientDao getPatientDao() {
        return patientDao;
    }

    @Override
    public DiagnosisDao getDiagnosisDao() {
        return diagnosisDao;
    }

    @Override
    public PrescriptionDao getPrescriptionDao() {
        return prescriptionDao;
    }

    @Override
    public void close() throws Exception {

    }
}
