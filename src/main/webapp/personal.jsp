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

    <title>Add personal</title>
</head>
<body style="background-color:powderblue;overflow:hidden">


<main class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Создайте персонал в системе EPAM Hospital</div>
                    <div class="card-body">
                        <form action="/addPersonal" method="post">
                            <div class="form-group row">
                                <label for="field1" class="col-md-4 col-form-label text-md-right">Введите имя</label>
                                <div class="col-md-6">
                                    <input type="text" id="field1" class="form-control" name="firstName" required
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="field2" class="col-md-4 col-form-label text-md-right">Введите
                                    фамилию</label>
                                <div class="col-md-6">
                                    <input type="text" id="field2" class="form-control" name="lastName" required
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="field5" class="col-md-4 col-form-label text-md-right">Введите логин (e-mail) </label>
                                <div class="col-md-6">
                                    <input type="text" id="field5" class="form-control" name="login" required
                                           autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Введите
                                    пароль</label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-md-4 col-form-label text-md-right">Выберите роль</label>
                                <div class="col-md-6">
                                <br>
                                <select name="role">
                                  <option value="doctor">Doctor/Доктор</option>
                                  <option value="nurse">Nurse/Медсестра</option>
                                  <option value="admin">Administrator/Администратор</option>
                                </select>
                                </div>
                            </div>
                            <div class="form-group row" align="center">
                                <c:if test="${!empty requestScope.loginError}">
                                    <p><font color="red"><span class="error">${requestScope.personalError}</span></font>
                                    </p>
                                </c:if>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Сохранить
                                </button>
                                <a href="/main.jsp" class="btn btn-link">
                                    Отмена
                                </a>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>

</body>
</html>