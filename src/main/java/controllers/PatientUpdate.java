package controllers;

import dao.DaoFactory;
import dao.PatientDao;
import model.Patient;
import model.Personal;
import model.Role;
import utils.StringFieldValidate;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/patientProfile")
public class PatientUpdate extends HttpServlet {

    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            locale = new Locale("ru");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");

        Personal currentUser = (Personal) session.getAttribute("user");

        Long patientId = Long.parseLong(request.getParameter("patientId"));
        PatientDao patientDao = daoFactory.getPatientDao();
        Patient patient = patientDao.getPatient(patientId);
        session.setAttribute("currentPatient", patient);

        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();
        boolean isDeleted = patient.isDeleted();

        String first = request.getParameter("firstName");
        System.out.println(first);

        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(firstName);
        if (isValid) {
            isValid = stringFieldValidate.doValidation(lastName);
        }

        if (isValid) {
            Patient updatePatient = new Patient();
            updatePatient.setFirstName(first);
            updatePatient.setLastName(lastName);
            updatePatient.setDeleted(isDeleted);
            updatePatient.setPatientId(patientId);
//            System.out.println(firstName);
//            System.out.println(lastName);
//            System.out.println(isDeleted);
            patientDao.updatePatient(updatePatient);

            if (currentUser.getRole() == Role.DOCTOR) {
                List<Patient> patients = patientDao.getAllPatients();
                if (patients != null) {
                    session.setAttribute("patients", patients);
                }
                request.getRequestDispatcher("/patientProfile.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/main.jsp").forward(request, response);
            }
        } else {
            String str = bundle.getString("personalError");
            str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            request.setAttribute("personalError", str);
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }


    }

    @Override
    public void destroy() {
        super.destroy();
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

