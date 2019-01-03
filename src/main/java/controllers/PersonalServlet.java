package controllers;

import utils.SignUpValidate;
import utils.StringFieldValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/addPersonal")
public class PersonalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("SignUp");
//        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            locale = new Locale("ru");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(firstName);
        //TODO сделать проверку на совпадение логина
        if (isValid) {
            isValid = stringFieldValidate.doValidation(lastName);
            if (isValid) {
                isValid = stringFieldValidate.doValidation(login);
                if (isValid) {
                    isValid = stringFieldValidate.doValidation(password);
                    if (isValid) {
                        isValid = stringFieldValidate.doValidation(role);
                    }
                }
            }
        }
        if (isValid) {
            //TODO add personal
            // Patient patient = new PatientService().addPatient(firstName, lastName);
            request.getRequestDispatcher("/main.jsp").forward(request, response);
            System.out.println("Personal is added \t" + firstName+" "+ lastName);
        } else {
            String str = bundle.getString("personalError");
            str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            request.setAttribute("personalError", str);
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}