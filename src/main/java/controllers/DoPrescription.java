package controllers;

import dao.DaoFactory;
import model.Patient;
import model.Personal;
import model.Prescription;
import services.PrescriptionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doPrescription")
public class DoPrescription extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prescriptionId = request.getParameter("prescriptionId");
        HttpSession session = request.getSession();
        Personal user = (Personal) session.getAttribute("user");
        new PrescriptionService().doPrescription(user,prescriptionId,daoFactory);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }
}
