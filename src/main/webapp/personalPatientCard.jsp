<%@ page import="model.Patient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<fmt:setLocale value="${sessionScope.locale}"/>
<%--<fmt:setLocale value="de-GR"/>--%>
<fmt:setBundle basename="internationalization.resource" var="loc"/>
<fmt:message bundle="${loc}" key="currentPatientCard" var="currentPatientCard_msg"/>
<fmt:message bundle="${loc}" key="buttonCloseDiagnosis" var="buttonCloseDiagnosis_msg"/>
<fmt:message bundle="${loc}" key="buttonDoPerscription" var="buttonDoPerscription_msg"/>
<fmt:message bundle="${loc}" key="diagnosis" var="diagnosis_msg"/>
<fmt:message bundle="${loc}" key="perscriptions" var="perscriptons_msg"/>
<fmt:message bundle="${loc}" key="columnId" var="columnId_msg"/>
<fmt:message bundle="${loc}" key="columnDescription" var="columnDescription_msg"/>
<fmt:message bundle="${loc}" key="columnTime" var="columnTime_msg"/>
<fmt:message bundle="${loc}" key="columnOpen" var="columnOpen_msg"/>
<fmt:message bundle="${loc}" key="columnTypeOfPerscription" var="columnTypeOfPerscription_msg"/>
<fmt:message bundle="${loc}" key="columnDone" var="columnDone_msg"/>
<fmt:message bundle="${loc}" key="addDiagnosis" var="addDiagnosis_msg"/>
<fmt:message bundle="${loc}" key="diagnosisDescription" var="diagnosisDescription_msg"/>
<fmt:message bundle="${loc}" key="buttonAdd" var="buttonAdd_msg"/>
<fmt:message bundle="${loc}" key="addPerscription" var="addPerscription_msg"/>
<fmt:message bundle="${loc}" key="type" var="type_msg"/>
<fmt:message bundle="${loc}" key="perscriptionDescription" var="perscriptionDescription_msg"/>
<fmt:message bundle="${loc}" key="procedure" var="procedure_msg"/>
<fmt:message bundle="${loc}" key="operation" var="operation_msg"/>
<fmt:message bundle="${loc}" key="drug" var="drug_msg"/>
<fmt:message bundle="${loc}" key="buttonParientsList" var="buttonParientsList_msg"/>


<h3 style="text-align:center;font-size:28px" >${currentPatientCard_msg} ${sessionScope.currentPatient.lastName} ${sessionScope.currentPatient.firstName}</h3>
<br>
<button onclick="window.location.href='/main.jsp'">${buttonParientsList_msg}</button>
<br><br>
<fieldset >
   <%--<legend >History of disease</legend>--%>
    <button id="closeDiagnosisButton">${buttonCloseDiagnosis_msg}</button>
    <button id="doPrescription"  style="display:inline; float:right">${buttonDoPerscription_msg}</button>
    <br><br>
    <table id = "diagnosis" style="display:inline; float:left">
        <caption  style=" font-family: Arial, Helvetica, sans-serif;">${diagnosis_msg}:</caption>
            <tr>
                <th>${columnId_msg}</th>
                <th>${columnDescription_msg}</th>
                <th>${columnTime_msg}</th>
                <th>${columnOpen_msg}</th>
            </tr>
    </table>
     <table id="prescriptionsTable" class="page" style="display:inline; float:right;" >
        <caption style=" font-family: Arial, Helvetica, sans-serif;" >${perscriptons_msg}:</caption>
            <tr>
                <th>${columnId_msg}</th>
                <th>${columnDescription_msg}</th>
                 <th>${columnTypeOfPerscription_msg}</th>
                <th>${columnTime_msg}</th>
                 <th>${columnDone_msg}</th>
            </tr>
    </table>
</fieldset>

<br>
<fieldset style="float:left">
<legend>${addDiagnosis_msg}</legend>
 <br>
  <caption>${diagnosisDescription_msg}:</caption>
  <p><textarea name="comment" id="diagnosisDescription"></textarea></p>
  <button id="diagnosisSubmit" value="Добавить">${buttonAdd_msg}</button>

</fieldset>

<fieldset id="addPrescriptionFieldSet" style="display:none;">
<legend>${addPerscription_msg}</legend>
 <form>
  <caption>${type_msg}:</caption>
<form>
  <select name="prescriptionType" id="prescriptionType">
    <option value="Procedure">${procedure_msg}</option>
    <option value="Operation">${operation_msg}</option>
    <option value="Drug">${drug_msg}</option>
  </select>
</form>
 <caption>${perscriptionDescription_msg}:</caption>
  <p><textarea id="prescriptionDescription" name="comment"></textarea></p>
     <button id="prescriptionSubmit" value="Добавить">${buttonAdd_msg}</button>
 </form>

</fieldset>
<script>
    var diagnosisTable = document.getElementById("diagnosis");
    var diagnosis = <%=  request.getAttribute("diagnosesList") %>;
    if (diagnosis.length > 0) {
        updateDiagnosisTable(diagnosis, diagnosisTable);
        updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
    }
    var diagnosisSubmitButton = document.getElementById("diagnosisSubmit");
    diagnosisSubmitButton.addEventListener("click", diagnosisSubmitButtonListener);
    var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
    prescriptionSubmitButton.addEventListener("click", prescriptionSubmitButtonListener);
    var closeDiagnosisButton = document.getElementById("closeDiagnosisButton");
    closeDiagnosisButton.addEventListener("click", closeDiagnosisButtonListener);

    var doPrescriptionButton = document.getElementById("doPrescription");
    doPrescriptionButton.addEventListener("click", prescriptionDoneListener);
</script>
</body>
</html>