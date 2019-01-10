package controllers;

import dao.DaoFactory;
import dao.PersonalDao;
import model.Personal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
сервлет для списка медперсонала? отрабатывает переход в личный кабинет медперсоанала
 */
@WebServlet("/personalCard")
public class PersonalList extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=utf-8");
        Long personalId = Long.parseLong(request.getParameter("personalId"));
        System.out.println("PersonalList servlet get id of Personal = " + personalId);
        PersonalDao personalDao = daoFactory.getPersonalDao();
        Personal personal = personalDao.getPersonalById((long)personalId);
        request.setAttribute("currentPersonal",personal);
        Personal currentUser = (Personal)session.getAttribute("user");
        session.setAttribute("user", currentUser);
        request.getRequestDispatcher("/personalUpdate.jsp").forward(request, response);
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
