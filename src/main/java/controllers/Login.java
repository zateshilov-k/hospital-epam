package controllers;

import services.LoginValidate;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
Обработка страницы авторизации
 */
//TODO 1. бандлы и проперти и локаль   2. хэширование пароля 3. передача роли пользователя на фронт
@WebServlet("/login")
public class Login extends HttpServlet {
    private String password;
    private String login;
    private final String ERROR_MESSAGE_EN = "Invalid login or password";
    private final String ERROR_MESSAGE_RU = "Неверный логин или пароль";
//    private DaoFactory daoFactory;
//private static final Logger log = LoggerFactory.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("doGet method");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(1800);

        Locale locale = (Locale) session.getAttribute("locale");
        System.out.println(locale);
        if (locale == null) {
            locale = new Locale("ru");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        System.out.println("doPost method");
        // получаем параметр login
        login = request.getParameter("login").trim();
        // получаем параметр password
        password = request.getParameter("password");
        System.out.println(login + " " + password);
//TODO захэшировать пароль

        LoginValidate loginValidate = new LoginValidate();
        boolean result = loginValidate.doValidation(login, password);
        System.out.println("result \t" + result);
        if (result) {
//            User user = daoFactory.getUserDao().readUserByLogin(login);
//            if (user!=null) {
//                // если хэшкод пароля совпадает, заполняем аттрибуты сессии
//                if (user.getPassword().equals(password.hashCode())){

//TODO добавить атрибуты, которые помогут странице понять роль HospitalPerson
            //           request.setAttribute("role", user.getRole);
            request.getRequestDispatcher("/nextPage.jsp").forward(request, response);
            //TODO записать в лог успешную регистрацию
            String ip = request.getRemoteAddr();
            return;
//                }
//        }

        } else {
            request.setAttribute("loginError", bundle.getString("loginError"));
            request.getRequestDispatcher("/").forward(request, response);
            //TODO записать в лог неуспешную регистрацию (дата, время, логин, IP (String getRemoteAddr())
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
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
