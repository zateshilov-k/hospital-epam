<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head >

 <style>
   .button1 {
    position: absolute; /* Относительное позиционирование */
    left: 400px;
    top: 235px;
    line-height: 300px;
   }
      .button2 {
    position: absolute; /* Относительное позиционирование */
    left: 100px;
    top: 395px;
    line-height: 300px;
   }
      .button3 {
    position: absolute; /* Относительное позиционирование */
    left: 100px;
    top: 516px;
    line-height: 300px;
   }
         .button4 {
    position: absolute; /* Относительное позиционирование */
    left: 150px;
    top: 616px;
    line-height: 300px;
   }
  </style>


  <meta charset="utf-8">
<h1 style="text-align:center;" style="font-size:220">Personal patient card</h1>
 </head>
 <body style="background-color:powderblue;">

 <form action="handler.php">
  <p><b>For diagnosis</b></p>
  <p><textarea name="comment"></textarea></p>
 </form>
  <form action="handler.php">
  <p><b>
  Назначьте лечение:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="assign">


 <div class="button1">
   <button type="button1" onclick="href='#">perform</button>
   </div>



 </form>
  <form action="handler.php">
  <p><b>Назначьте операцию:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="assign">

<div class="button2">
   <button type="button2" onclick="href='#'">perform</button>
   </div>


 </form>
  <form action="handler.php">
  <p><b>Назначьте процедуру:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="assign">

<div class="button3">
   <button type="button3" onclick="href='#'">perform</button>
   </div>


   <br><br><br>

 </form>
<div class="button4">
   <button type="button3" onclick="href='#'">Heal</button>
   </div>
 </body>
</html>