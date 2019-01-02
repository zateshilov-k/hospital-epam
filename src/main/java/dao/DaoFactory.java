package dao;

import model.Personal;

public interface DaoFactory extends AutoCloseable {

    Personal getPersonalDao();

}