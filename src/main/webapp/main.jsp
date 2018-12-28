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
<table class="table" style="width:100%">
  <tr>
    <th>Id</th>
    <th>firstName and lastName</th>
  </tr>

  <tr>
    <td>Eve</td>
      <td>
       <a href="#" style="position: absolute";>Sasha Alexandrovich</a>
    </td>
  </tr>
  <tr>
    <td>John</td>
    <td>

    <a href="#" style="position: absolute";>Jill Alexandrovich</a>

    </td>
  </tr>
    <tr>
    <td>John</td>
      <td>

    <a href="#" style="position: absolute";>Jimmy Alexandrovich</a>

    </td>
  </tr>
    <tr>
    <td>John</td>
    <td>

    <a href="#" style="position: absolute";>Mike Alexandrovich</a>

    </td>
  </tr>
    <tr>
    <td>John</td>
    <td>

    <a href="#" style="position: absolute";>Oleg Alexandrovich</a>

    </td>
  </tr>
    <tr>
    <td>John</td>
    <td>

    <a href="#" style="position: absolute";>Vasya Alexandrovich</a>

    </td>
  </tr>

</table>
</body>
</html>