package controllers;

import dao.DaoFactory;
import dao.h2.H2DiagnosisDao;
import dao.h2.H2PatientDao;
import model.Diagnosis;
import model.Patient;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/listofdiagnosis")
public class ListOfDiagnosis extends HttpServlet {

    // Добавить в DAO метод выборки - все диагнозы пациента
    DataSource dataSource;
    DaoFactory daoFactory;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patientList = new H2PatientDao(dataSource).getAllPatients();
        Patient patient1 = patientList.get(10);
        System.out.println("OUR PATIENT");
        System.out.println(patient1.getPatientId() + " " + patient1.getFirstName() + " " + patient1.getLastName() + " " + patient1.isDischarged());
        List<Diagnosis> diagnosisListForOnePatient = daoFactory.getDiagnosisDao().getAllDiagnosesByPatientId(patient1.getPatientId());
        for (int i = 0; i < diagnosisListForOnePatient.size(); i++) {
            System.out.println(diagnosisListForOnePatient.get(i));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = (DataSource) context.getAttribute("dataSource");
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

}