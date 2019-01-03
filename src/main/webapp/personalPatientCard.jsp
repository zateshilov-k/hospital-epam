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


<h2 style="text-align:center;" style="font-size:28px" >Личная карточка пациента</h2>
<br>

<button style="text-align:center;" onclick="href='/main.jsp'">To Main</button>
<br>
<br>

<fieldset >
<legend >История болезней</legend>
<button onclick="myFunction()">Close diagnosis</button>
<br><br>
<table style="display:inline; float:left">
  <caption>Диагнозы:</caption>
  <tr>
    <th>Id</th>
    <th>Описание</th>
    <th>Время</th>
    <th>Открыт</th>
    <th>Действие</th>
  </tr>
  <tr>
    <td>1</td>
    <td>Грипп</td>
    <td>12.02.2018, 9:30</td>
    <td>Нет</td>
    <td> <a href="#">Тык</a></td>
  </tr>
    <tr>
    <td>2</td>
    <td>Грипп</td>
    <td>12.02.2018, 9:30</td>
    <td>Нет</td>
    <td> <a href="#">Тык</a></td>
  </tr>
    <tr>
    <td>3</td>
    <td>Грипп</td>
    <td>12.02.2018, 9:30</td>
    <td>Нет</td>
    <td> <a href="#">Тык</a></td>
  </tr>
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
    <th>Статус</th>
  </tr>
  <tr>
    <td>1</td>
    <td>Капотен</td>
    <td>Лекарство</td>
    <td>12.08.2018,9:30</td>
    <td>Выполнен</td>
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

</body>
</html>