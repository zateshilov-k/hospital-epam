package services;
import dao.h2.H2PatientDao;
import model.Patient;

import javax.sql.DataSource;
import java.util.List;

public class PatientService {
    private DataSource dataSource;
    public List<Patient> getAllPatients(DataSource dataSource){
        H2PatientDao h2PatientDao = new H2PatientDao(dataSource);
        return h2PatientDao.getAllPatients();
    }
}
