package controllers;

import model.Personal;
import services.PersonalService;
import utils.HashGenerator;
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
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/*
Обработка страницы авторизации
 */
@WebServlet("/login")
public class Login extends HttpServlet {

//    private final String ERROR_MESSAGE_EN = "Invalid login or password";
//    private final String ERROR_MESSAGE_RU = "Неверный логин или пароль";
    DataSource dataSource;
    HashGenerator hashGenerator;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("hello get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost method");
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1800);
        Locale locale = (Locale) session.getAttribute("locale");
        System.out.println(locale);
        if (locale == null) {
            locale = new Locale("ru");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password");
        System.out.println(login + " " + password);
        Optional<Personal> currentUser = new PersonalService().authenticatePersonal(login,
                password,dataSource,hashGenerator);

        if (currentUser.isPresent()) {
            request.setAttribute("name", currentUser.get().getFirstName());
            request.setAttribute("surname", currentUser.get().getLastName());
            request.getRequestDispatcher("/main.jsp").forward(request, response);
            String ip = request.getRemoteAddr();
            return;
        }
        else {
            request.setAttribute("loginError", bundle.getString("loginError"));
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
        hashGenerator = (HashGenerator) context.getAttribute("hashGenerator");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
