package controllers;

import dao.DaoFactory;
import dao.PersonalDao;
import model.Personal;
import model.Role;
import utils.HashGenerator;
import utils.StringFieldValidate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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
сервлет для личного кабинета персонала (update)
 */
@WebServlet("/personalUpdate")
public class PersonalUpdate extends HttpServlet {
    DaoFactory daoFactory;
    HashGenerator hashGenerator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
        Personal currentUser = (Personal) session.getAttribute("user");
        Long personalId = Long.parseLong(request.getParameter("personalId"));
        String firstName = request.getParameter("firstName").trim();
        firstName = new String(firstName.getBytes("ISO-8859-1"), "UTF-8");
        String lastName = request.getParameter("lastName").trim();
        lastName = new String(lastName.getBytes("ISO-8859-1"), "UTF-8");
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(firstName);
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
            PersonalDao personalDao = daoFactory.getPersonalDao();
            Personal updatedPersonal = new Personal();
            updatedPersonal.setFirstName(firstName);
            updatedPersonal.setLastName(lastName);
            updatedPersonal.setLogin(login);
            if (role.equals("doctor")) {
                updatedPersonal.setRole(Role.DOCTOR);
            } else if (role.equals("admin")) {
                updatedPersonal.setRole(Role.ADMIN);
            } else if (role.equals("nurse")) {
                updatedPersonal.setRole(Role.NURSE);
            }
            updatedPersonal.setPassword(hashGenerator.getHash(password));
            updatedPersonal.setPersonalId(personalId);

            personalDao.updatePersonal(updatedPersonal);

            if (currentUser.getRole() == Role.ADMIN) {
                List<Personal> personals = personalDao.getAllPersonals();
                if (personals != null) {
                    session.setAttribute("personals", personals);
                }
                request.getRequestDispatcher("/personals.jsp").forward(request, response);
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
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
        hashGenerator = (HashGenerator) context.getAttribute("hashGenerator");
    }
}
