package controllers;

import dao.DaoFactory;
import dao.PersonalDao;
import model.Personal;
import services.PersonalService;
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
сервлет для личного кабинета персонала
 */
@WebServlet("/personalUpdate")
public class PersonalCabinet extends HttpServlet {
    DaoFactory daoFactory;
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
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        //TODO сделать ограничение на изменение роли только для роли АДМИН
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
            //TODO update personal
            //TODO write code here


            List<Personal> personals = new PersonalService().getAllPersonals(daoFactory);
            if (personals != null) {
                session.setAttribute("personals", personals);
            }
            request.getRequestDispatcher("/personals.jsp").forward(request, response);
            System.out.println("Personal is updated \t" + firstName+" "+ lastName);
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
    }
}
