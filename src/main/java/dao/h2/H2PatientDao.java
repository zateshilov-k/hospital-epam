package dao.h2;

import dao.PatientDao;
import model.Patient;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class H2PatientDao implements PatientDao {

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;
    private static final Logger log = Logger.getLogger(String.valueOf(H2PatientDao.class));

    public H2PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // SQL queries
    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged FROM " +
            "patient";

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PATIENTS_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Patient patient = new Patient();
                    patient.setPatientId(resultSet.getLong("patient_id"));
                    patient.setFirstName(resultSet.getString("first_name"));
                    patient.setLastName(resultSet.getString("last_name"));
                    patient.setDischarged(resultSet.getBoolean("is_discharged"));
                    patientList.add(patient);
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return patientList;
    }

}