package internationalization;

import java.util.ListResourceBundle;

public class resource extends ListResourceBundle {
    private static final Object[][] contents = {
            //titles
            {"titleIndexPage", "Login to EPAM Hospital System"},
            {"titleAddPatientPage", "Add patient to EPAM Hospital System"},
            {"titleAddPersonalPage", "Add personal to EPAM Hospital System"},
            {"titleUpdatePersonalPage", "Change personal's data to EPAM Hospital System"},
            //fields
            {"loginField", "Enter your e-mail"},
            {"passwordField", "Enter your password"},
            {"firstNameField", "Enter firstname"},
            {"lastNameField", "Enter lastname"},
            {"chooseRoleField", "Choose role"},
            {"doctorField", "doctor"},
            {"nurseField", "nurse"},
            {"adminField", "administrator"},
            {"operation", "Operation"},
            {"procedure", "Procedure"},
            {"drug", "Drug"},
            {"type", "Type"},
            {"ADMIN", "Administrator"},
            {"DOCTOR", "Doctor"},
            {"NURSE", "Nurse"},
            //buttons
            {"buttonEnter", "Login"},
            {"buttonSave", "Save"},
            {"buttonCancel", "Cancel"},
            {"buttonOpenProfile", "Open profile"},
            {"buttonOpenCard", "Open card"},
            {"buttonCloseDiagnosis", "Close diagnosis"},
            {"buttonDoPerscription", "Do perscription"},
            {"buttonAdd", "Add"},
            {"buttonParientsList", "Back to patient's list"},
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
            {"perscriptions", "Perscriptions"},
            {"addDiagnosis", "Add diagnosis"},
            {"diagnosisDescription", "Description of diagnosis"},
            {"addPerscription", "Add perscription"},
            {"perscriptionDescription", "Description of perscription"},
            {"diagnosPerscription", "Perscriptions from diagnosis"},
            {"loginErrorMsg", "Invalid login or password!"},
            {"patientErrorMsg", "Invalid firstname or lastname!"},
            {"personalErrorMsg","Invalid firstname or lastname!"},
            {"createPersonalError","Creation error!"},
            {"updatePersonalError","Update error!"},
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