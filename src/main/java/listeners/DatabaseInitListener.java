package listeners;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Random;
import java.util.stream.Collectors;

@WebListener
public class DatabaseInitListener implements ServletContextListener {
    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;

    private static void printTable(Statement statement, String table) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; ++i) {
                System.out.print(resultSet.getString(i) + ",");
            }
            System.out.println();
        }
    }

    private static String getRandomName(Random random) {
        String[] baseNames = {"Александр", "Артем", "Максим",
                "Михаил", "Иван", "Даниил", "Дмитрий", "Матвей", "Андрей", "Кирилл"};
        return baseNames[random.nextInt(baseNames.length)];
    }

    private static String getRandomSurname(Random random) {
        String[] baseSurnames = {"Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев",
                "Козлов", "Новиков", "Морозов", "Петров", "Волков"};
        return baseSurnames[random.nextInt(baseSurnames.length)];
    }

    private static String getRandomRole(Random random) {
        String[] baseRoles = {"Доктор", "Медбрат"};
        return baseRoles[random.nextInt(baseRoles.length)];
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Database init started");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            Path path = Paths.get(servletContextEvent.getServletContext().getRealPath("sql/"));
            Files.walk(path).filter(Files::isRegularFile).forEach(currentPath -> {
                try {
                    statement.addBatch(Files.lines(currentPath).collect(Collectors.joining()));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            int numberOfRows = 5;
            Random random = new Random();
            for (int i = 1; i <= numberOfRows; ++i) {
                statement.addBatch("INSERT INTO Med_personal " +
                        "(idMed_personal,name,surname,role,creds) VALUES" +
                        "(" + i + "," +
                        "\'" + getRandomName(random) + "\'," +
                        "\'" + getRandomSurname(random) + "\'," +
                        "\'" + getRandomRole(random) + "\'," +
                        "\'****\');");
            }

            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();) {
            printTable(statement, "Med_personal");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database init ended");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
