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

    private static final String CREATE_PERSONAL_SQL = "INSERT INTO medical_personal (login, password, first_name," +
            " last_name, role) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_PERSONAL_BY_LOGIN_SQL = "SELECT personal_id, first_name, last_name, role, " +
            "login, " + "password FROM medical_personal WHERE login = ?;";
    private static final String UPDATE_PERSONAL_SQL = "UPDATE medical_personal SET login = ?, password = ?, " +
            "first_name = ?, " + "last_name = ?, role = ? WHERE medical_personal.personal_id = ?";
    private static final String GET_ALL_PERSONALS_SQL = "SELECT personal_id, first_name, last_name, role, login FROM "
            + "medical_personal";
    private static final String GET_PERSONAL_BY_ID = "SELECT * FROM medical_personal WHERE medical_personal" +
            ".personal_id = ?;";

    private static final Logger log = Logger.getLogger(H2PersonalDao.class.getName());

    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;

    public H2PersonalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long createPersonal(Personal personal) {
        long personalId = 0;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(CREATE_PERSONAL_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, personal.getLogin());
            statement.setString(2, String.valueOf(personal.getPassword()));
            statement.setString(3, personal.getFirstName());
            statement.setString(4, personal.getLastName());
            statement.setString(5, personal.getRole().toString());
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    personalId = resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            log.info("Create personal: " + personal.getLogin() + "; first name: " + personal.getFirstName() + "; " +
                    "last" + " name: " + personal.getLastName() + "; role: " + personal.getRole() + "; status: FAILED");
        }
        return personalId;
    }

    public Optional<Personal> readPersonalByLogin(String login) {
        Optional<Personal> optionalPersonal = null;
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
                    optionalPersonal = Optional.of(personal);
                }
            }
        } catch (SQLException e) {
            log.info("Read personal by login: " + login + "; status: FAILED");
        }
        return optionalPersonal;
    }

    @Override
    public long updatePersonal(Personal personal) {
        long personalId = 0;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(UPDATE_PERSONAL_SQL)) {
            statement.setString(1, personal.getLogin());
            statement.setString(2, personal.getPassword());
            statement.setString(3, personal.getFirstName());
            statement.setString(4, personal.getLastName());
            statement.setString(5, personal.getRole().toString());
            statement.setLong(6, personal.getPersonalId());
            statement.executeUpdate();
            personalId = personal.getPersonalId();
        } catch (SQLException e) {
            log.info("Update personal by ID: " + personal.getPersonalId() + "; login: " + personal.getLogin() + "; "
                    + "first name: " + personal.getFirstName() + "; last name: " + personal.getLastName() + "; role: " +
                    personal.getRole().toString() + "; status: FAILED");
        }
        return personalId;
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
            log.info("Get all personals, personal size: " + personalList.size() + "; status: FAILED");
        }
        return personalList;
    }

    @Override
    public Personal getPersonalById(long personalId) {
        Personal personal = new Personal();
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement =
                connection.prepareStatement(GET_PERSONAL_BY_ID)) {
            statement.setLong(1, personalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                personal = getPersonalFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            log.info("Get personal by ID: " + personalId + "; status: FAILED");
        }
        return personal;
    }

    public Personal getPersonalFromResultSet(ResultSet resultSet) throws SQLException {
        Personal personal = new Personal();
        personal.setPersonalId(resultSet.getLong("personal_id"));
        personal.setLogin(resultSet.getString("login"));
        personal.setFirstName(resultSet.getString("first_name"));
        personal.setLastName(resultSet.getString("last_name"));
        personal.setRole(Role.valueOf(resultSet.getString("role")));
        return personal;
    }

}