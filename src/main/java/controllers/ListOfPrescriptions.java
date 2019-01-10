package controllers;

import com.google.gson.GsonBuilder;
import dao.DaoFactory;
import model.Prescription;
import model.PrescriptionType;
import org.json.JSONArray;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

@WebServlet("/listOfPrescriptions")
public class ListOfPrescriptions extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Long diagnosisId = Long.parseLong(request.getParameter("diagnosisId"));
        response.setContentType("text/html;charset=utf-8");
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        System.out.println(locale);
        PrescriptionType.locale = locale;
        List<Prescription> prescriptions = daoFactory.getPrescriptionDao().getAllPrescriptionsByDiagnosisId(diagnosisId);
        try {
            PrintWriter writer = response.getWriter();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Prescription.class,new PrescriptionType.PrescriptionTypeAdapter());
            writer.write(gsonBuilder.create().toJson(prescriptions).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

}
