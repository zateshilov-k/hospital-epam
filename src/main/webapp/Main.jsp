<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>

<h1 style="text-align:center;" style="font-size:220">Welcome, Username</h1>

<table>
  <script>
	var pacientTable = document.createElement('table'),
    tr = table.insertRow();
    for (i = 1; i < lastElementBD; i++) {
      String str = "1, Vasya Smirnov, bolen";
      String[] words = str.split("\\,"); // Разбиение строки на слова с помощью разграничителя (запятая)
      for(String subStr:words) {
         System.out.println(subStr);
      }
       tr.insertCell();
    }
  </script>
</table>

</body>
</html>