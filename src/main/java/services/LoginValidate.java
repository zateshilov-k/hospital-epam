package services;

public class LoginValidate {

    public boolean authenticate (String login, String password){

        if (password == null || password.trim()=="")         return false;
        return true;
    }
}
