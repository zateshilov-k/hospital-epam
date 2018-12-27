package dao;

import model.Personal;

import java.util.Optional;

public interface PersonalDao {

    long createPersonal(Personal personal);

    Optional<Personal> readPersonalByEmail(String email);

    long updatePersonal(Personal personal);

}