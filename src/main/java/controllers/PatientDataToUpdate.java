package controllers;
import dao.DaoFactory;
import dao.PatientDao;
import dao.PersonalDao;
import model.Patient;
import model.Personal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/patientDataForUpdate")
public class PatientDataToUpdate extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=utf-8");
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        System.out.println("PatientList servlet get id of Patient = " + patientId);
        PatientDao patientDao = daoFactory.getPatientDao();
        Patient patient = patientDao.getPatient(patientId);
        request.setAttribute("currentPatient",patient);
        System.out.println(patient);
        Personal currentUser = (Personal)session.getAttribute("user");
        session.setAttribute("user", currentUser);
        request.getRequestDispatcher("/patientProfile.jsp").forward(request, response);
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
