package controllers;

import com.google.gson.GsonBuilder;
import dao.DaoFactory;
import dao.PatientDao;
import model.Diagnosis;
import model.Patient;
import model.Personal;
import org.json.JSONArray;
import utils.DiagnosisTypeAdapter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/*
Сервлет отрабатывает действия в личной карточке пациента
 */
@WebServlet("/patientCard")
public class PatientCardServlet extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Patient Card GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=utf-8");

        Long patientId = Long.parseLong(request.getParameter("patientId"));
        PatientDao patientDao = daoFactory.getPatientDao();
        Patient patient = patientDao.getPatient(patientId);
        session.setAttribute("currentPatient", patient);

        List<Diagnosis> diagnosisList = daoFactory.getDiagnosisDao().getAllDiagnosesByPatientId(patientId);
        request.setAttribute("currentPatient", patient);


        Locale locale = (Locale) request.getSession().getAttribute("locale");
        GsonBuilder gsonBuilder = new GsonBuilder();
        DiagnosisTypeAdapter.locale = locale;
        System.out.println(locale);
        gsonBuilder.registerTypeAdapter(Diagnosis.class,new DiagnosisTypeAdapter());


        request.setAttribute("diagnosesList", gsonBuilder.create().toJson(diagnosisList).toString());
        Personal currentUser = (Personal) session.getAttribute("user");
        session.setAttribute("user", currentUser);
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/personalPatientCard.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
