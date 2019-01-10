package dao.h2;

import dao.PatientDao;
import model.Patient;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class H2PatientDao implements PatientDao {

    private static final String CREATE_PATIENT_SQL = "INSERT INTO patient (first_name, last_name, is_discharged) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_PATIENT_SQL = "UPDATE patient SET first_name = ?, last_name = ?, " +
            "is_discharged = ?, is_deleted = ? " + " WHERE patient_id = ?";
    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged FROM "
            + "patient";
    private static final String GET_PATIENT_SQL = "SELECT * FROM patient WHERE patient.patient_id = ?";

    private static final Logger log = Logger.getLogger(H2PatientDao.class.getName());

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;

    public H2PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPatient(Patient patient) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(CREATE_PATIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setBoolean(3, patient.isDischarged());
            System.out.println(statement.execute());
        } catch (SQLException e) {
            log.info("Add patient by ID: " + patient.getPatientId() + "; first name: " + patient.getFirstName() + "; " +
                    "last name: " + patient.getLastName() + "; discharged: " + patient.isDischarged() + "; status: " + "FAILED");
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(UPDATE_PATIENT_SQL)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setBoolean(3, patient.isDischarged());
            statement.setBoolean(4, patient.isDeleted());
            statement.setLong(5, patient.getPatientId());
            System.out.println(statement.execute());
        } catch (SQLException e) {
            log.info("Update patient by ID: " + patient.getPatientId() + "; first name: " + patient.getFirstName() +
                    "; last name: " + patient.getLastName() + "; discharged: " + patient.isDischarged() + "; deleted: " + patient.isDeleted() + "; status: "
                    + "FAILED");
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PATIENTS_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    patientList.add(getPatientFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            log.info("Get all patients, list size: " + patientList.size() + "; status: FAILED");
        }
        return patientList;
    }

    @Override
    public Patient getPatient(long patientId) {
        Patient patient = new Patient();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_PATIENT_SQL)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                patient = getPatientFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            log.info("Get patient, by ID: " + patientId + "; status: FAILED");
        }
        return patient;
    }

    public Patient getPatientFromResultSet(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(resultSet.getLong("patient_id"));
        patient.setFirstName(resultSet.getString("first_name"));
        patient.setLastName(resultSet.getString("last_name"));
        patient.setDischarged(resultSet.getBoolean("is_discharged"));
        return patient;
    }

}