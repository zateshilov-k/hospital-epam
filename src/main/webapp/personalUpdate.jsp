<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

    <title>Add personal</title>
</head>
<body style="background-color:powderblue;overflow:hidden">
<fmt:setLocale value="${sessionScope.locale}"/>
<%--<fmt:setLocale value="de-GR"/>--%>
<fmt:setBundle basename="internationalization.resource" var="loc"/>
<fmt:message bundle="${loc}" key="titleAddPersonalPage" var="titleAddPersonalPage_msg"/>
<fmt:message bundle="${loc}" key="firstNameField" var="firstNameField_msg"/>
<fmt:message bundle="${loc}" key="lastNameField" var="lastNameField_msg"/>
<fmt:message bundle="${loc}" key="loginField" var="loginField_msg"/>
<fmt:message bundle="${loc}" key="passwordField" var="passwordField_msg"/>
<fmt:message bundle="${loc}" key="chooseRoleField" var="chooseRoleField_msg"/>
<fmt:message bundle="${loc}" key="buttonSave" var="buttonSave_msg"/>
<fmt:message bundle="${loc}" key="buttonCancel" var="buttonCancel_msg"/>
<fmt:message bundle="${loc}" key="titleUpdatePersonalPage" var="titleUpdatePersonalPage_msg"/>


<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">${titleUpdatePersonalPage_msg}</div>
                    <div class="card-body">
                        <form action="/personalUpdate" method="post">
                            <div class="form-group row">
                                <label for="field1"
                                       class="col-md-4 col-form-label text-md-right">${firstNameField_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="field1" class="form-control" name="firstName" required
                                           autofocus value="${currentPersonal.firstName}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="field2"
                                       class="col-md-4 col-form-label text-md-right">${lastNameField_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="field2" class="form-control" name="lastName" required
                                           autofocus value="${currentPersonal.lastName}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="field5"
                                       class="col-md-4 col-form-label text-md-right">${loginField_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="field5" class="form-control" name="login" required
                                           autofocus value="${currentPersonal.login}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password"
                                       class="col-md-4 col-form-label text-md-right">${passwordField_msg}</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>
                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <div class="form-group row">
                                    <label class="col-md-4 col-form-label text-md-right">${chooseRoleField_msg}ь</label>
                                    <div class="col-md-6">
                                        <br>
                                        <select name="role">
                                            <option value="doctor">Doctor/Доктор</option>
                                            <option value="nurse">Nurse/Медсестра</option>
                                            <option value="admin">Administrator/Администратор</option>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group row" align="center">
                                <c:if test="${!empty requestScope.loginError}">
                                    <p><font color="red"><span class="error">${requestScope.personalError}</span></font>
                                    </p>
                                </c:if>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" name="personalId" value="${currentPersonal.personalId}"
                                        class="btn btn-primary">
                                    ${buttonSave_msg}
                                </button>
                                <a href="/personals.jsp" class="btn btn-link">
                                    ${buttonCancel_msg}
                                </a>
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