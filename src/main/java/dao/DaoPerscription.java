package dao;

public interface DaoPerscription {
    // добавить назначение по конкретному диагнозу
    void addPerscription();

    // изменить конкретное назначение
    void updatePerscription();

    // получить все назначения по диагнозу пациента
    void getAllPerscription();
}
