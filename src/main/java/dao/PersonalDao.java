package dao;

import model.Personal;

public interface PersonalDao {

    long createPersonal(Personal personal);

    Personal readPersonalByEmail(String email);

    long updatePersonal(Personal personal);

}