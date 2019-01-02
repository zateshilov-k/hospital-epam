package dao;

public interface DaoFactory extends AutoCloseable {

    PersonalDao getPersonalDao();

}