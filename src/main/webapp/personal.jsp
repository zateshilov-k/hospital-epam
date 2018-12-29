<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<h1 style="text-align:center;" style="font-size:220">Change user data</h1>
<form action = "Save" method="post" style="text-align:center;" action = "Back" method="post" style="text-align:center;">
<fieldset>
  Change firstName:<br>
  <input type ="text" name= "firstName">
  <br>
  Change lastName:<br>
  <input type ="text" name= "lastName">
  <br>
  Change login:<br>
  <input type="text" name= "login">
  <br>
  Change password:<br>
  <input type="text" name= "password">
<br>
<br>
<button style="text-align:center;" onclick="href='/Main.jsp'">Save</button>
<button style="text-align:center;" onclick="href='/Main.jsp'">Back</button>
</fieldset>
</form>
</body>
</html>
