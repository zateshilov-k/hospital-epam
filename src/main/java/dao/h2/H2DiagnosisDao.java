package dao.h2;

import dao.DiagnosisDao;
import model.Diagnosis;
import model.Patient;
import model.Personal;
import model.Role;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class H2DiagnosisDao implements DiagnosisDao {

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;
    private DateTimeFormatter dateTimeFormatter;
    private static final Logger log = Logger.getLogger(String.valueOf(H2DiagnosisDao.class));

    // SQL queries
    private static final String GET_ALL_DIAGNOSES_SQL = "SELECT * FROM diagnosis JOIN patient " +
            "ON diagnosis.patient_id = patient.patient_id JOIN medical_personal " +
            "ON diagnosis.personal_id = medical_personal.personal_id WHERE diagnosis.patient_id = ?;";

    private static final String ADD_DIAGNOSIS = "INSERT INTO diagnosis (description, personal_Id, patient_id, time, " +
            "is_healthy) VALUES (?, ?, ?, ?,?);";

    public H2DiagnosisDao(DataSource dataSource, DateTimeFormatter dateTimeFormatter) {
        this.dataSource = dataSource;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public List<Diagnosis> getAllDiagnosesByPatientId(long patientId) {
        List<Diagnosis> diagnosisList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_DIAGNOSES_SQL)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Diagnosis diagnosis = new Diagnosis();
                    Patient patient = new Patient();
                    patient.setPatientId(resultSet.getLong("patient.patient_id"));
                    patient.setFirstName(resultSet.getString("patient.first_name"));
                    patient.setLastName(resultSet.getString("patient.last_name"));
                    patient.setDischarged(resultSet.getBoolean("patient.is_discharged"));
                    diagnosis.setPatient(patient);

                    Personal personal = new Personal();
                    personal.setPersonalId(resultSet.getLong("medical_personal.personal_id"));
                    personal.setPassword(resultSet.getString("medical_personal.password"));
                    personal.setLogin(resultSet.getString("medical_personal.login"));
                    personal.setRole(Role.valueOf(resultSet.getString("medical_personal.role")));
                    personal.setFirstName(resultSet.getString("medical_personal.first_name"));
                    personal.setLastName(resultSet.getString("medical_personal.last_name"));
                    diagnosis.setPersonal(personal);

                    diagnosis.setDiagnosisId(resultSet.getLong("diagnosis.diagnosis_id"));
                    diagnosis.setDescription(resultSet.getString("diagnosis.description"));
                    diagnosis.setTime(LocalDateTime.parse(
                            resultSet.getString("diagnosis.time"),dateTimeFormatter));
                    diagnosis.setHealthy(resultSet.getBoolean("diagnosis.is_healthy"));
                    diagnosisList.add(diagnosis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosisList;
    }

    @Override
    public void addDiagnosis(long patientId, long personalId, String description) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(ADD_DIAGNOSIS)) {
            statement.setString(1, description);
            statement.setLong(2, personalId);
            statement.setLong(3, patientId);
            statement.setString(4, LocalDateTime.now().format(dateTimeFormatter));
            statement.setBoolean(5,false);
            System.out.println(statement.execute());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDiagnosis(long diagnosisId, String description, boolean isHealthy){
        //TODO write code here
    }
}