package controllers;

import dao.DaoFactory;

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

/*
Сервлет отрабатывает действия в личной карточке пациента
 */
@WebServlet("/patientCard")
public class PatientCardServlet extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        System.out.println("PatientCardServlet doPost method");
        request.getRequestDispatcher("/personalPatientCard.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
//        super.destroy();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }
}
