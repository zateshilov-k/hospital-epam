package dao;

import model.Personal;

import java.util.Optional;

public interface PersonalDao {

    long createPersonal(Personal personal);

    Optional<Personal> readPersonalByLogin(String email);

    long updatePersonal(Personal personal);

}