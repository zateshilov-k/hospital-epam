package internationalization;

import java.util.ListResourceBundle;

public class resource_en extends ListResourceBundle {
    private static final Object[][] contents = {
            //titles
            {"titleIndexPage", "Login to EPAM Hospital System"},
            {"titleAddPatientPage", "Add patient to EPAM Hospital System"},
            {"titleAddPersonalPage", "Add personal to EPAM Hospital System"},
            //fields
            {"loginField", "Enter your e-mail"},
            {"passwordField", "Enter your password"},
            {"firstNameField", "Enter firstname"},
            {"lastNameField", "Enter lastname"},
            {"chooseRoleField","Chose role"},
            {"doctorField","doctor"},
            {"nurseField","nurse"},
            {"adminField","administrator"},
            //buttons
            {"buttonEnter", "Login"},
            {"buttonSave", "Save"},
            {"buttonCancel", "Cancel"},
            {"buttonOpenProfile", "Open profile"},
            {"buttonOpenCard", "Open card"},
            //menus
            {"menuAddPersonal", "Add personal"},
            {"menuAddPatient", "Add patient"},
            {"menuLogout", "Logout"},
            //message
            {"currentUser", "Current user"},
            {"openPatientCard","Open patient card"},
            //table columns
            {"columnId", "id"},
            {"columnLogin", "Login"},
            {"columnFirstName", "Firstnamr"},
            {"columnLastName", "Lastname"},
            {"columnRole", "Role"},
            {"columnAction", "Action"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
