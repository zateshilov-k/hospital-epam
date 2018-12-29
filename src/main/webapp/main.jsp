<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<h1 style="text-align:center;" style="font-size:220">Welcome, ${name} ${sessionScope.user.lastName} ${sessionScope.user.firstName} </h1>
${sessionScope.user}



</body>
</html>