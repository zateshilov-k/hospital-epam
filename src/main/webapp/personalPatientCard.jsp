<%@ page import="model.Patient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/js/main.js" />" type="text/javascript"></script>
    <style>

    table, tbody,thead {
    display: block;
    }
    tbody {
    height: 280px;
    overflow-y: auto;
    overflow-x: hidden;
    }

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

</style>
</head>
<body style="background-color:powderblue;">

<h3 style="text-align:center;font-size:28px" >Личная карточка пациента ${sessionScope.currentPatient.lastName} ${sessionScope.currentPatient.firstName}</h3>
<br>
<button onclick="window.location.href='/main.jsp'">To Main</button>
<br><br>
<fieldset >
   <legend >History of disease</legend>
    <button id="closeDiagnosisButton">Close diagnosis</button>
    <button id="doPrescription"  style="display:inline; float:right">Do prescription</button>
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
        <caption style=" font-family: Arial, Helvetica, sans-serif;" >Appointments:</caption>
            <tr>
                <th>Id</th>
                 <th>Description</th>
                 <th>Type</th>
                 <th>Time</th>
                 <th>Complited</th>
            </tr>
    </table>
<legend >История болезней</legend>
<button id="closeDiagnosisButton">Close diagnosis</button>
<button id="doPrescription"  style="display:inline; float:right">Do prescription</button>
<br><br>
<table id = "diagnosis" style="display:inline; float:left">
  <caption>Диагнозы:</caption>
  <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Время</th>
    <th>Открыт</th>
  </tr>
</table>

<table id="prescriptionsTable" class="page" style="display:inline; float:right;" >
 <caption>Назначение по выбранному диагнозу:</caption>
  <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Тип</th>
    <th>Время</th>
      <th>Выполнен</th>
  </tr>
</table>
</fieldset>

<br>
<fieldset style="float:left">
<legend>Добавить диагноз</legend>
 <br>
  <caption>Описание диагноза:</caption>
  <p><textarea name="comment" id="diagnosisDescription"></textarea></p>
  <button id="diagnosisSubmit" value="Добавить">Добавить</button>

</fieldset>

<fieldset id="addPrescriptionFieldSet" style="display:none;">
<legend>Добавить назначение</legend>
 <form>
  <caption>Тип:</caption>
<form action="/action_page.php">
  <select name="prescriptionType" id="prescriptionType">
    <option value="Procedure">Процедура</option>
    <option value="Operation">Операция</option>
    <option value="Drug">Лекарство</option>
  </select>
</form>
 <caption>Описание назначения:</caption>
  <p><textarea id="prescriptionDescription" name="comment"></textarea></p>
     <button id="prescriptionSubmit" value="Добавить">Добавить</button>
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

    var doPrescriptionButton = document.getElementById("doPrescription");
    doPrescriptionButton.addEventListener("click", prescriptionDoneListener);
</script>
</body>
</html>