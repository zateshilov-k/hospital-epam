package dao.h2;

import dao.PatientDao;
import model.Patient;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class H2PatientDao implements PatientDao {

    // SQL queries
    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged FROM " +
            "patient";
    private static final String CREATE_PATIENT_SQL = "INSERT INTO patient (first_name, last_name) VALUES (?, ?)";
    private static final String UPDATE_PATIENT_SQL = "UPDATE patient SET first_name = ?, last_name = ? WHERE patient_id = ?";
    private static final String GET_PATIENT = "SELECT * FROM patient WHERE patient.patient_id = ?";

    private static final Logger log = Logger.getLogger(String.valueOf(H2PatientDao.class));

    private DataSource dataSource;

    public H2PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPatient(String firstName, String lastName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PATIENT_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            System.out.println(statement.execute());
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public void updatePatient(String firstName, String lastName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PATIENT_SQL)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            System.out.println(statement.execute());
        } catch (SQLException e) {
            e.printStackTrace();
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
        //TODO !!!
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