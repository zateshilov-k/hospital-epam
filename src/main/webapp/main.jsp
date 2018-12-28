<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<style>
   .button1 {
    position: absolute; /* Относительное позиционирование */
    left: 300px;
    top: 16px;
    line-height: 300px;
   }
   .button2 {
    left: 200px;
    position: absolute; /* Относительное позиционирование */
   }

table, th, td {
  border: 2px solid white;
}
th, td {
  padding: 13px;
}
</style>
</head>
<body style="background-color:powderblue;">

<h1 style="text-align:center;" style="font-size:220">Welcome, ${name} ${surname}</h1>

<h1 style="text-align:center;" style="font-size:220">Choose the right patient</h1>

<br>
  <div class="button1">
   <button type="button1">New Pacient</button>
   </div>
   <div class="button2">
    <button type="button2">Change data</button>
   </div>
   <br><br>
<table class="table" style="width:100%">
  <tr>
    <th>Id</th>
    <th>firstName and lastName</th>
  </tr>

  <tr>
    <td>${personalId}</td>
      <td>
       <a href="/personalPatientCard.jsp'" style="position: absolute";>${name} ${surname}</a>
    </td>
  </tr>
  <tr>
    <td>${personalId}</td>
    <td>
    <a href="/personalPatientCard.jsp'" style="position: absolute";>${name} ${surname}</a>
    </td>
  </tr>
    <tr>
    <td>${personalId}</td>
      <td>
    <a href="'/personalPatientCard.jsp'" style="position: absolute";>${name} ${surname}</a>
</table>
<br>
</body>
</html>
