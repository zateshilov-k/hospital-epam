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
     caption>Изменение добычи ресурсов по годам</caption>
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
<h1 style="text-align:center;" style="font-size:220">Welcome, ${name} ${surname}</h1>
<h1 style="text-align:center;" style="font-size:220">Choose the right patient</h1>
<br>
  <div class="button1">
   <button type="button1">New Pacient</button>
   </div>
   <div class="button2">
    <button type="button2">Change data</button>
   </div>
 <br><br>

 <table style="width:100%">
  <caption>Patient</caption>
  <script>
  window.onload = functional(){
  var newTable=document.createElement("table")
    for(x=0; x<personalId.length-1;x++){
      var newRow=newTable.insertRow(x);
       for(y=0; y<2;y++){
         var newCell=newRow.insertCell(y)

        if(x===0 && y===0){
        	newCell.innerHTML="Id";
        	newCell.width=50;
        }
        else if(x===0 && y===1){
        	newCell.innerHTML="Patient";
          	newCell.width=50;
        }
        else if(y===0 && x>0){
       	   newCell.classname="id";
       	   newCell.innerHTML=x;
       	   newCell.width=50;
        }
        else (y=1 && x>0 ){
          var str="${name}" + "${surname}";
           newCell.innerHTML=str;
           newCell.width=50;
       }
      }
    }
    document.body.appendChild(newTable);
  }
 </script>
 </table>
 <br>
</body>
</html>