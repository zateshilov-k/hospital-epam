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
<fmt:message bundle="${loc}" key="titleUpdatePatientPage" var="titleUpdatePatientPage_msg"/>
<fmt:message bundle="${loc}" key="firstNameField" var="firstNameField_msg"/>
<fmt:message bundle="${loc}" key="lastNameField" var="lastNameField_msg"/>
<fmt:message bundle="${loc}" key="buttonSave" var="buttonSave_msg"/>
<fmt:message bundle="${loc}" key="buttonCancel" var="buttonCancel_msg"/>


<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">${titleUpdatePatientPage}</div>
                    <div class="card-body">
                        <form action="/patientProfile" method="post">
                            <div class="form-group row">
                                <label for="field1" class="col-md-4 col-form-label text-md-right">${firstNameField}</label>
                                <div class="col-md-6">
                                    <input type="text" id="field1" class="form-control" name="firstName" required
                                           autofocus value="${currentPatient.firstName}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="field2" class="col-md-4 col-form-label text-md-right">${lastNameField}</label>
                                <div class="col-md-6">
                                    <input type="text" id="field2" class="form-control" name="lastName" required
                                           autofocus value="${currentPatient.lastName}">
                                </div>
                            </div>
                            <%--<div class="form-group row">--%>
                                <%--<label for="field2" class="col-md-4 col-form-label text-md-right">Not Active</label>--%>
                                <%--<div class="col-md-6">--%>
                                    <%--<input type="checkbox" id="field3" class="form-control" name="isDeleted">--%>
                                <%--</div>--%>
                            <%--</div>--%>



                            <div class="form-group row" align="center">
                                <c:if test="${!empty requestScope.loginError}">
                                    <p><font color="red"><span class="error">${requestScope.personalError}</span></font>
                                    </p>
                                </c:if>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit"  name="patientId" value="${currentPatient.patientId}" class="btn btn-primary">
                                    ${buttonSave_msg}
                                </button>
                                    <a href="/main.jsp" class="btn btn-link">
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
