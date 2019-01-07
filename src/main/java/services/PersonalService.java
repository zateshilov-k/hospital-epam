package services;

import dao.DaoFactory;
import model.Personal;
import utils.HashGenerator;
import utils.StringFieldValidate;

import java.util.List;
import java.util.Optional;

public class PersonalService {

    public Optional<Personal> authenticatePersonal(String login, String password, DaoFactory daoFactory
            , HashGenerator hashGenerator) {
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(login);
        if (isValid) {
            isValid = stringFieldValidate.doValidation(password);
        }
        if (isValid) {
            Optional<Personal> personal = daoFactory.getPersonalDao().readPersonalByLogin(login);
            if (personal.isPresent()) {
                if (!personal.get().getPassword().equals(hashGenerator.getHash(password))) {
                    return Optional.empty();
                }
            }
            return personal;
        } else {
            return Optional.empty();
        }
    }

    public List<Personal> getAllPersonals(DaoFactory daoFactory) {
        return  daoFactory.getPersonalDao().getAllPersonals();
    }

    public long addPersonal(DaoFactory daoFactory, Personal personal){
        return daoFactory.getPersonalDao().createPersonal(personal);
    }
}
