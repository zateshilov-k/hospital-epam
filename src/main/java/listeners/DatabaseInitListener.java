package listeners;

import dao.DaoFactory;
import dao.h2.H2DaoFactory;
import dao.h2.H2PatientDao;
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
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Resource(name = "jdbc/hospital-h2-db")
    private DataSource dataSource;
    private Random random = new Random();
    private HashGenerator hashGenerator;

    public static void printTable(Statement statement, String table) throws SQLException {
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

    private Role getRandomRole() {
        return Role.values()[random.nextInt(Role.values().length-1)];
    }

    private String getRandomDisease() {
        String[] baseDiseases = {"Аднексит", "Аллергия", "Ангина", "Анемия", "Артериальная гипертензия",
                "Артериальная гипотония", "Артрит", "Артроз", "Атеросклероз", "Бессонница", "Боль", "Бронхиальная " +
                "астма", "Бронхит", "Трахеит", "Мастит", "Лактостаз", "Мастопатия", "Метеоризм", "Миалгия", "Мигрень"
                , "Микоз", "Сахарный диабет", "Свинка", "Себорея", "Синусит", "Скарлатина", "Сотрясение головного " +
                "мозга", "Стоматит"};
        return baseDiseases[random.nextInt(baseDiseases.length)];
    }

    private void addPersonal(Statement statement, Personal personal) throws SQLException {
        statement.addBatch("INSERT INTO medical_personal " + "(personal_id, first_name, last_name, role, login, password) VALUES"
                + "(" + (personal.getPersonalId()) + "," + "\'" + personal.getFirstName() + "\'," + "\'" + personal.getLastName()
                + "\'," + "\'"
                + personal.getRole() + "\'," + "\'"
                + personal.getLogin() + "\'," + "\'"
                + personal.getPassword() + "\'" + ");");
    }

    private void addPatients(Statement statement, Patient patient) throws SQLException {
        statement.addBatch("INSERT INTO patient " + "(patient_id, first_name, last_name, is_discharged, is_deleted) VALUES"
                + "(" + (patient.getPatientId()) + "," + "\'" + patient.getFirstName() + "\'," + "\'" + patient.getLastName()
                + "\'," + "FALSE" + ", " + "FALSE"  + ");");
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

    private void addDiagnosis(Statement statement, Diagnosis diagnosis) throws SQLException {
        statement.addBatch("INSERT INTO diagnosis " + "(diagnosis_id, description, personal_id, patient_id, time, is_opened) " +
                "VALUES" + "(" + (diagnosis.getDiagnosisId()) + "," + "\'" + diagnosis.getDescription() + "\',"
                + (diagnosis.getPersonal().getPersonalId())
                + "," + (diagnosis.getPatient().getPatientId()) + ","
                + "\'" + diagnosis.getTime().format(df) + "\'," + "FALSE" + ");");
    }

    private void addPrescription(Statement statement, Prescription prescription) throws SQLException {
        statement.addBatch("INSERT INTO prescription " + "(prescription_id, description, patient_id, time, diagnosis_id," +
                "type, is_done) VALUES" + "(" + (prescription.getPrescriptionId()) + "," + "\'" + "prescription descr"
                + prescription.getDescription() + "\'," + (prescription.getPatient().getPatientId()) + "," + "\'"
                + (prescription.getTime())
                + "\'," + prescription.getDiagnosis().getDiagnosisId() + "," + "\'" + prescription.getType().name() + "\',"
                + prescription.isDone() + ");");
    }

    private void addPersonalPrescription(Statement statement, PersonalPrescription personalPrescription) throws SQLException {
        statement.addBatch("INSERT INTO medical_personal_prescription " + "(personal_prescription_id, type, personal_id," +
                "prescription_id) VALUES" + "("
                + personalPrescription.getPersonalPrescriptionId()
                + "," + "\'" + personalPrescription.getType().toString() + "\',"
                + (personalPrescription.getPersonal().getPersonalId())
                + "," + personalPrescription.getPrescription().getPrescriptionId() + ");");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        hashGenerator = (HashGenerator) servletContextEvent.getServletContext().getAttribute("hashGenerator");
        final int numberOfPersonal = 10;
        final int numberOfPatients = 15;
        final int numberOfDiagnosisPerPatient = 7;
        final int numberOfPrescriptionsPerDiagnosis = 14;

        List<Personal> personals = getPersonals(numberOfPersonal);
        Personal admin = new Personal();
        admin.setFirstName("AdminName");
        admin.setLastName("AdminLastName");
        admin.setLogin("admin@epam.com");
        admin.setRole(Role.ADMIN);
        admin.setPassword(hashGenerator.getHash("admin" ));
        admin.setPersonalId(personals.size()+1);
        personals.add(admin);

        Personal doctor = new Personal();
        doctor.setFirstName("DoctorName");
        doctor.setLastName("DoctorLastName");
        doctor.setLogin("doctor@epam.com");
        doctor.setRole(Role.DOCTOR);
        doctor.setPassword(hashGenerator.getHash("password1"));
        doctor.setPersonalId(personals.size()+1);
        personals.add(doctor);

        Personal nurse = new Personal();
        nurse.setFirstName("NurseName");
        nurse.setLogin("nurse@epam.com");
        nurse.setRole(Role.NURSE);
        nurse.setPassword(hashGenerator.getHash("nurse"));
        nurse.setPersonalId(personals.size()+1);
        personals.add(nurse);

        List<Patient> patients = getPatients(numberOfPatients);
        List<Diagnosis> diagnoses = getDiagnoses(numberOfDiagnosisPerPatient, personals, patients);
        List<Prescription> prescriptions = getPrescriptions(numberOfPrescriptionsPerDiagnosis,diagnoses);
        List<PersonalPrescription> personalPrescriptions = getPersonalPrescriptions(personals, prescriptions);

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement();) {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource("sql/table_creation.sql").getFile();
            path = path.substring(1);
            path = path.replace("/", "\\");
            Path realPath = Paths.get(path).toRealPath();
            statement.addBatch(Files.lines(realPath).collect(Collectors.joining()));
            for (Personal personal : personals) {
                System.out.println("CHECK:\t" + personal);
                addPersonal(statement, personal);
            }
            for (Patient patient : patients) {
                addPatients(statement, patient);
            }
            for (Diagnosis diagnosis : diagnoses) {
                addDiagnosis(statement, diagnosis);
            }
            for (Prescription prescription : prescriptions) {
                addPrescription(statement, prescription);
            }
            for (PersonalPrescription personalPrescription : personalPrescriptions) {
                addPersonalPrescription(statement, personalPrescription);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DaoFactory daoFactory = new H2DaoFactory(dataSource,dateTimeFormatter);

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            printTable(statement, "medical_personal");
            printTable(statement, "patient");
            printTable(statement, "diagnosis");
            printTable(statement, "prescription");
            printTable(statement, "medical_personal_prescription");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletContextEvent.getServletContext().setAttribute("daoFactory",daoFactory);
//        deletePatientTest();
    }

//    private void deletePatientTest() {
//        Patient patient = new H2PatientDao(dataSource).getAllPatients().get(5);
//        System.out.println(patient.getPatientId());
//        System.out.println(patient.getFirstName());
//        System.out.println(patient.getLastName());
//        System.out.println("PATIENT DELETE");
//        new H2PatientDao(dataSource).deletePatient(5);
//    }

    private List<PersonalPrescription> getPersonalPrescriptions(List<Personal> personals, List<Prescription> prescriptions) {
        List<PersonalPrescription> personalPrescriptions = new ArrayList<>();
        prescriptions.forEach(prescription -> {
            if (prescription.isDone()) {
                Personal executor;
                switch (prescription.getType()) {
                    case OPERATION:
                        executor = IntStream
                                .generate(() -> random.nextInt(personals.size()))
                                .mapToObj(j -> personals.get(j))
                                .filter((p) -> p.getRole() == Role.DOCTOR)
                                .findAny().get();
                        break;
                    case DRUG:
                    case PROCEDURE:
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
                                PersonalPrescriptionType.DONE,
                                executor,
                                prescription
                        )
                );
            }
            personalPrescriptions.add(new PersonalPrescription(
                    personalPrescriptions.size() + 1,
                    PersonalPrescriptionType.PRESCRIBE,
                    prescription.getDiagnosis().getPersonal(),
                    prescription
            ));
        });
        return personalPrescriptions;
    }

    private List<Prescription> getPrescriptions(int numberOfPrescriptionsPerDiagnosis, List<Diagnosis> diagnoses) {
        List<Prescription> prescriptions = new ArrayList<>();
        diagnoses.forEach(diagnosis -> {
            for (int i = 0; i < numberOfPrescriptionsPerDiagnosis; i++) {
                Prescription firstPrescription = getRandomPrescription(prescriptions.size() + 1, diagnosis);
                prescriptions.add(firstPrescription);
            }
        });
        return prescriptions;
    }

    private List<Diagnosis> getDiagnoses(int numberOfDiagnosisPerPatient, List<Personal> personals, List<Patient> patients) {
        List<Diagnosis> diagnoses = new ArrayList<>();
        patients.forEach(patient -> {
            for (int i = 0; i < numberOfDiagnosisPerPatient; i++) {
                Personal doctor = IntStream
                        .generate(() -> random.nextInt(personals.size()))
                        .mapToObj(j -> personals.get(j))
                        .filter((p) -> p.getRole() == Role.DOCTOR)
                        .findAny().get();
                diagnoses.add(getRandomDiagnosis(diagnoses.size() + 1, patient, doctor));
            }
        });
        return diagnoses;
    }

    // id patients
    private List<Patient> getPatients(int numberOfPatients) {
        return IntStream.range(0, numberOfPatients)
                .mapToObj((i) -> getRandomPatient(i + 1))
                .collect(Collectors.toList());
    }

    private List<Personal> getPersonals(int numberOfPersonal) {
        List<Personal> personals = IntStream.range(1, numberOfPersonal)
                .mapToObj((i) -> getRandomPersonal(i, false))
                .collect(Collectors.toList());
        personals.add(getRandomPersonal(personals.size() + 1, true));
        return personals;
    }

    private Prescription getRandomPrescription(int id, Diagnosis diagnosis) {
        return new Prescription(
                id,
                "Prescription descr" + id,
                diagnosis.getPatient(),
                diagnosis,
                random.nextBoolean(),
                getRandomDateAfter(diagnosis.getTime()),
                getRandomPrescriptionType()
        );
    }

    private PrescriptionType getRandomPrescriptionType() {
        PrescriptionType[] prescriptionTypes = PrescriptionType.values();
        return prescriptionTypes[random.nextInt(prescriptionTypes.length)];
    }

    private LocalDateTime getRandomDateAfter(LocalDateTime time) {
        return time.plusDays(random.nextInt(30));
    }

    private Diagnosis getRandomDiagnosis(int id, Patient patient, Personal doctor) {
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
                false,
                false
        );
    }

    private Personal getRandomPersonal(int id, boolean isDoctor) {
        Role role;
        if (isDoctor) {
            role = Role.DOCTOR;
        } else {
            role = getRandomRole();
        }
        return new Personal(
                id,
                "login" + id + "@epam.com",
                hashGenerator.getHash("password" + id),
                getRandomFirstName(),
                getRandomLastName(),
                role
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}