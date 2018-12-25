package controllers;

import services.LoginValidate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private String password;
    private String login;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // получаем параметр login
        login = request.getParameter("login").trim();

        // получаем параметр password
        password = request.getParameter("password");

        LoginValidate loginValidate = new LoginValidate();
        boolean result = loginValidate.authenticate(login, password);
        if (!result) {
            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            dispatcher = request.getRequestDispatcher("/main.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
