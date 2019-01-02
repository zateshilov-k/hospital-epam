package services;

import dao.h2.H2PersonalDao;
import model.Personal;
import utils.HashGenerator;
import utils.StringFieldValidate;

import javax.sql.DataSource;
import java.util.Optional;

public class PersonalService {

    public Optional<Personal> authenticatePersonal(String login, String password, DataSource dataSource
            , HashGenerator hashGenerator) {
        StringFieldValidate stringFieldValidate = new StringFieldValidate();
        boolean isValid = stringFieldValidate.doValidation(login);
        if (isValid) {
            isValid = stringFieldValidate.doValidation(password);
        }
        if (isValid) {
            Optional<Personal> personal = new H2PersonalDao(dataSource).readPersonalByLogin(login);
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
}
