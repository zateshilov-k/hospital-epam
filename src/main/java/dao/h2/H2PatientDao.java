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

public class H2PatientDao implements PatientDao {

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;

    public H2PatientDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // SQL queries
    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged FROM "
            + "patient WHERE patient_id = ?";

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PATIENTS_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Patient patient = new Patient();
                    System.out.println(1);
                    patient.setPatientId(resultSet.getLong("patient_id"));
                    System.out.println(2);
                    patient.setFirstName(resultSet.getString("first_name"));
                    System.out.println(3);
                    patient.setLastName(resultSet.getString("last_name"));
                    System.out.println(4);
                    patient.setDischarged(resultSet.getBoolean("is_discharged"));
                    System.out.println(5);
                    patients.add(patient);
                    System.out.println(6);
                }
            }
        } catch (SQLException e) {
            System.err.println("Wrong add patient in patients list");
        }
        return patients;
    }

}
