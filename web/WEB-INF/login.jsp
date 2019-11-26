<%@ page import="controller.util.ServletUtil" %>
<%@ page import="attribute.AttrServlet" %><%--
  Created by IntelliJ IDEA.
  User: nullpo299
  Date: 2019/09/22
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <title>Bootstrap Sample</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- BootstrapのJS読み込み -->
    <script src="../js/bootstrap.min.js"></script>
    <!-- fontの読み込み-->
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- 自分のcss , js の読み込み-->
    <script src="../myJs/login.js"></script>
    <link rel="stylesheet" href="../myCss/login.css">
</head>
<!--
    user_id
    password
-->
<body>
<header><h1>MyDrive Login</h1></header>
<div class="container-fluid">
    <form id="form" action="<%=AttrServlet.LOGIN.getUrl(true)%>" method="post"></form><!--actionよろしく-->
    <div class="row">
        <div class="col-sm-8 offset-sm-2 d-flex align-items-center flex-column">
            <div class="form_container">
                <label for="UserID">UserID</label>
                <input type="text" id="UserID" form="form" name="user-id" required>
            </div>
            <div class="form_container">
                <label for="Pass">PassWord</label>
                <input type="password" id="Pass" name="password" form="form" required>
                <span class="pass_icon"><a href="#"><i id="eye" class="material-icons">visibility</i></a></span>
            </div>
            <div class="form_container">
                <input type="submit" value="Login" form="form">
            </div>
        </div>
    </div>
</div>
<p>If you don't have an account , click <a href="<%=AttrServlet.REGISTER.getUrl(true)%>">here</a></p>
<p>If you forget your ID or Password , click <a href="#">here</a></p>
<footer><small>&copy;H&amp;H 2019</small></footer>
</body>
</html>
