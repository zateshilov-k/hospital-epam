package controllers;

import model.Personal;
import services.PersonalService;
import utils.LoginValidate;

import javax.servlet.ServletContext;
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
import java.util.Optional;

/*
Обработка страницы авторизации
 */
//TODO 1. бандлы и проперти и локаль   2. хэширование пароля 3. передача роли пользователя на фронт
@WebServlet("/login")
public class Login extends HttpServlet {

    private final String ERROR_MESSAGE_EN = "Invalid login or password";
    private final String ERROR_MESSAGE_RU = "Неверный логин или пароль";
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("doGet method");
        System.out.println("hello get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1800);
        System.out.println("hello post");
        response.setContentType("text/html");
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password");
        System.out.println(login + " " + password);
        Optional<Personal> currentUser = new PersonalService().authenticatePersonal(login, password,dataSource);

        /*request.getRequestDispatcher("/hospital-system/").forward(request, response);

        LoginValidate loginValidate = new LoginValidate();
        boolean result = loginValidate.doValidation(login, password);
        System.out.println("result \t" + result);*/
        request.setAttribute("loginError", ERROR_MESSAGE_EN);
        request.setAttribute("default","MYYYYYYYYYYYYYY");
        if (currentUser.isPresent()) {
            request.setAttribute("name", currentUser.get().getFirstName());
            request.setAttribute("surname", currentUser.get().getLastName());
            request.getRequestDispatcher("/Main.jsp").forward(request, response);
            String ip = request.getRemoteAddr();
            return;
        }
        else {
            request.setAttribute("loginError", ERROR_MESSAGE_EN);
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
        dataSource = (DataSource) context.getAttribute("dataSource");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
