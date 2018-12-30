package services;

import dao.h2.H2PersonalDao;
import model.Personal;
import utils.HashGenerator;
import utils.LoginValidate;

import javax.sql.DataSource;
import java.util.Optional;

public class PersonalService {

    public Optional<Personal> authenticatePersonal(String login, String password, DataSource dataSource
            , HashGenerator hashGenerator) {
        LoginValidate loginValidate = new LoginValidate();
        boolean isValid = loginValidate.doValidation(login, password);
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
