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
  padding: 15px;
}
</style>
</head>
<body style="background-color:powderblue;">

<h1 style="text-align:center;" style="font-size:220">Welcome,UserName</h1>
<h1 style="text-align:center;" style="font-size:220">Choose the right patient</h1>
<br>
  <div class="button1">
   <button type="button1">New Pacient</button>
   </div>
   <div class="button2">
    <button type="button2">Change data</button>
   </div>
   <br><br>
<table style="width:90%">
  <tr>
    <th>Id</th>
    <th>firstName and lastName</th>
  </tr>
  <tr>
    <td>Jill</td>
    <td>Smith</td>

  </tr>
  <tr>
    <td>Eve</td>
    <td>Jackson</td>
  </tr>
  <tr>
    <td>John</td>
    <td>Doe</td>
  </tr>
    <tr>
    <td>John</td>
    <td>Doe</td>
  </tr>
    <tr>
    <td>John</td>
    <td>Doe</td>
  </tr>
    <tr>
    <td>John</td>
    <td>Doe</td>
  </tr>
    <tr>
    <td>John</td>
    <td>Doe</td>
  </tr>

</table>
</body>
</html>