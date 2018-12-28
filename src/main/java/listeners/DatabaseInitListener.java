package listeners;

import utils.HashGenerator;

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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        String[] baseNames = {"Александр", "Артем", "Максим", "Михаил", "Иван", "Даниил", "Дмитрий", "Матвей",
                "Андрей", "Кирилл"};
        return baseNames[random.nextInt(baseNames.length)];
    }

    private static String getRandomSurname(Random random) {
        String[] baseSurnames = {"Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов", "Новиков",
                "Морозов", "Петров", "Волков"};
        return baseSurnames[random.nextInt(baseSurnames.length)];
    }

    private static String getRandomRole(Random random) {
        String[] baseRoles = {"Доктор", "Медбрат"};
        return baseRoles[random.nextInt(baseRoles.length)];
    }

    private static String getRandomDisease(Random random) {
        String[] baseDiseases = {"Аднексит", "Аллергия", "Ангина", "Анемия", "Артериальная гипертензия",
                "Артериальная гипотония", "Артрит", "Артроз", "Атеросклероз", "Бессонница", "Боль", "Бронхиальная " +
                "астма", "Бронхит", "Трахеит", "Мастит", "Лактостаз", "Мастопатия", "Метеоризм", "Миалгия", "Мигрень"
                , "Микоз", "Сахарный диабет", "Свинка", "Себорея", "Синусит", "Скарлатина", "Сотрясение головного " +
                "мозга", "Стоматит"};
        return baseDiseases[random.nextInt(baseDiseases.length)];
    }

    private static String getRandomPrescription(Random random) {
        String[] basePescriptions = {"Операция", "Процедура", "Лекарство"};
        return basePescriptions[random.nextInt(basePescriptions.length)];
    }

    private static void addMedPersonal(Statement statement, int personalId, Random random, String randomRole,
                                       HashGenerator hashGenerator) throws SQLException {
        statement.addBatch("INSERT INTO Med_personal " + "(personalId,firstName,lastName,role,login,password) VALUES"
                + "(" + (personalId) + "," + "\'" + getRandomName(random) + "\'," + "\'" + getRandomSurname(random)
                + "\'," + "\'" + randomRole + "\'," + "\'" + "login"
                + personalId + "@epam.com" + "\'," + "\'"
                + hashGenerator.getHash("password1") + "\'" + ");");
        //System.out.println(hashGenerator.getHash("password1"));
    }

    private static void addPatients(Statement statement, int patientId, Random random, String isDischarged) throws SQLException {
        statement.addBatch("INSERT INTO Patient " + "(patientId,firstName,lastName,isDischarged) VALUES"
                + "(" + (patientId) + "," + "\'" + getRandomName(random) + "\'," + "\'" + getRandomSurname(random)
                + "\'," + isDischarged + ");");
    }

    private static String getRandomDate(Random random) {
        Instant now = Instant.now();
        LocalDateTime dt = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        dt.plusDays(random.nextInt(30));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return dt.format(df);
    }

    private static void addDiagnosis(Statement statement, int diagnosisId, int personalId, int patientId,
                                     Random random) throws SQLException {

        statement.addBatch("INSERT INTO Diagnosis " + "(diagnosisId,description,personalId,patientId,time,isHealthy) " +
                "VALUES" + "(" + (diagnosisId) + "," + "\'" + getRandomDisease(random) + "\',"
                + (personalId) + "," + (patientId) + "," + "\'" + getRandomDate(random) + "\'," + "FALSE" + ");");
    }

    private static void addPrescription(Statement statement, int currentPrescriptionId, int currentPatientId,
                                        int currentDiagnosisId, boolean isDone, Random random) throws SQLException {
        statement.addBatch("INSERT INTO Prescription " + "(prescriptionId,description,patientId,time,diagnosisId," +
                "type,isDone) VALUES" + "(" + (currentPrescriptionId) + "," + "\'" + "Prescription descr"
                + currentPrescriptionId + "\'," + (currentPatientId) + "," + "\'" + (getRandomDate(random))
                + "\'," + currentDiagnosisId + "," + "\'" + getRandomPrescription(random) + "\',"
                + (Boolean.valueOf(isDone).toString()).toUpperCase() + ");");
    }

    private void addPersonalPrescription(Statement statement, int id, int personalId, int prescriptionId,
                                         String type) throws SQLException {
        statement.addBatch("INSERT INTO Med_personal_Prescription " + "(personalPrescriptionId,Type,personalId," +
                "prescriptionId) VALUES" + "(" + id + "," + "\'" + type + "\'," + (personalId) + "," + prescriptionId + ");");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HashGenerator hashGenerator = (HashGenerator) servletContextEvent.getServletContext().getAttribute(
                "hashGenerator");
        System.out.println("Database init started");
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource("sql/table_creation.sql").getFile();
            path = path.substring(1);
            path = path.replace("/", "\\");
            Path realPath = Paths.get(path).toRealPath();
            statement.addBatch(Files.lines(realPath).collect(Collectors.joining()));
            final int numberOfMedPersonal = 5;
            final int numberOfPatients = 10;
            final int numberOfDiagnosis = 3;
            final int numberOfPrescriptions = 2;
            Random random = new Random();
            for (int medPersonalId = 1; medPersonalId <= numberOfMedPersonal; ++medPersonalId) {
                String randomRole = getRandomRole(random);
                addMedPersonal(statement, medPersonalId, random, randomRole, hashGenerator);

                for (int patientId = 1; patientId <= numberOfPatients; ++patientId) {
                    String isDischarged = (random.nextBoolean() + "").toUpperCase();
                    int currentPatientId = patientId + (medPersonalId - 1) * numberOfPatients;
                    addPatients(statement, currentPatientId, random, isDischarged);

                    for (int diagnosisId = 1; diagnosisId <= numberOfDiagnosis; ++diagnosisId) {
                        int currentDiagnosisId =
                                diagnosisId + (patientId - 1) * numberOfDiagnosis + (medPersonalId - 1)
                                        * numberOfDiagnosis * numberOfPatients;
                        addDiagnosis(statement, currentDiagnosisId, medPersonalId, currentPatientId, random);

                        for (int prescriptionId = 1; prescriptionId <= numberOfPrescriptions; ++prescriptionId) {
                            int currentPrescriptionId =
                                    prescriptionId + (diagnosisId - 1) * numberOfPrescriptions + (patientId - 1) *
                                            numberOfDiagnosis * numberOfPrescriptions + (medPersonalId - 1)
                                            * numberOfPatients * numberOfDiagnosis * numberOfPrescriptions;
                            boolean isPrescriptionDone = random.nextBoolean();

                            addPrescription(statement, currentPrescriptionId, currentPatientId, currentDiagnosisId,
                                    isPrescriptionDone, random);
                            for (int medPersonalPrescriptionId = 1; medPersonalPrescriptionId <= 2; ++medPersonalPrescriptionId) {
                                int currentMedPersonalPrescriptionId =
                                        medPersonalPrescriptionId + (prescriptionId - 1) * 2 + (diagnosisId - 1)
                                                * numberOfPrescriptions * 2 + (patientId - 1) * numberOfDiagnosis
                                                * numberOfPrescriptions * 2 + (medPersonalId - 1) * numberOfPatients
                                                * numberOfDiagnosis * numberOfPrescriptions * 2;
                                if (medPersonalPrescriptionId == 1) {
                                    addPersonalPrescription(statement, currentMedPersonalPrescriptionId,
                                            medPersonalId, currentPrescriptionId, "Назначено");
                                } else if (isPrescriptionDone) {
                                    addPersonalPrescription(statement, currentMedPersonalPrescriptionId,
                                            medPersonalId, currentPrescriptionId, "Выполнено");
                                }
                            }
                        }
                    }
                }
            }

            int[] ints = statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            printTable(statement, "Med_personal");
            printTable(statement, "Patient");
            printTable(statement, "Diagnosis");
            printTable(statement, "Prescription");
            printTable(statement, "Med_personal_Prescription");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database init ended");
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
