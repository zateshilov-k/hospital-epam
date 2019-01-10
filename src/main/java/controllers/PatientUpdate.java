package controllers;

import dao.DaoFactory;
import dao.PatientDao;
import dao.h2.H2PatientDao;
import model.Patient;
import model.Personal;
import model.Role;
import services.PersonalService;
import utils.HashGenerator;
import utils.StringFieldValidate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
    HashGenerator hashGenerator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("currentPatient");
        System.out.println(patient.getFirstName());
        System.out.println(patient.getLastName());
        System.out.println(patient.isDeleted());
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
        hashGenerator = (HashGenerator) context.getAttribute("hashGenerator");
    }

}

