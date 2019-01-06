<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html id = "personals">
<head>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        .wrapper{
            float:left;
            width:100%;
            min-height:20px;
        }
        .navigation{
            float: left;
            width: 100%;
            text-align: center;
        }
        .navigation ul{
            margin: 0;
            padding: 0;
            float: none;
            width: auto;
            list-style: none;
            display: inline-block;
        }
        .navigation ul li{
            float: left;
            width: auto;
            margin-right: 60px;
            position: relative;
        }
        .navigation ul li a{
            float: left;
            width: 90%;
            color: #333;
            padding: 16px 0;
            font-size: 16px;
            line-height: normal;
            text-decoration:none;
            box-sizing:border-box;
            text-transform: uppercase;
            font-family: 'Montserrat', sans-serif;      -webkit-transition:color 0.3s ease;
            transition:color 0.3s ease;
        }
    </style>
</head>
<body style="background-color:powderblue;overflow:hidden">

<h1 style="text-align:center" font-size="28">Welcome, ${sessionScope.user.lastName} ${sessionScope.user.firstName}</h1>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<table id="example" class="table table-bordered table-hover" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>role</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.personals}" var="personal">
        <tr>
            <td>${personals.patientId}</td>
            <td>${personals.firstName}</td>
            <td>${personals.lastName}</td>
             <td>${personals.role}</td>
            <form name="Button" action="/patientCard" method="POST">
                <td>
                    <input type="hidden" name="personalId" value="${personal.personalId}"/>
                    <input type="submit" name="button" value="Перейти в профиль"/>
                </td>
            </form>
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

    var personalsTable = document.getElementById('example');
    var personalRow = personalsTable.rows;
    for (i = 1; i < personalRow.length; ++i) {
        personalRow[i].addEventListener("click",personalClick);
    }

    function personalClick() {
        var rows = this.parentNode.rows;
        var URL = "/personalPatientCard.jsp";
        for (i=0; i < rows.length; i++) {
            rows[i].style.color = "black";
        }
        this.style.color = "red";
        var button = document.getElementById('buttonToPatientPage');
        button.value = this.cells[0].innerHTML;
    }
</script>
</body>
</html>