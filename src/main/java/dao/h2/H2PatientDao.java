package dao.h2;

import dao.PatientDao;
import model.Patient;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class H2PatientDao implements PatientDao {

    private static final Logger log = Logger.getLogger(String.valueOf(H2PatientDao.class));
    // SQL queries
    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged FROM " +
            "patient";
    private static final String GET_PATIENT = "SELECT * FROM patient WHERE patient.patient_id = ?;";
    private DataSource dataSource;

    public H2PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPatient(String firstName, String lastName) {

    }

    @Override
    public void updatePatient(String firstName, String lastName) {

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
            log.warning(e.getMessage());
            System.err.println(e.getMessage());
        }
        return patientList;
    }

    @Override
    public Patient getPatient(long patientId) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_PATIENT)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return getPatientFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getPatientFromResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("getPatientFromResultSet");
        Patient patient = new Patient();
        patient.setPatientId(resultSet.getLong("patient_id"));
        patient.setFirstName(resultSet.getString("first_name"));
        patient.setLastName(resultSet.getString("last_name"));
        patient.setDischarged(resultSet.getBoolean("is_discharged"));
        return patient;
    }

}