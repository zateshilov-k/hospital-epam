package controllers;

import com.google.gson.GsonBuilder;
import dao.DaoFactory;
import dao.h2.H2DiagnosisDao;
import dao.h2.H2PatientDao;
import model.Diagnosis;
import model.Patient;
import model.Prescription;
import model.PrescriptionType;
import org.json.JSONArray;
import utils.DiagnosisTypeAdapter;

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
import java.util.Locale;

@WebServlet("/listofdiagnosis")
public class ListOfDiagnosis extends HttpServlet {

    // Добавить в DAO метод выборки - все диагнозы пациента
    DataSource dataSource;
    DaoFactory daoFactory;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Patient currentPatient = (Patient) request.getSession().getAttribute("currentPatient");
        List<Diagnosis> diagnosisListForOnePatient = daoFactory.getDiagnosisDao().getAllDiagnosesByPatientId(currentPatient.getPatientId());
        response.setContentType("text/html;charset=utf-8");
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        GsonBuilder gsonBuilder = new GsonBuilder();
        DiagnosisTypeAdapter.locale = locale;
        gsonBuilder.registerTypeAdapter(Diagnosis.class,new DiagnosisTypeAdapter());

        response.getWriter().write(gsonBuilder.create().toJson(diagnosisListForOnePatient));
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