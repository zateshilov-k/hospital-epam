<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
 <head >
  <meta charset="utf-8">
<h1 style="text-align:center;" style="font-size:220">Личная карточка</h1>
 </head>
 <body style="background-color:powderblue;">

 <form action="handler.php">
  <p><b>Для диагноза</b></p>
  <p><textarea name="comment"></textarea></p>
 </form>
  <form action="handler.php">
  <p><b>
  Назначьте лечение:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="Назначить">
 </form>
  <form action="handler.php">
  <p><b>Назначьте операцию:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="Назначить">
 </form>
  <form action="handler.php">
  <p><b>Назначьте процедуру:</b></p>
  <p><textarea name="comment"></textarea></p>
<p><input type="submit" value="Назначить">
   <br><br><br>

 </form>
  <button style="vertical-align:middle">Лечить
   </button>
 </body>
</html>