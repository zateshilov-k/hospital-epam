<!DOCTYPE html>
<html>
<body style="background-color:powderblue;">
<h1 style="text-align:center;" style="font-size:200"> Welcome to Hospital!</h1>
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
 <button style="text-align:center;">Sign Up</button>
</fieldset>
</form>
        <div>
        <script>
            <c:if test="empty requestScope.loginError">
                <p><span class="error">requestScope.loginError</span></p>
            </c:if>
         </script>
        </div>
</body>
</html>
