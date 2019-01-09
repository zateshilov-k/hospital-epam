<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="css/style.css">

    <link rel="icon" href="Favicon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <title>Войдите в систему EPAM Hospital</title>

</head>
<body style="background-color:powderblue">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="internationalization.resource" var="loc"/>
<fmt:message bundle="${loc}" key="loginField" var="enterEmail_msg"/>
<fmt:message bundle="${loc}" key="passwordField" var="enterPassword_msg"/>
<fmt:message bundle="${loc}" key="titleIndexPage" var="title_msg"/>
<fmt:message bundle="${loc}" key="buttonEnter" var="buttonEnter_msg"/>

<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">${title_msg}</div>
                    <div class="card-body">
                        <form action="/login" method="post">
                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right">${enterEmail_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="login" required
                                           autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">${enterPassword_msg}</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>

                            <div class="form-group row" align="center">

                                <c:if test="${!empty requestScope.loginError}">
                                    <p><font color="red"><span class="error">${requestScope.loginError}</span></font>
                                    </p>
                                </c:if>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary" id="loginSubmitButton">
                                    ${buttonEnter_msg}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
</body>
</html>