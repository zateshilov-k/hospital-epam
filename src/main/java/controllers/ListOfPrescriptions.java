package controllers;

import dao.DaoFactory;
import model.Prescription;
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

@WebServlet("/listOfPrescriptions")
public class ListOfPrescriptions extends HttpServlet {
    DaoFactory daoFactory;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Long diagnosisId = Long.parseLong(request.getParameter("diagnosisId"));
        List<Prescription> prescriptions = daoFactory.getPrescriptionDao().getAllPrescriptionsByDiagnosisId(diagnosisId);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(new JSONArray(prescriptions).toString());
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
