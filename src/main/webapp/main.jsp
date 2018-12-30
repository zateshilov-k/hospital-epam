<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<style>
   .button1 {
    position: absolute; /* Относительное позиционирование */
    left: 300px;
    top: 16px;
    line-height: 300px;
   }
   .button2 {
    left: 200px;
    position: absolute; /* Относительное позиционирование */
   }
	table{

	  border-collapse: collapse;
	}
	td{
	  border: 1px solid black;
	  padding: 3px;
	  text-align: center;
	}
    .id{
    background-color:yellow;
    font-weight: bold;
    text-align: center;
    }

</style>

</head>
<body style="background-color:powderblue;">
<h1 style="text-align:center" font-size="28">Welcome, ${sessionScope.user.lastName} ${sessionScope.user.firstName}</h1>
<h1 style="text-align:center" font-size="22">Choose the right patient</h1>


<p>End of collections</p>
<c:forEach var="patient" items="${sessionScope.patients}">
    <td><div align="center"><b> <c:out  value="${patient.firstName}"/>
    </b></div></td>
</c:forEach>

<br>
  <div class="button1">
   <button type="button1" onclick="href='/personalPatientCard.jsp'" >New Patient</button>
   </div>
   <div class="button2">
    <button type="button2" onclick="href='/personal.jsp'" > Change data </button>
   </div>
 <br><br>
 <br>
</body>
</html>