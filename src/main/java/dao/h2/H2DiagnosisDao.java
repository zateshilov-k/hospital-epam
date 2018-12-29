package dao.h2;

import dao.DiagnosisDao;
import model.Diagnosis;
import model.Patient;
import model.Personal;

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
    private static final String GET_ALL_DIAGNOSES_SQL = "SELECT diagnosis_id, description FROM diagnosis WHERE " +
            "patient_id = ?";

    public H2DiagnosisDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Diagnosis> getDiagnosisByPatientId(long patientId) {
        List<Diagnosis> diagnosisList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_DIAGNOSES_SQL)) {
            statement.setLong(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Diagnosis diagnosis = new Diagnosis();
                    diagnosis.setDiagnosisId(resultSet.getLong("diagnosis_id"));
                    diagnosis.setDescription(resultSet.getString("description"));

//                    H2PersonalDao h2PatientDao = new H2PersonalDao();
//                    Personal personal = h2PatientDao.getPersonal(resultSet.getLong("personal_id"));\


                    diagnosisList.add(diagnosis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosisList;
    }

}