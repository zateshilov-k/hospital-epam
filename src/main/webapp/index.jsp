<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
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

</fieldset>
</form>
        <div>
            <c:if test="${!empty requestScope.loginError}">
                <p><span class="error">${requestScope.loginError}</span></p>
            </c:if>
        </div>
<button style="text-align:center;" onclick="href='/pageOfSignUp.jsp'">Sign Up</button>
<a class="btn btn-default" href="/signUp.jsp" role="button">Link</a>
</body>
</html>
