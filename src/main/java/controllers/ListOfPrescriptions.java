package controllers;

import dao.DaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/listOfPrescriptions")
public class ListOfPrescriptions extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

}
