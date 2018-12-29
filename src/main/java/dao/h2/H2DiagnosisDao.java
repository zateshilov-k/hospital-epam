package dao.h2;

import dao.DiagnosisDao;
import model.Diagnosis;
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

public class H2DiagnosisDao implements DiagnosisDao {

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;
    private static final Logger log = Logger.getLogger(String.valueOf(H2DiagnosisDao.class));

    // SQL queries
//    private static final String GET_ALL_PATIENTS_SQL = "SELECT patient_id, first_name, last_name, is_discharged
//    FROM " +
//            "patient";

    private static final String GET_ALL_DIAGNOSES_SQL = "SELECT diagnosis_id, description, personal_id, patient_id, time, is_healthy " + " FROM diagnosis";

    public H2DiagnosisDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Diagnosis> allDiagnosis(Patient patient) {
        List<Diagnosis> diagnosesList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_DIAGNOSES_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setDiagnosisId(resultSet.getLong("diagnosis_id"));
                    diagnosis.setDescription(resultSet.getString("description"));


                    diagnosesList.add(diagnosis);
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return diagnosesList;
    }

}
