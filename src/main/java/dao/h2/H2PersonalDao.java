package dao.h2;

import dao.PersonalDao;
import model.Personal;
import model.Role;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class H2PersonalDao implements PersonalDao {

    private static final String CREATE_PERSONAL_SQL = "INSERT INTO Personals (personalId, login, password, firstName," +
            " lastName, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PERSONAL_BY_LOGIN_SQL = "SELECT personal_id, first_name, last_name, role, login, " +
            "password FROM medical_personal WHERE login = ?;";
    private static final String UPDATE_PERSONAL_SQL = "UPDATE Personals SET login = ?, password = ?, firstName = ?, " +
            "lastName = ?, role = ? WHERE personalId = ?";
    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;

    public H2PersonalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createPersonal(Personal personal) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(CREATE_PERSONAL_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, personal.getPersonalId());
            statement.setString(2, personal.getLogin());
            statement.setString(3, String.valueOf(personal.getPassword()));
            statement.setString(4, personal.getFirstName());
            statement.setString(5, personal.getLastName());
            statement.setString(6, personal.getRole().toString());
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("creating personal - error");
        }
        return 0;
    }

    public Optional<Personal> readPersonalByEmail(String login) {
        try (Connection connection = this.dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(SELECT_PERSONAL_BY_LOGIN_SQL)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                Personal personal = new Personal();
                if (resultSet.next()) {
                    personal.setPersonalId(resultSet.getInt("personal_id"));
                    personal.setLogin(login);
                    personal.setPassword(resultSet.getString("password"));
                    personal.setFirstName(resultSet.getString("first_name"));
                    personal.setLastName(resultSet.getString("last_name"));
                    personal.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
                    return Optional.of(personal);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public long updatePersonal(Personal personal) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(UPDATE_PERSONAL_SQL)) {
            statement.setLong(1, personal.getPersonalId());
            statement.setString(1, personal.getLogin());
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