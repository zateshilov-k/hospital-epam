package controllers;

import dao.DaoFactory;
import model.Patient;
import model.Personal;

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

@WebServlet("/addDiagnosis")
public class AddDiagnosis extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("addDiagnosis doPost");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();
        Personal user = (Personal) session.getAttribute("user");
        Patient patient = (Patient) session.getAttribute("currentPatient");
        daoFactory.getDiagnosisDao().addDiagnosis(patient.getPatientId(),user.getPersonalId(),description);
    }

    @Override
    public void destroy() {
//        super.destroy();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }


}
