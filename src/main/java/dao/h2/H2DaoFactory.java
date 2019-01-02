package dao.h2;

import dao.DaoFactory;
import dao.PersonalDao;

import javax.sql.DataSource;

public class H2DaoFactory implements DaoFactory {
    final private PersonalDao personalDao;

    public H2DaoFactory(DataSource dataSource) {
        personalDao = new H2PersonalDao(dataSource);
    }

    @Override
    public PersonalDao getPersonalDao() {
        return personalDao;
    }

    @Override
    public void close() throws Exception {

    }
}
