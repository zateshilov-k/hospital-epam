package dao;

import model.Personal;

import java.util.List;
import java.util.Optional;

public interface PersonalDao {
    long createPersonal(Personal personal);

    Optional<Personal> readPersonalByLogin(String login);

    long updatePersonal(Personal personal);

    List<Personal> getAllPersonals();

    Personal getPersonalById(long personalId);
}