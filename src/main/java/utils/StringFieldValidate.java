package utils;

public class StringFieldValidate {

    public boolean doValidation(String stringField) {
        if (stringField == null || stringField.equals("")) return false;
        return true;
    }
}