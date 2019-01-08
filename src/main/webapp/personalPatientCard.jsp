<%@ page import="model.Patient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/js/main.js" />" type="text/javascript"></script>
    <style>
    table, th, td {
        padding: 11px;
        border: 3px solid rgb(230, 230, 230);
        border-collapse: collapse;
    }
    d {
        padding: 5px;
        text-align: left;
    }
    tr:hover {background-color:rgb(255, 255, 255);}
    #form1{
      margin-left: 900px;
    }
    </style>
    </head>
    <body style="background-color:powderblue;">
    <style>
    table, th, td {
        padding: 12px;
        border: 3px solid rgb(230, 230, 230);
        border-collapse: collapse;
    }
    d {
        padding: 5px;
        text-align: left;
    th, t
    }
    tr:hover {background-color:rgb(255, 255, 255);}

    </style>
    </head>
    <body style="background-color:powderblue;">


<h3 style="text-align:center;font-size:28px" >Личная карточка пациента ${sessionScope.currentPatient.lastName} ${sessionScope.currentPatient.firstName}</h3>

<br>

    <button style="text-align:center;" onclick="href='/main.jsp'">To Main</button>
    <br>
    <br>

    <fieldset >
    <legend >History of disease</legend>
    <button id="closeDiagnosisButton">Close diagnosis</button>
    <br><br>

    <table id = "diagnosis" style="display:inline; float:left">
        <caption  style=" font-family: Arial, Helvetica, sans-serif;">Diagnoses:</caption>
    <tr>
    <th>Id</th>
    <th>Description</th>
    <th>Time</th>
    <th>Open</th>
    </tr>
    </table>


    <table id="prescriptionsTable" class="page" style="display:inline; float:right;" >
        <caption style=" font-family: Arial, Helvetica, sans-serif;" >Appointment for the selected diagnosis:</caption>
    <tr>
    <th>Id</th>
    <th>Description</th>
    <th>Type</th>
    <th>Time</th>
    <th>Complited</th>
    </tr>
    </table>
    </fieldset>

    <br>
    <fieldset style="float:left">
        <legend>Add a diagnosis</legend>
    <br>
    <caption>Description of the diagnosis:</caption>
    <p><textarea name="comment" id="diagnosisDescription"></textarea></p>
    <button id="diagnosisSubmit" value="Добавить">Add</button>

        </fieldset>

        <fieldset id="addPrescriptionFieldSet" style="display:none;">
        <legend>Add appointment</legend>
    <form>
    <caption style=" font-family: Arial, Helvetica, sans-serif;">Тип:</caption>
    <form action="/action_page.php">
        <select name="prescriptionType" id="prescriptionType">
        <option value="Procedure">Procedure</option>
        <option value="Operation">Operation</option>
        <option value="Drug">Drug</option>
        </select>
        </form>
        <caption style=" font-family: Arial, Helvetica, sans-serif;" >Description of purpose:</caption>
    <p><textarea id="prescriptionDescription" name="comment"></textarea></p>
    <button id="prescriptionSubmit" value="Добавить">Add</button>
        </form>

        </fieldset>

        <script>

    var diagnosisTable = document.getElementById("diagnosis");
    var diagnosis = <%=  request.getAttribute("diagnosesList") %>;
    if (diagnosis.length > 0) {
        updateDiagnosisTable(diagnosis,diagnosisTable);
        updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
    }

    var diagnosisSubmitButton = document.getElementById("diagnosisSubmit");
    diagnosisSubmitButton.addEventListener("click",diagnosisSubmitButtonListener);
    var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
    prescriptionSubmitButton.addEventListener("click",prescriptionSubmitButtonListener);
    var closeDiagnosisButton = document.getElementById("closeDiagnosisButton");
    closeDiagnosisButton.addEventListener("click",closeDiagnosisButtonListener);
    </script>
    </body>
</html>