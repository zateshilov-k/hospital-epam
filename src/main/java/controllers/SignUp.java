package controllers;

import utils.SignUpValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class SignUp extends HttpServlet {
    private String password;
    private String firstname;
    private String surname;
    private String email;
//    private DaoFactory daoFactory;
//private static final Logger log = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SignUp");
//        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost method");
        response.setContentType("text/html");
        // получаем параметр firstname
        firstname = request.getParameter("firstname").trim();
        // получаем параметр surname
        surname = request.getParameter("surname");
        // получаем параметр login
        email = request.getParameter("login");
        // получаем параметр password
        password = request.getParameter("password");

        System.out.println(email + "\t" + password);

        SignUpValidate signUpValidate = new SignUpValidate();
        boolean result = signUpValidate.doValidation(email, password, firstname, surname);
        if (!result) {
            //TODO добавить атрибуты, которые помогут странице понять роль HospitalPerson
            //           request.setAttribute("role", user.getRole);
            request.getRequestDispatcher("/").forward(request, response);
            //TODO записать в лог успешную регистрацию
            return;

        } else {
            request.getRequestDispatcher("/").forward(request, response);
            //TODO записать в лог неуспешную регистрацию
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
