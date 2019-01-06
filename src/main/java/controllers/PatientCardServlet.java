package controllers;

import dao.DaoFactory;
import dao.PatientDao;
import model.Diagnosis;
import model.Patient;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
Сервлет отрабатывает действия в личной карточке пациента
 */
@WebServlet("/patientCard")
public class PatientCardServlet extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Patient Card GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("PatientCardServlet doPost method");
        System.out.println(request.getParameter("patientId"));
        System.out.println("PATIENT_ID" + request.getParameter("patientId"));
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        PatientDao patientDao = daoFactory.getPatientDao();
        Patient patient = patientDao.getPatient(patientId);

        List<Diagnosis> diagnosisList = daoFactory.getDiagnosisDao().getAllDiagnosesByPatientId(patientId);
        request.setAttribute("currentPatient",patient);
        request.getSession().setAttribute("currentPatient",patient);
        request.setAttribute("diagnosesList",new JSONArray(diagnosisList).toString());
        System.out.println(diagnosisList);
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/personalPatientCard.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("after");
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
