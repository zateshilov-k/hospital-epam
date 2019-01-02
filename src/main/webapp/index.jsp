<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<h1 style="text-align:center;" style="font-size:28px"> Welcome to Hospital!</h1>
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
 <button style="text-align:center;" onclick="href='/signUp.jsp'">Sign Up</button>
</fieldset>
</form>
        <div>
            <c:if test="${!empty requestScope.loginError}">
                <p><span class="error">${requestScope.loginError}</span></p>
            </c:if>
        </div>
</body>
</html>
