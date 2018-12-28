package utils;

public class LoginValidate {

    public boolean doValidation(String login, String password) {
        if (login == null || login.equals("") || password == null || password.trim() == "") return false;
        return true;
    }
}
