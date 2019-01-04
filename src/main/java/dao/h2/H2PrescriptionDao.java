package dao.h2;

import dao.PrescriptionDao;
import model.Diagnosis;
import model.Prescription;
import model.PrescriptionType;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2PrescriptionDao implements PrescriptionDao {
    final private DataSource dataSource;
    final private DateTimeFormatter dateTimeFormatter;

    final private String GET_ALL_PRESCRIPTIONS = "SELECT * FROM prescription " +
            " WHERE diagnosis_id = ?;";
    public H2PrescriptionDao(DataSource dataSource, DateTimeFormatter dateTimeFormatter) {
        this.dataSource = dataSource;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public List<Prescription> getAllPrescriptionsByDiagnosisId(long diagnosisId) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PRESCRIPTIONS)) {
            statement.setLong(1,diagnosisId);
            try (ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(resultSet.getLong("prescription_id"));
                    prescription.setType(PrescriptionType.valueOf(resultSet.getString("type")));
                    prescription.setDescription(resultSet.getString("description"));
                    prescription.setTime(LocalDateTime.parse(resultSet.getString("time"),dateTimeFormatter));
                    prescription.setDone(resultSet.getBoolean("is_done"));
                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }
}
