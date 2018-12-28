package listeners;

import model.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebListener
public class DatabaseInitListener implements ServletContextListener {
    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;
    private Random random = new Random();
    private HashGenerator hashGenerator;

    private void printTable(Statement statement, String table) throws SQLException {
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

    private String getRandomFirstName() {
        String[] baseNames = {"Александр", "Артем", "Максим", "Михаил", "Иван", "Даниил", "Дмитрий", "Матвей",
                "Андрей", "Кирилл"};
        return baseNames[random.nextInt(baseNames.length)];
    }

    private String getRandomLastName() {
        String[] baseSurnames = {"Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев", "Козлов", "Новиков",
                "Морозов", "Петров", "Волков"};
        return baseSurnames[random.nextInt(baseSurnames.length)];
    }

    private String getRandomRole() {
        String[] baseRoles = {"Доктор", "Медбрат"};
        return baseRoles[random.nextInt(baseRoles.length)];
    }

    private String getRandomDisease() {
        String[] baseDiseases = {"Аднексит", "Аллергия", "Ангина", "Анемия", "Артериальная гипертензия",
                "Артериальная гипотония", "Артрит", "Артроз", "Атеросклероз", "Бессонница", "Боль", "Бронхиальная " +
                "астма", "Бронхит", "Трахеит", "Мастит", "Лактостаз", "Мастопатия", "Метеоризм", "Миалгия", "Мигрень"
                , "Микоз", "Сахарный диабет", "Свинка", "Себорея", "Синусит", "Скарлатина", "Сотрясение головного " +
                "мозга", "Стоматит"};
        return baseDiseases[random.nextInt(baseDiseases.length)];
    }

    private String getRandomPrescriptionString() {
        String[] basePrescriptions = {"Операция", "Процедура", "Лекарство"};
        return basePrescriptions[random.nextInt(basePrescriptions.length)];
    }

    private void addMedPersonal(Statement statement, int personalId, Random random, String randomRole,
                                HashGenerator hashGenerator) throws SQLException {
        statement.addBatch("INSERT INTO medical_personal " + "(personal_id, first_name, last_name, role, login, password) VALUES"
                + "(" + (personalId) + "," + "\'" + getRandomFirstName() + "\'," + "\'" + getRandomLastName()
                + "\'," + "\'" + randomRole + "\'," + "\'" + "login"
                + personalId + "@epam.com" + "\'," + "\'"
                + hashGenerator.getHash("password1") + "\'" + ");");
        //System.out.println(hashGenerator.getHash("password1"));
    }

    private void addPatients(Statement statement, int patientId, Random random, String isDischarged) throws SQLException {
        statement.addBatch("INSERT INTO patient " + "(patient_id, first_name, last_name, is_discharged) VALUES"
                + "(" + (patientId) + "," + "\'" + getRandomFirstName() + "\'," + "\'" + getRandomLastName()
                + "\'," + isDischarged + ");");
    }

    private String getRandomDate() {
        Instant now = Instant.now();
        LocalDateTime dt = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        dt.plusDays(random.nextInt(30));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return dt.format(df);
    }

    private LocalDateTime getRandomDate2() {
        Instant now = Instant.now();
        LocalDateTime dt = LocalDateTime.ofInstant(now, ZoneOffset.UTC);
        dt = dt.plusDays(random.nextInt(30));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return dt;
    }

    private void addDiagnosis(Statement statement, int diagnosisId, int personalId, int patientId,
                              Random random) throws SQLException {

        statement.addBatch("INSERT INTO diagnosis " + "(diagnosis_id, description, personal_id, patient_id, time, is_healthy) " +
                "VALUES" + "(" + (diagnosisId) + "," + "\'" + getRandomDisease() + "\',"
                + (personalId) + "," + (patientId) + "," + "\'" + getRandomDate() + "\'," + "FALSE" + ");");
    }

    private void addPrescription(Statement statement, int currentPrescriptionId, int currentPatientId,
                                 int currentDiagnosisId, boolean isDone, Random random) throws SQLException {
        statement.addBatch("INSERT INTO prescription " + "(prescription_id, description, patient_id, time, diagnosis_id," +
                "type, is_done) VALUES" + "(" + (currentPrescriptionId) + "," + "\'" + "prescription descr"
                + currentPrescriptionId + "\'," + (currentPatientId) + "," + "\'" + (getRandomDate())
                + "\'," + currentDiagnosisId + "," + "\'" + getRandomPrescriptionString() + "\',"
                + (Boolean.valueOf(isDone).toString()).toUpperCase() + ");");
    }

    private void addPersonalPrescription(Statement statement, int id, int personalId, int prescriptionId,
                                         String type) throws SQLException {
        statement.addBatch("INSERT INTO medical_personal_prescription " + "(personal_prescription_id, type, personal_id," +
                "prescription_id) VALUES" + "(" + id + "," + "\'" + type + "\'," + (personalId) + "," + prescriptionId + ");");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        hashGenerator = (HashGenerator) servletContextEvent.getServletContext().getAttribute("hashGenerator");
        final int numberOfPersonal = 2;
        final int numberOfPatients = 2;
        final int numberOfDiagnosisPerPatient = 1;
        final int numberOfPrescriptionsPerDiagnosis = 2;

        List<Personal> personals = IntStream.range(1, numberOfPersonal)
                .mapToObj((i) -> getRandomPersonal(i, false))
                .collect(Collectors.toList());
        personals.add(getRandomPersonal(personals.size(), true));

        List<Patient> patients = IntStream.range(1, numberOfPatients)
                .mapToObj((i) -> getRandomPatient(i))
                .collect(Collectors.toList());
        List<Diagnosis> diagnoses = new ArrayList<>();
        int diagnosisId = 0;
        patients.forEach(patient -> {
            for (int i = 0; i < numberOfDiagnosisPerPatient; i++) {
                Personal doctor = IntStream
                        .generate(() -> random.nextInt(personals.size()))
                        .mapToObj(j -> personals.get(j))
                        .filter((p) -> p.getRole() == Role.ДОКТОР)
                        .findAny().get();
                diagnoses.add(getRandomDiagonsis(diagnoses.size() + 1, patient, doctor));
            }
        });

        List<Prescription> prescriptions = new ArrayList<>();
        diagnoses.forEach(diagnosis -> {
            for (int i = 0; i < numberOfPrescriptionsPerDiagnosis; i++) {
                Prescription firstPrescription = getRandomPrescription(prescriptions.size() + 1, diagnosis);
                prescriptions.add(firstPrescription);
            }
        });

        List<PersonalPrescription> personalPrescriptions = new ArrayList<>();
        prescriptions.forEach(prescription -> {
            if (prescription.isDone()) {
                Personal executor;
                switch (prescription.getType()) {
                    case ОПЕРАЦИЯ:
                        executor = IntStream
                                .generate(() -> random.nextInt(personals.size()))
                                .mapToObj(j -> personals.get(j))
                                .filter((p) -> p.getRole() == Role.ДОКТОР)
                                .findAny().get();
                        break;
                    case ЛЕКАРСТВО:
                    case ПРОЦЕДУРА:
                        executor = IntStream
                                .generate(() -> random.nextInt(personals.size()))
                                .mapToObj(j -> personals.get(j))
                                .findAny().get();
                        break;
                    default: {
                        throw new IllegalArgumentException("Wrongn PersonalPrescriptionType");
                    }
                }
                personalPrescriptions.add(new PersonalPrescription(
                                personalPrescriptions.size() + 1,
                                PersonalPrescriptionType.ВЫПОЛНИЛ,
                                executor,
                                prescription
                        )
                );
            }
            personalPrescriptions.add(new PersonalPrescription(
                    personalPrescriptions.size() + 1,
                    PersonalPrescriptionType.НАЗНАЧИЛ,
                    prescription.getDiagnosis().getPersonal(),
                    prescription
            ));
        });
        personals.forEach(System.out::println);
        patients.forEach(System.out::println);
        diagnoses.forEach(System.out::println);
        prescriptions.forEach(System.out::println);
        personalPrescriptions.forEach(System.out::println);
    }

    private PersonalPrescriptionType getRandomPersonalPrescriptionType() {
        PersonalPrescriptionType[] personalPrescriptionTypes = PersonalPrescriptionType.values();
        return personalPrescriptionTypes[random.nextInt(personalPrescriptionTypes.length)];
    }

    private Prescription getRandomPrescription(int id, Diagnosis diagnosis) {
        return new Prescription(
                id,
                getRandomPrescriptionString(),
                diagnosis.getPatient(),
                diagnosis,
                random.nextBoolean(),
                getRandomDateAfter(diagnosis.getTime()),
                getRandomPrecriptionType()
        );
    }

    private PrescriptionType getRandomPrecriptionType() {
        PrescriptionType[] prescriptionTypes = PrescriptionType.values();
        return prescriptionTypes[random.nextInt(prescriptionTypes.length)];
    }

    private LocalDateTime getRandomDateAfter(LocalDateTime time) {
        time = time.plusDays(random.nextInt(30));
        return time;
    }

    private Diagnosis getRandomDiagonsis(int id, Patient patient, Personal doctor) {
        return new Diagnosis(
                id,
                getRandomDisease(),
                doctor,
                patient,
                false,
                getRandomDate2()
        );
    }

    private Patient getRandomPatient(int id) {
        return new Patient(
                id,
                getRandomFirstName(),
                getRandomLastName(),
                false
        );
    }

    private Personal getRandomPersonal(int id, boolean isDoctor) {
        Role role;
        if (isDoctor) {
            role = Role.ДОКТОР;
        } else {
            role = Role.valueOf(getRandomRole().toUpperCase());
        }
        return new Personal(
                id,
                "login" + id + "epam.com",
                hashGenerator.getHash("password" + id),
                getRandomFirstName(),
                getRandomLastName(),
                role
        );
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public void temp(ServletContextEvent servletContextEvent) {
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
                String randomRole = getRandomRole();
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
            printTable(statement, "medical_personal");
            printTable(statement, "patient");
            printTable(statement, "diagnosis");
            printTable(statement, "prescription");
            printTable(statement, "medical_personal_prescription");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database init ended");
        servletContextEvent.getServletContext().setAttribute("dataSource", dataSource);

    }
}