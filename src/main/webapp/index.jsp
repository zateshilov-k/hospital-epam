<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<h1 style="text-align:center; font-size:28px" > Welcome to Hospital!</h1>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<form action = "/login" method="post" style="text-align:center;">
  <fieldset>
    <legend></legend>
    Login:<br>
    <input type="text" name="login">
    <br>
    Password:<br>
    <input type="text" name="password">
    <br>
    <br>
    <button style="text-align:center;">Sign in</button>
    <br>
    <br>
  </fieldset>
</form>
<div>

  <c:if test="${!empty requestScope.loginError}">
    <p><span class="error">${requestScope.loginError}</span></p>
  </c:if>

  <br/>${loginError}
</div>
</body>
</html>