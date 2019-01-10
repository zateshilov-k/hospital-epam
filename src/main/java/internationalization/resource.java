package internationalization;

import java.util.ListResourceBundle;

public class resource extends ListResourceBundle {
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
            {"chooseRoleField", "Chose role"},
            {"doctorField", "doctor"},
            {"nurseField", "nurse"},
            {"adminField", "administrator"},
            {"operation","Operation"},
            {"procedure","Procedure"},
            {"drug","Drug"},
            {"type","Type"},
            {"buttonParientsList","Back to patient's list"},
            //buttons
            {"buttonEnter", "Login"},
            {"buttonSave", "Save"},
            {"buttonCancel", "Cancel"},
            {"buttonOpenProfile", "Open profile"},
            {"buttonOpenCard", "Open card"},
            {"buttonCloseDiagnosis","Close diagnosis"},
            {"buttonDoPerscription","Do perscription"},
            {"buttonAdd","Add"},
            //menus
            {"menuAddPersonal", "Add personal"},
            {"menuAddPatient", "Add patient"},
            {"menuLogout", "Logout"},
            //message
            {"currentUser", "Current user"},
            {"currentPatientCard", "Card of patient"},
            {"openPatientCard", "Open patient card"},
            {"historyOfDiagnosis", "History of diagnosis"},
            {"diagnosis", "Diagnosises"},
            {"perscriptions","Perscriptions"},
            {"addDiagnosis", "Add diagnosis"},
            {"diagnosisDescription", "Description of diagnosis"},
            {"addPerscription","Add perscription"},
            {"perscriptionDescription","Description of perscription"},
            {"diagnosPerscription", "Perscriptions from diagnosis"},
            //table columns
            {"columnId", "id"},
            {"columnLogin", "Login"},
            {"columnFirstName", "Firstnamr"},
            {"columnLastName", "Lastname"},
            {"columnRole", "Role"},
            {"columnAction", "Action"},
            {"columnDescription", "Description"},
            {"columnTime", "Time"},
            {"columnOpen", "Open"},
            {"columnDone", "Done"},
            {"columnTypeOfPerscription", "Type"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}