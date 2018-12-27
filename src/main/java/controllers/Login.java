package controllers;

import dao.h2.H2PersonalDao;
import model.Personal;
import services.LoginValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
Обработка страницы авторизации
 */

@WebServlet("/login")
public class Login extends HttpServlet {
    private String password;
    private String login;
    RequestDispatcher dispatcher;

//    PersonalService;

//    private DaoFactory daoFactory;
//private static final Logger log = LoggerFactory.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet method");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Locale locale = (Locale) session.getAttribute("locale");
//        if (locale == null) {
//            locale = new Locale("en");
//        }
//        ResourceBundle bundle = ResourceBundle.getBundle("login", locale);


        //Personal personal = new H2PersonalDao().readPersonalByEmail(l)


//        personalService.identify(personal);




        System.out.println("doPost method");
        response.setContentType("text/html");
        // получаем параметр login
        login = request.getParameter("login").trim();
        // получаем параметр password
        password = request.getParameter("password");
        System.out.println(login + " " + password);

        LoginValidate loginValidate = new LoginValidate();
        boolean result = loginValidate.authenticate(login, password);
        System.out.println("result \t" + result);
        if (result) {
//            User user = daoFactory.getUserDao().readUserByLogin(login);
//            if (user!=null) {
//                // если хэшкод пароля совпадает, заполняем аттрибуты сессии
//                if (user.getPassword().equals(password.hashCode())){


            dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request, response);
            return;
//                }
//        }

        } else {
//            request.setAttribute("Invalid login or password", bundle.getString("loginError"));
//            request.getRequestDispatcher("/index.jsp").forward(request, response);

//            response.getWriter().write("Invalid login or password");

            dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
            return;
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
