package controllers;

import dao.DaoFactory;
import dao.PatientDao;
import model.Patient;
import model.Personal;
import services.PatientService;
import utils.StringFieldValidate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/*
сервлет, отрабатывающий желание добавить пациента
 */
@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            locale = new Locale("ru");
        }
        session.setAttribute("locale", locale);
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        String firstName = request.getParameter("firstName").trim();
        firstName = new String(firstName.getBytes("ISO-8859-1"), "UTF-8");
        String lastName = request.getParameter("lastName").trim();
        lastName = new String(lastName.getBytes("ISO-8859-1"), "UTF-8");
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(firstName);
        if (isValid) {
            isValid = stringFieldValidate.doValidation(lastName);
        }
        if (isValid) {
            PatientDao patientDao = daoFactory.getPatientDao();
            Patient newPatient = new Patient();
            newPatient.setFirstName(firstName);
            newPatient.setLastName(lastName);
            newPatient.setDischarged(false);
            patientDao.addPatient(newPatient);
            List<Patient> patients = new PatientService().getAllPatients(daoFactory);
            if (patients != null) {
                Personal currentUser = (Personal)session.getAttribute("user");
                session.setAttribute("user", currentUser);
                session.setAttribute("patients", patients);
            }

            request.getRequestDispatcher("/main.jsp").forward(request, response);
        } else {
            String str = bundle.getString("patientError");
            str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            request.setAttribute("patientError", str);
            request.getRequestDispatcher("/patient.jsp").forward(request, response);
            return;
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
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
