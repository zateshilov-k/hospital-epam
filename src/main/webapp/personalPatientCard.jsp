<%@ page import="model.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

th, td {
  padding: 5px;
  text-align: left;
}
tr:hover {background-color:#a0a0a0;}

</style>
</head>
<body style="background-color:powderblue;">


<h2 style="text-align:center;" style="font-size:28px" >Личная карточка пациента <%=
((Patient)request.getAttribute("currentPatient")).getFirstName()
        + " " + ((Patient)request.getAttribute("currentPatient")).getLastName()  %></h2>
<br>

<button style="text-align:center;" onclick="href='/main.jsp'">To Main</button>
<br>
<br>

<fieldset >
<legend >История болезней</legend>
<button onclick="myFunction()">Close diagnosis</button>
<br><br>
<table id = "diagnosis" style="display:inline; float:left">
  <caption>Диагнозы:</caption>
  <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Время</th>
    <th>Открыт</th>
  </tr>
  <tr>

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
    updateDiagnosisTable(diagnosis,diagnosisTable);

    var diagnosisSubmitButton = document.getElementById("diagnosisSubmit");

    diagnosisSubmitButton.addEventListener("click",diagnosisSubmitButtonListener);
    var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");

    function prescriptionSubmitButtonListener() {
        var prescriptionTextArea = document.getElementById("prescriptionDescription");
        var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
        var prescriptionTypeSelect = document.getElementById("prescriptionType");
        if (prescriptionTextArea.value === "") {
            alert("Введите описание назначения");
        } else {
            $.post("addPrescription", {description : prescriptionTextArea.value,
             diagnosisId : prescriptionSubmitButton.value,
             type : prescriptionTypeSelect.value});
            prescriptionTextArea.value = "";
            $.post("listOfPrescriptions", {diagnosisId :  prescriptionSubmitButton.value}, function(response) {
                var prescriptions = JSON.parse(response);
                updatePrescriptionsTable(prescriptions);
            });
        }
    }

    prescriptionSubmitButton.addEventListener("click",prescriptionSubmitButtonListener);

    function diagnosisSubmitButtonListener() {
        var diagnosisTextArea = document.getElementById("diagnosisDescription");
        if (diagnosisTextArea.value === "") {
            alert("Введите описание диагноза");
        } else {
            $.post("addDiagnosis",{description : diagnosisTextArea.value});
            diagnosisTextArea.value = "";
            getAndUpdateDiagnosis();
        }

    }
    function getAndUpdateDiagnosis() {
        $.post("listofdiagnosis",{},function (response) {
           var diagnosis = JSON.parse(response);
            var diagnosisTable = document.getElementById("diagnosis");
            updateDiagnosisTable(diagnosis,diagnosisTable);
        });
    }
    function updateDiagnosisTable(diagnosis, table) {
        for (var i = 1; i < table.rows.length; ++i) {
            table.rows[i].innerHTML = "";
        }
        for (var i = 0;  i < diagnosis.length; i++) {
            var newRow = table.insertRow(i+1);
            newRow.addEventListener("click", diagnosisClick);
            for (var j = 0; j < 4; j++) {
                var newCell = newRow.insertCell(j);
                if (j === 0 ) {
                    newCell.innerHTML=diagnosis[i]['diagnosisId'];
                } else if (j === 1) {
                    newCell.innerHTML=diagnosis[i]['description'];
                } else if (j === 2) {
                    newCell.innerHTML=diagnosis[i]['time'];
                } else if (j === 3) {
                    newCell.innerHTML=diagnosis[i]['opened'];
                }
            }
        }
    }
    function diagnosisClick() {
        var rows = this.parentNode.rows;
        for (i=1; i < rows.length; i++) {
            rows[i].style.color = "black";
        }
        this.style.color = "red";
        var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");
        if (this.cells[3].innerHTML === 'false') {
            prescriptionFieldSet.style.display = "none";
        } else if(this.cells[3].innerHTML === 'true') {
            prescriptionFieldSet.style.display = "inline";
            var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
            prescriptionSubmitButton.value = this.cells[0].innerHTML;
        }
        $.post("listOfPrescriptions", {diagnosisId : this.cells[0].innerHTML}, function(response) {
            var prescriptions = JSON.parse(response);
            updatePrescriptionsTable(prescriptions);
        });
    }
    function updatePrescriptionsTable(prescriptions) {
        var table = document.getElementById('prescriptionsTable');
        for (var i = 1; i < table.rows.length; ++i) {
            table.rows[i].innerHTML = "";
        }
        if (prescriptions.length === 0) {
            table.style.display = "none";
        } else {
            table.style.display = "block";
        }
        for (var i = 0;  i < prescriptions.length; i++) {
            var newRow = table.insertRow(i+1);
            for (var j = 0; j < 5; j++) {
                var newCell = newRow.insertCell(j);
                if (j === 0 ) {
                    newCell.innerHTML=prescriptions[i]['prescriptionId'];
                } else if (j === 1) {
                    newCell.innerHTML=prescriptions[i]['description'];
                } else if( j === 2) {
                    newCell.innerHTML=prescriptions[i]['type'];
                } else if (j === 3) {
                    newCell.innerHTML=prescriptions[i]['time'];
                } else if (j === 4) {
                    newCell.innerHTML=prescriptions[i]['done'];
                }
            }
        }
    }
</script>
</body>
</html>