package controllers;

import dao.DaoFactory;
import dao.PatientDao;
import dao.h2.H2PatientDao;
import model.Diagnosis;
import model.Patient;
import model.Personal;
import model.Role;
import org.json.JSONArray;
import services.PersonalService;
import utils.HashGenerator;
import utils.StringFieldValidate;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@WebServlet("/patientProfileUpdate")
public class PatientUpdate extends HttpServlet {

    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Patient Card GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=utf-8");

        Long patientId = Long.parseLong(request.getParameter("patientId"));
        PatientDao patientDao = daoFactory.getPatientDao();
        Patient patient = patientDao.getPatient(patientId);
        session.setAttribute("currentPatient", patient);


    }

    @Override
    public void destroy() {
//        super.destroy();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }


}

