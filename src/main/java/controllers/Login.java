package controllers;

import dao.DaoFactory;
import model.Patient;
import model.Personal;
import model.Role;
import services.PatientService;
import services.PersonalService;
import utils.HashGenerator;

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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/*
Обработка страницы авторизации
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    DaoFactory daoFactory;
    HashGenerator hashGenerator;
    private static final Logger log = Logger.getLogger(Login.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale == null) {
            locale = new Locale("ru", "RU");
        }
        session.setAttribute("locale", locale);
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        response.setContentType("text/html;charset=utf-8");
        String login = request.getParameter("login").trim();
        String password = request.getParameter("password").trim();
        System.out.println("Login: " + login);
        Optional<Personal> currentUser = new PersonalService().authenticatePersonal(login,
                password, daoFactory, hashGenerator);
        String ip = request.getRemoteAddr();
        if (currentUser.isPresent()) {
            session.setAttribute("user", currentUser.get());
            if (currentUser.get().getRole() == Role.ADMIN) {
                List<Personal> personals = new PersonalService().getAllPersonals(daoFactory);
                if (personals != null) {
                    session.setAttribute("personals", personals);
                } else {
                    System.out.println("Personals is null");
                }
                request.getRequestDispatcher("/personals.jsp").forward(request, response);
            } else {
                List<Patient> patients = new PatientService().getAllPatients(daoFactory);
                if (patients != null) {
                    session.setAttribute("patients", patients);
                }

                request.getRequestDispatcher("/main.jsp").forward(request, response);
            }
            log.info("From IP: " + ip + "; User: " + currentUser.get().getLastName()
                    + currentUser.get().getFirstName() + "; login: " + currentUser.get().getLogin()
                    + "; role: " + currentUser.get().getRole() + "; status: LOGGED");
            return;
        } else {
            String str = bundle.getString("loginError");
            str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            log.info("From IP: " + ip + "; " + "status: ERROR LOGIN");
            request.setAttribute("loginError", str);
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
        hashGenerator = (HashGenerator) context.getAttribute("hashGenerator");
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
