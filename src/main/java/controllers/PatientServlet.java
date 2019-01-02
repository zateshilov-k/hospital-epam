package controllers;

import model.Patient;
import services.PatientService;
import utils.StringFieldValidate;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addPatient")
public class PatientServlet extends HttpServlet {
    DataSource dataSource;

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
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(firstName);
        if (isValid) {
            isValid = stringFieldValidate.doValidation(lastName);
        }
        if (isValid) {
            //TODO add patient
            // Patient patient = new PatientService().addPatient(firstName, lastName);
        } else {
            String str = bundle.getString("patientError");
            str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            request.setAttribute("patientError", str);
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
}
