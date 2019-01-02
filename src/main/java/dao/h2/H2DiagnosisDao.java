package dao.h2;

import dao.DiagnosisDao;
import model.Diagnosis;

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
    private static final String GET_ALL_DIAGNOSES_SQL = "SELECT diagnosis_id, description, personal_id, patient_id, " +
            "time, is_healthy FROM diagnosis WHERE " + "patient_id = ?";

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
                    // Нужно засунуть personal_id and patient_id with type long. Но, в model patient and personal,
                    //    private Personal personal вместо private long personalId - не соотвествие с БД, поэтому
                    // Нужен setPersonalId(long id)
                    // Сейчас setPersonal(Personal personal)
                    // Поэтому не распознают методы ниже\

                    /*
                    * Peronal p = new Pers();
                    * p.set.
                    *
                    * */


                    //personalId = resultSet.getLong("personal_id");
                    //Personal pers = personalDao.getPersonalById(personalId);
                    //diagnosis.setPersonal(pers);
//                    diagnosis.setPersonal(resultSet.getLong("personal_id"));
//                    diagnosis.setPatient(resultSet.getLong("patient_id"));
                    //diagnosis.setTime(resultSet.getTime("time"));
                    diagnosis.setHealthy(resultSet.getBoolean("is_healthy"));
                    diagnosisList.add(diagnosis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosisList;
    }

}