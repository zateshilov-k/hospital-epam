<%@ page import="model.Patient" %>
<%@ page import="model.Diagnosis" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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

<table class="page" style="display:inline; float:right;" >
 <caption>Назначение по выбранному диагнозу:</caption>
    <script>
function myFunction() {
  var x = document.getElementsByClassName("page");
  for (var i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
}
</script>
  <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Тип</th>
    <th>Время</th>
  </tr>
  <tr>
    <td>1</td>
    <td>Капотен</td>
    <td>Лекарство</td>
    <td>12.08.2018,9:30</td>
  </tr>
    <tr>
    <td>2</td>
    <td>Капотен</td>
    <td>Лекарство</td>
    <td>12.08.2018,9:30</td>
  </tr>
    <tr>
    <td>3</td>
    <td>Капотен</td>
    <td>Лекарство</td>
    <td>12.08.2018,9:30</td>
  </tr>

</table>
</fieldset>

<br>
<fieldset style="float:left">
<legend>Добавить диагноза</legend>
 <form >
 <br>
  <caption>Описание диагноза:</caption>
  <p><textarea name="comment"></textarea></p>
  <p><input type="submit" value="Добавить" ></p>
 </form>
</fieldset>

<fieldset style="display:inline;">
<legend>Добавить диагноз</legend>
 <form >
  <caption>Тип:</caption>
<form action="/action_page.php">
  <select name="cars">
    <option value="saab">Процедура</option>
    <option value="fiat">Операция</option>
    <option value="audi">Таблетки</option>
  </select>
</form>
 <caption>Описание диагноза:</caption>
  <p><textarea name="comment"></textarea></p>
  <p><input type="submit" value="Добавить" ></p>
 </form>

</fieldset>
<script>
    var diagnosisTable = document.getElementById("diagnosis");
    var diagnosis = <%=  request.getAttribute("diagnosesList") %>;
    for (var i = 0;  i < diagnosis.length; i++) {
        var newRow = diagnosisTable.insertRow(i+1);
        newRow.addEventListener("click", mouseClick);
        for (var j = 0; j < 4; j++) {
            var newCell = newRow.insertCell(j);
            if (j === 0 ) {
                newCell.innerHTML=diagnosis[i]['diagnosisId'];
            } else if (j === 1) {
                newCell.innerHTML=diagnosis[i]['description']
            } else if (j === 2) {
                newCell.innerHTML=diagnosis[i]['time']
            }
        }
    }

    function mouseClick() {
        var rows = this.parentNode.rows;
        for (i=1; i < rows.length; i++) {
            rows[i].style.color = "black";
        }
        this.style.color = "red";
        $.post("test", {patientId : this.cells[0].innerHTML}, function(response) {
            var diagnoses = JSON.parse(response);
            updateDiagnosesTable(diagnoses);
        });
    }
    function updateDiagnosesTable(diagnoses) {
        var table = document.getElementById('diagnosis');
        $("#diagnosis tbody tr").remove();
        for (var i = 0;  i < diagnoses.length; i++) {
            var newRow = table.insertRow(i);
            for (var j = 0; j < 2; j++) {
                var newCell = newRow.insertCell(j);
                if (j === 0 ) {
                    newCell.innerHTML=diagnoses[i]['diagnosisId'];
                } else {
                    newCell.innerHTML=diagnoses[i]['description'];
                }
            }
        }
    }
</script>
</body>
</html>