package controllers;

import dao.DaoFactory;
import model.Patient;
import model.Personal;
import model.PrescriptionType;
import services.PrescriptionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addPrescription")
public class AddPrescription extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String diagnosisIdString = request.getParameter("diagnosisId");
        HttpSession session = request.getSession();
        Personal user = (Personal) session.getAttribute("user");
        Patient patient = (Patient) session.getAttribute("currentPatient");

        new PrescriptionService().addPrescribePrescription(user,patient,diagnosisIdString,description,
                type,daoFactory);

    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }
}
