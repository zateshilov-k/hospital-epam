package services;

public class LoginValidate {

    public boolean doValidation(String login, String password) {
        if (login == null || login.equals("") || password == null || password.trim() == "") return false;

        //TODO проверить есть ли логин в БД, затем сравнить пароли

        return true;
    }
}
