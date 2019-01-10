package internationalization;

import java.util.ListResourceBundle;

public class resource_ru extends ListResourceBundle {
    private static final Object[][] contents = {
            //titles
            {"titleIndexPage", "Войдите в систему EPAM Hospital"},
            {"titleAddPatientPage", "Создайте пациента в системе EPAM Hospital"},
            {"titleAddPersonalPage", "Создайте сотрудника в системе EPAM Hospital"},
            //fields
            {"loginField", "Введите электронную почту"},
            {"passwordField", "Введите пароль"},
            {"firstNameField", "Введите имя"},
            {"lastNameField", "Введите фамилию"},
            {"chooseRoleField", "Выберите роль"},
            {"doctorField", "доктор"},
            {"nurseField", "медсестра"},
            {"adminField", "администратор"},
            {"operation","Операция"},
            {"procedure","Процедура"},
            {"drug","Лекарство"},
            //buttons
            {"buttonEnter", "Войти"},
            {"buttonSave", "Сохранить"},
            {"buttonCancel", "Отмена"},
            {"buttonOpenProfile", "Перейти в профиль"},
            {"buttonOpenCard", "Перейти в карточку"},
            {"buttonCloseDiagnosis","Закрыть диагноз"},
            //menus
            {"menuAddPersonal", "Добавить сотрудника"},
            {"menuAddPatient", "Добавить пациента"},
            {"menuLogout", "Выйти из программы"},
            //message
            {"currentUser", "Текущий пользователь"},
            {"openPatientCard", "Перейти в карточку выбранного пациента"},
            {"historyOfDiagnosis", "История болезней"},
            {"diagnosis", "Диагнозы"},
            {"addDiagnosis", "Добавить диагноз"},
            {"diagnosisDescription", "Описание диагноза"},
            {"addPerscription","Добавить назначение"},
            {"perscriptionDescription","Описание Назначения"},
            {"diagnosPerscription", "Назначение по выбранному диагнозу"},
            //table columns
            {"columnId", "Код"},
            {"columnLogin", "Логин"},
            {"columnFirstName", "Имя"},
            {"columnLastName", "Фамилия"},
            {"columnRole", "Должность"},
            {"columnAction", "Действие"},
            {"columnDescription", "Описание"},
            {"columnTime", "Время"},
            {"columnOpen", "Открыт"},
            {"columnDone", "Выполнен"},
            {"columnTypeOfPerscription", "Тип"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
