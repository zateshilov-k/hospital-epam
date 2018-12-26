package services;

public class LoginValidate {

    public boolean authenticate(String login, String password) {
        if (login == null || login.equals("") || password == null || password.trim() == "") return false;
        return true;
    }
}
