package dao.h2;

import dao.PersonalDao;
import model.Personal;
import model.Role;

import javax.sql.DataSource;
import java.sql.*;

public class H2PersonalDao implements PersonalDao {

    private DataSource dataSource;

    private static final String CREATE_PERSONAL_SQL =
            "INSERT INTO Personals (personalId, email, password, firstName, lastName, role) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PERSONAL_BY_EMAIL_SQL =
            "SELECT personalId, password, firstName, lastName, role FROM Personals WHERE email = ?";

    private static final String UPDATE_PERSONAL_SQL =
            "UPDATE Personals SET email = ?, password = ?, firstName = ?, lastName = ?, role = ? WHERE personalId = ?";

    public H2PersonalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createPersonal(Personal personal) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_PERSONAL_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(0, personal.getPersonalId());
            statement.setString(1, personal.getEmail());
            statement.setString(2, String.valueOf(personal.getPassword()));
            statement.setString(3, personal.getFirstName());
            statement.setString(4, personal.getLastName());
            statement.setString(5, personal.getRole().toString());
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch(SQLException e) {
            System.err.println("creating personal - error");
        }
        return 0;
    }

    @Override
    public Personal readPersonalByEmail(String email) {
        Personal personal = new Personal();
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_PERSONAL_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                personal.setPersonalId(resultSet.getLong("personalId"));
                personal.setEmail(email);
                personal.setPassword(resultSet.getString("password"));
                personal.setFirstName(resultSet.getString("firstName"));
                personal.setLastName(resultSet.getString("lastName"));
                personal.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {

        }
        return personal;
    }



    @Override
    public long updatePersonal(Personal personal) {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_PERSONAL_SQL)) {
            statement.setLong(0, personal.getPersonalId());
            statement.setString(1, personal.getEmail());
            statement.setString(2, personal.getPassword().toString());
            statement.setString(3, personal.getFirstName());
            statement.setString(4, personal.getLastName());
            statement.setString(5, personal.getRole().toString());
            statement.executeUpdate();
            return personal.getPersonalId();
        } catch (SQLException e) {
            return 0;
        }
    }

}
