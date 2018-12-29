<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head >
  <meta charset="utf-8">
<h1 style="text-align:center;" style="font-size:220">Personal patient card</h1>
 </head>
 <body style="background-color:powderblue;">

 <form >
  <p><b>For diagnosis</b></p>
  <p><textarea name="comment"></textarea></p>
  <p><input type="submit" value="add new diagnosis"></p>
 </form>
  <form >
  <p><b>
  Назначьте лечение:</b></p>
  <p><textarea name="comment"></textarea></p>
<input type="submit" value="assign">
<input type="submit" value="perform">

 </form>
  <form >
  <p><b>Назначьте операцию:</b></p>
  <p><textarea name="comment"></textarea></p>
<input type="submit" value="assign">
<input style="text-align:center;" type="submit" value="perform"></p>

 </form>
  <form >
  <p><b>Назначьте процедуру:</b></p>
  <p><textarea name="comment"></textarea></p>
  <input type="submit" value="assign">
<input style="text-align:center;" type="submit" value="perform"></p>
   <br>

 </form>
   <button>Heal</button>
 </body>
</html>