package dao.h2;

import dao.PersonalPrescriptionDao;
import listeners.DatabaseInitListener;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class H2PersonalPrescription implements PersonalPrescriptionDao {
    private final String ADD_PERSONAL_PRESCRIPTION = "INSERT INTO medical_personal_prescription (type, personal_id, prescription_id) " +
            "VALUES (?, ?, ?);";
    private DataSource dataSource;

    public H2PersonalPrescription(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPersonalPrescription(long personalId, long prescriptionId, String personalPrescriptionType) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PERSONAL_PRESCRIPTION)) {
            statement.setString(1, personalPrescriptionType);
            statement.setLong(2, personalId);
            statement.setLong(3, prescriptionId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
