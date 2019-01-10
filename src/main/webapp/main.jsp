<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html id = "main">
<head>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <style>
        .wrapper {
            float: left;
            width: 100%;
            min-height: 20px;
        }

        .navigation {
            float: left;
            width: 100%;
            text-align: center;
        }

        .navigation ul {
            margin: 0;
            padding: 0;
            float: none;
            width: auto;
            list-style: none;
            display: inline-block;
        }

        .navigation ul li {
            float: left;
            width: auto;
            margin-right: 60px;
            position: relative;
        }

        .navigation ul li a {
            float: left;
            width: 100%;
            color: #333;
            padding: 16px 0;
            font-size: 16px;
            line-height: normal;
            text-decoration: none;
            box-sizing: border-box;
            text-transform: uppercase;
            font-family: 'Montserrat', sans-serif;
            -webkit-transition: color 0.3s ease;
            transition: color 0.3s ease;
        }
    </style>
    <title>Главная страница</title>
</head>
<body style="background-color:powderblue">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="internationalization.resource" var="loc"/>
<fmt:message bundle="${loc}" key="currentUser" var="currentUser_msg"/>
<fmt:message bundle="${loc}" key="menuAddPatient" var="menuAddPatient_msg"/>
<fmt:message bundle="${loc}" key="menuLogout" var="menuLogout_msg"/>
<fmt:message bundle="${loc}" key="columnId" var="columnId_msg"/>
<fmt:message bundle="${loc}" key="columnFirstName" var="columnFirstName_msg"/>
<fmt:message bundle="${loc}" key="columnLastName" var="columnLastName_msg"/>
<fmt:message bundle="${loc}" key="columnAction" var="columnAction_msg"/>
<fmt:message bundle="${loc}" key="buttonOpenCard" var="buttonOpenCard_msg"/>
<fmt:message bundle="${loc}" key="openPatientCard" var="openPatientCard_msg"/>

<div class="wrapper">
    <nav class="navigation">
        <ul>
            <li>
                <a href="#">${currentUser_msg} ${sessionScope.user.role} ${sessionScope.user.lastName} ${sessionScope.user.firstName}</a>
            </li>
            <c:if test="${sessionScope.user.role eq 'DOCTOR'}">
                <li><a href="/patient.jsp">${menuAddPatient_msg}</a></li>
            </c:if>
            <li><a href="/logout">${menuLogout_msg}</a></li>
        </ul>
    </nav>
</div>

<form action="/patientCard" method="post">
    <button type="submit" name="patientId" id="buttonToPatientPage"
            value="your_value" class="btn-link">${openPatientCard_msg}</button>
</form>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<table id="example" class="table table-bordered table-hover" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>${columnId_msg}</th>
        <th>${columnFirstName_msg}</th>
        <th>${columnLastName_msg}</th>
        <th>${columnAction_msg}</th>
        <%--<th>Update</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.patients}" var="patient">
        <tr>
            <td>${patient.patientId}</td>
            <td>${patient.firstName}</td>
            <td>${patient.lastName}</td>
            <td>
                <form name="Button" action="/patientCard" method="POST" style="display:inline">
                    <input type="hidden" name="patientId" value="${patient.patientId}"/>
                    <input type="submit" name="button" value="${buttonOpenCard_msg}"/>
                </form>
                <form name="Button" action="/patientProfile" method="POST" style="display:inline">
                    <input type="hidden" name="patientId" value="${patient.patientId}"/>
                    <input type="hidden" name="firstName" value="${patient.firstName}"/>
                    <input type="hidden" name="lastName" value="${patient.lastName}"/>
                    <input type="submit" name="button" value="Update Profile"/>
                </form>
            </td>
        </tr>
    </c:forEach>



    </tbody>
</table>

<script src="https://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });

    var patientsTable = document.getElementById('example');
    var patientRow = patientsTable.rows;
    for (i = 1; i < patientRow.length; ++i) {
        patientRow[i].addEventListener("click", patientClick);
    }

    function patientClick() {
        var rows = this.parentNode.rows;
        var URL = "/personalPatientCard.jsp";
        for (i = 0; i < rows.length; i++) {
            rows[i].style.color = "black";
        }
        this.style.color = "red";
        var button = document.getElementById('buttonToPatientPage');
        button.value = this.cells[0].innerHTML;
    }
</script>
</body>
</html>
