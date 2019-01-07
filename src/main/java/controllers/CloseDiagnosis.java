package controllers;

import dao.DaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/closeDiagnosis")
public class CloseDiagnosis extends HttpServlet {
    DaoFactory daoFactory;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Long diagnosisId = Long.parseLong(request.getParameter("diagnosisId"));
        daoFactory.getDiagnosisDao().closeDiagnosis(diagnosisId);
    }
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

}
