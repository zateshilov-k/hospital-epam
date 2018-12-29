<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<h1 style="text-align:center;" style="font-size:220">Create new patient</h1>
<form action = "Save" method="post" style="text-align:center;" action = "Cancel" method="post" style="text-align:center;">
<fieldset>
  Input firstName:<br>
  <input type ="text" name= "firstName">
  <br>
  Input lastName:<br>
  <input type ="text" name= "lastName">
  <br>
<br>
<button style="text-align:center;" onclick="href='/Main.jsp'">Save</button>
<button style="text-align:center;" onclick="href='/Main.jsp'">Cancel</button>
</fieldset>
</form>
</body>
</html>