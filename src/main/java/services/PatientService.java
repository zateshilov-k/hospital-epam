package services;
import dao.DaoFactory;
import dao.h2.H2PatientDao;
import model.Patient;

import javax.sql.DataSource;
import java.util.List;

public class PatientService {
    private DataSource dataSource;
    public List<Patient> getAllPatients(DaoFactory daoFactory){
        //H2PatientDao h2PatientDao = new H2PatientDao(dataSource);
        return daoFactory.getPatientDao().getAllPatients();
    }
}
