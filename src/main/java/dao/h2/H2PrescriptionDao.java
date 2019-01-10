package dao.h2;

import dao.PrescriptionDao;
import model.Prescription;
import model.PrescriptionType;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class H2PrescriptionDao implements PrescriptionDao {

    final private String GET_ALL_PRESCRIPTIONS = "SELECT * FROM prescription " + " WHERE diagnosis_id = ?;";
    final private String ADD_PRESCRIPTION =
            "INSERT INTO prescription (description, patient_id, time, diagnosis_id, " + "type, is_done) VALUES (?, ?," +
                    " ?, ?, ?, ?);";
    final private String UPDATE_PRESCRIPTION = "UPDATE prescription SET is_done = ?, time = ? WHERE prescription_id = ?;";

    private static final Logger log = Logger.getLogger(H2PrescriptionDao.class.getName());

    @Resource(name = "jdbc/hospital-h2-db")
    final private DataSource dataSource;
    final private DateTimeFormatter dateTimeFormatter;


    public H2PrescriptionDao(DataSource dataSource, DateTimeFormatter dateTimeFormatter) {
        this.dataSource = dataSource;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public List<Prescription> getAllPrescriptionsByDiagnosisId(long diagnosisId) {
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PRESCRIPTIONS)) {
            statement.setLong(1, diagnosisId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(resultSet.getLong("prescription_id"));
                    prescription.setType(PrescriptionType.valueOf(resultSet.getString("type")));

                    prescription.setDescription(resultSet.getString("description"));
                    prescription.setTime(LocalDateTime.parse(resultSet.getString("time").substring(0, 21), dateTimeFormatter));
                    prescription.setDone(resultSet.getBoolean("is_done"));
                    prescriptions.add(prescription);
                }
            }
        } catch (SQLException e) {
            log.info("Get all prescriptions by diagnosis id: " + diagnosisId + "; status: FAILED");
        }
        return prescriptions;
    }

    @Override
    public long addPrescription(long diagnosisId, long patientId, String description, PrescriptionType type) throws SQLException {
        long prescriptionId = 0;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(ADD_PRESCRIPTION)) {
            statement.setString(1, description);
            statement.setLong(2, patientId);
            statement.setString(3, LocalDateTime.now().format(dateTimeFormatter));
            statement.setLong(4, diagnosisId);
            statement.setString(5, type.name());
            statement.setBoolean(6, false);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                prescriptionId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            log.info("Add prescription, ID: " + diagnosisId + "; patientId: " + patientId + "; description: " +
                    description + "; type: " + type + "; status: FAILED");
        }
        return prescriptionId;
    }

    @Override
    public void updatePrescription(long prescriptionId) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(UPDATE_PRESCRIPTION)) {
            statement.setBoolean(1, true);
            statement.setString(2, LocalDateTime.now().format(dateTimeFormatter));
            statement.setLong(3, prescriptionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
