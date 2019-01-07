package dao.h2;

import dao.PersonalDao;
import model.Personal;
import model.Role;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class H2PersonalDao implements PersonalDao {

    private static final String CREATE_PERSONAL_SQL = "INSERT INTO medical_personal (personal_id, login, password, first_name," +
            " last_name, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PERSONAL_BY_LOGIN_SQL = "SELECT personal_id, first_name, last_name, role, login, " +
            "password FROM medical_personal WHERE login = ?;";
    private static final String UPDATE_PERSONAL_SQL = "UPDATE medical_personal SET login = ?, password = ?, firstName = ?, " +
            "lastName = ?, role = ? WHERE personalId = ?";
    private static final String GET_ALL_PERSONALS_SQL = "SELECT personal_id, first_name, last_name, role, login FROM " +
            "medical_personal";
    private static final String GET_PERSONAL_BY_ID = "SELECT * FROM medical_personal WHERE medical_personal.personal_id = ?;";

    private DataSource dataSource;
    private static final Logger log = Logger.getLogger(String.valueOf(H2PersonalDao.class));

    public H2PersonalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createPersonal(Personal personal) {
        System.out.println("H2PersonalDao - какой новый personal к нам приходит:");
        System.out.println(personal);
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(CREATE_PERSONAL_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, personal.getPersonalId());

            statement.setString(2, personal.getLogin());
            statement.setString(3, String.valueOf(personal.getPassword()));
            statement.setString(4, personal.getFirstName());
            statement.setString(5, personal.getLastName());
            statement.setString(6, personal.getRole().toString());
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("creating personal - error");
        }
        return 0;
    }

    public Optional<Personal> readPersonalByLogin(String login) {
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
            log.warning(e.getMessage());
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

    @Override
    public List<Personal> getAllPersonals() {
        List<Personal> personalList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_ALL_PERSONALS_SQL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    personalList.add(getPersonalFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
            System.err.println(e.getMessage());
        }
        return personalList;
    }

    @Override
    public Personal getPersonalById(long personalId) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_PERSONAL_BY_ID)) {
            statement.setLong(1, personalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return getPersonalFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Personal getPersonalFromResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("getPersonalFromResultSet");
        Personal personal = new Personal();
        personal.setPersonalId(resultSet.getLong("personal_id"));
        personal.setFirstName(resultSet.getString("first_name"));
        personal.setLastName(resultSet.getString("last_name"));
        return personal;
    }
}