<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<style>
   .button1 {
    position: absolute;
    left: 300px;
    top: 16px;
    line-height: 300px;
   }
   .button2 {
    left: 200px;
    position: absolute;
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
  <%--<script>--%>
  <%--window.onload = function(){--%>
  <%--var newTable=document.createElement("table");--%>
    <%--for( var x=0; x<${sessionScope.patients.size()};x++){--%>
        <%--var patient = ${sessionScope.patients.get(x)};--%>
      <%--var newRow=newTable.insertRow(x);--%>
       <%--for( var y=0; y<2;y++){--%>
         <%--var newCell=newRow.insertCell(y);--%>
        <%--if(x===0 && y===0){--%>
        	<%--newCell.innerHTML="Id";--%>
        	<%--newCell.width=50;--%>
        <%--}--%>
        <%--else if(x===0 && y===1){--%>
        	<%--newCell.innerHTML="Patient";--%>
          	<%--newCell.width=50;--%>
        <%--}--%>
        <%--else if(y===0 && x>0){--%>
       	   <%--newCell.classname="id";--%>
       	   <%--newCell.innerHTML=patient.patientId;--%>
       	   <%--newCell.width=50;--%>
        <%--}--%>
        <%--else {--%>
          <%--var str="patient.firstName" + "patient.lastName";--%>
           <%--newCell.innerHTML=str;--%>
           <%--newCell.width=50;--%>
       <%--}--%>
      <%--}--%>
    <%--}--%>
    <%--document.body.appendChild(newTable);--%>
  <%--}--%>
 <%--</script>--%>
</head>
<body style="background-color:powderblue;">
<h1 style="text-align:center" font-size="28">Welcome, ${sessionScope.user.lastName} ${sessionScope.user.firstName}</h1>
<h1 style="text-align:center" font-size="22">Choose the right patient</h1>

<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>UID</td>
        <td>Name</td>
    </tr>

    <c:forEach items="${sessionScope.patients}" var="patient">
        <tr>
            <td>${patient.patientId}</td>
            <td>${patient.firstName} ${patient.lastName}</td>
        </tr>
    </c:forEach>
</table>




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