package utils;

public class SignUpValidate {
    public boolean doValidation(String login, String password, String firstname, String surname) {
        if (login == null || login.equals("") || password == null || password.trim() == "") return false;
        if (firstname == null || firstname.equals("") || surname == null || surname.trim() == "") return false;
        return true;
    }
}