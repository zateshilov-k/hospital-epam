<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <%--<style>--%>
        <%--ul.hr {--%>
            <%--margin: 0; /* Обнуляем значение отступов */--%>
            <%--padding: 4px; /* Значение полей */--%>
        <%--}--%>
        <%--ul.hr li {--%>
            <%--display: inline; /* Отображать как строчный элемент */--%>
            <%--margin-right: 5px; /* Отступ слева */--%>
            <%--border: 1px solid #000; /* Рамка вокруг текста */--%>
            <%--padding: 3px; /* Поля вокруг текста */--%>
        <%--}--%>
    <%--</style>--%>
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
            width: 100%;
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
<body style="background-color:powderblue">
<div class="wrapper">
    <nav class="navigation">
        <ul>
            <li><a href="#">Profile</a></li>
            <li><a href="/patient.jsp">Add patient</a></li>
            <li><a href="#">Logout</a></li>
        </ul>
    </nav>
</div>
<%--<div>--%>
    <%--<ul class="hr">--%>
        <%--<li><a class="" href="#">Profile</a></li>--%>
        <%--<li><a class="" href="/patient.jsp">Add patient</a></li>--%>
        <%--<li><a class="" href="#">Logout</a></li>--%>
    <%--</ul>--%>
<%--</div>--%>

<h1 style="text-align:center" font-size="28">Welcome, ${sessionScope.user.lastName} ${sessionScope.user.firstName}</h1>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<table id="example" class="table table-bordered table-hover" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>firstname</th>
        <th>lastname</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sessionScope.patients}" var="patient">
        <tr>
            <td>${patient.patientId}</td>
            <td>${patient.firstName}</td>
            <td>${patient.lastName}</td>
            <form name="Button" action="/patientCard" method="POST">
                <td>
                    <input type="hidden" name="patientId" value="${patient.patientId}"/>
                    <input type="submit" name="button" value="Перейти в карточку"/>
                </td>
            </form>
            <%--<td action="/patientCard" method="post"><a href="/patient/${patient.patientId}"> Перейти в карточку</a></td>--%>
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
</script>
</body>
</html>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>--%>
<%--<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>
<%--</head>--%>
<%--<body style="background-color:powderblue;">--%>
<%--<h1 style="text-align:center" font-size="28">Welcome, ${sessionScope.user.lastName} ${sessionScope.user.firstName}</h1>--%>


<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<p style="text-align:left" font-size="14">Choose the right patient</p>--%>
<%--<div class="col-md-3">--%>
<%--<form action="#" method="get">--%>
<%--<div class="input-group">--%>
<%--<!-- USE TWITTER TYPEAHEAD JSON WITH API TO SEARCH -->--%>
<%--<input class="form-control" id="system-search" name="q" placeholder="Search for" required>--%>
<%--<span class="input-group-btn">--%>
<%--<button type="submit" class="btn btn-default"><i--%>
<%--class="glyphicon glyphicon-search"></i></button>--%>
<%--</span>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<form action="/patientCard" method="post">--%>
<%--<div class="col-md-9">--%>
<%--<table class="table table-list-search">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th>#</th>--%>
<%--<th>Firstname</th>--%>
<%--<th>Lastname</th>--%>
<%--<th>Action</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--<tbody>--%>
<%--<c:forEach items="${sessionScope.patients}" var="patient">--%>
<%--<tr>--%>
<%--<td>${patient.patientId}</td>--%>
<%--<td>${patient.firstName}</td>--%>
<%--<td>${patient.lastName}</td>--%>
<%--<td name=""><a href="/patient/${patient.patientId}"> Перейти в карточку</a></td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="button1">--%>
<%--<button type="button1" onclick="href='/personalPatientCard.jsp'">New Patient</button>--%>
<%--</div>--%>
<%--<div class="button2">--%>
<%--<button type="button2" onclick="href='/personal.jsp'"> Change data</button>--%>
<%--</div>--%>
<%--<script>--%>
<%--$(document).ready(function () {--%>
<%--var activeSystemClass = $('.list-group-item.active');--%>

<%--//something is entered in search form--%>
<%--$('#system-search').keyup(function () {--%>
<%--var that = this;--%>
<%--// affect all table rows on in systems table--%>
<%--var tableBody = $('.table-list-search tbody');--%>
<%--var tableRowsClass = $('.table-list-search tbody tr');--%>
<%--$('.search-sf').remove();--%>
<%--tableRowsClass.each(function (i, val) {--%>

<%--//Lower text for case insensitive--%>
<%--var rowText = $(val).text().toLowerCase();--%>
<%--var inputText = $(that).val().toLowerCase();--%>
<%--if (inputText != '') {--%>
<%--$('.search-query-sf').remove();--%>
<%--tableBody.prepend('<tr class="search-query-sf"><td colspan="6"><strong>Searching for: "'--%>
<%--+ $(that).val()--%>
<%--+ '"</strong></td></tr>');--%>
<%--}--%>
<%--else {--%>
<%--$('.search-query-sf').remove();--%>
<%--}--%>

<%--if (rowText.indexOf(inputText) == -1) {--%>
<%--//hide rows--%>
<%--tableRowsClass.eq(i).hide();--%>

<%--}--%>
<%--else {--%>
<%--$('.search-sf').remove();--%>
<%--tableRowsClass.eq(i).show();--%>
<%--}--%>
<%--});--%>
<%--//all tr elements are hidden--%>
<%--if (tableRowsClass.children(':visible').length == 0) {--%>
<%--tableBody.append('<tr class="search-sf"><td class="text-muted" colspan="6">No entries found.</td></tr>');--%>
<%--}--%>
<%--});--%>
<%--});--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>