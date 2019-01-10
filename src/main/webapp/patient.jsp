<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Add patient</title>
</head>
<body style="background-color:powderblue;overflow:hidden">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="internationalization.resource" var="loc"/>
<fmt:message bundle="${loc}" key="titleAddPatientPage" var="titleAddPage_msg"/>
<fmt:message bundle="${loc}" key="firstNameField" var="firstName_msg"/>
<fmt:message bundle="${loc}" key="lastNameField" var="lastName_msg"/>
<fmt:message bundle="${loc}" key="buttonSave" var="buttonSave_msg"/>
<fmt:message bundle="${loc}" key="buttonCancel" var="buttonCancel_msg"/>
<fmt:message bundle="${loc}" key="patientErrorMsg" var="patientError_msg"/>

<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">${titleAddPage_msg}</div>
                    <div class="card-body">
                        <form action="/addPatient" method="post">
                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right">${firstName_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" class="form-control" name="firstName" required
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address"
                                       class="col-md-4 col-form-label text-md-right">${lastName_msg}</label>
                                <div class="col-md-6">
                                    <input type="text" id="password" class="form-control" name="lastName" required
                                           autofocus>
                                </div>
                            </div>

                            <div class="form-group row" align="center">

                                <c:if test="${!empty requestScope.patientError}">
                                    <p><font color="red"><span class="error">${patientError_msg}</span></font>
                                    </p>
                                </c:if>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
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
    </div>
    </div>
</main>

</body>
</html>