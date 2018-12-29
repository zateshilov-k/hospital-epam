package controllers;

import dao.h2.H2PatientDao;
import model.Patient;
import utils.HashGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/listofpatients")
public class ListOfPatients extends HttpServlet {

    DataSource dataSource;
    HashGenerator hashGenerator;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patientList = new H2PatientDao(dataSource).getAllPatients();
        System.out.println("PATIENT LIST");
        for (int i = 0; i < patientList.size(); i++) {
            System.out.println(patientList.get(i));
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = (DataSource) context.getAttribute("dataSource");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

}
