<%@ page import="model.util.servlet.ServletUtil" %><%--
  Created by IntelliJ IDEA.
  User: nullpo299
  Date: 2019/09/24
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    final String SERVLET_LOGIN = ServletUtil.getSERVLET_LOGIN(true);
    final String SERVLET_REGISTER = ServletUtil.getSERVLET_REGISTER(true);
%>
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
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans+SC&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="../js/bootstrap.min.js"></script>
    <!--自前のファイル読み込み-->
    <link href="../myCss/register.css" rel="stylesheet">
    <script src="../myJs/register.js"></script>
</head>

<!--
パラメータ一覧
---ajax----
    emailAddress
    lastName
    firstName
    secretCodes

----Confirmでの一括送信------
    email-address
    last-name
    first-name
    user-id
    password
    question
    answer
    openness
-->

<body>
<header>
    <h1>MyDrive</h1>
</header>

<div class="loader-wrap">
    <div class="loader">Loading...</div>
</div>

<div class="container-fluid">
    <form action="#" id="Form" onsubmit="return false;"></form>
    <div class="row">
        <div class="col-lg-6 offset-lg-3 col-sm-10 offset-sm-1">
            <div class="step-progress-bar" id="Step-progress-bar"><!--step-progress-bar-->
                <ul>
                    <li id="Current"><p>Email</p></li>
                    <li><p>Certification</p></li>
                    <li><p>Settings</p></li>
                    <li><p>Secret<br>Question</p></li>
                    <li><p>Confirm</p></li>
                </ul>
            </div>
            <div id="content">
                <div id="Email"> <!--Emailのステップ-->
                    <div class="form-container">
                        <label for="Mail"><img src="../picture/email.png" alt="icon"></label>
                        <input type="email" id="Mail" placeholder="Email" form="Form" required>
                    </div>
                    <div class="form-container">
                        <label for="Last"><img src="../picture/person.png" alt="icon"></label>
                        <input form="Form" id="Last" placeholder="LastName" required type="text">
                        <input form="Form" id="First" placeholder="FirstName" required type="text">
                    </div>
                </div>
                <div id="Certification" hidden><!--Certificationのステップ-->
                    <div class="message">
                        Please input the secret cord<br>
                        which transmitted a message to your e-mail address
                    </div>
                    <div class="code-group">
                        <input type="text" class="code form-control" placeholder="0" maxlength="1" form="Form" required
                               autofocus>
                        <input type="text" class="code form-control" placeholder="0" maxlength="1" form="Form" required>
                        <input type="text" class="code form-control" placeholder="0" maxlength="1" form="Form" required>
                        <input type="text" class="code form-control" placeholder="0" maxlength="1" form="Form" required>
                    </div>
                </div>
                <div id="Settings" hidden><!--Settingsのステップ-->
                    <div class="setting-form">
                        <div class="form-container id-inspection">
                            <label for="Id">User ID</label>
                            <input type="text" id="Id" form="Form" required>
                            <div id="id-status"></div>
                        </div>
                    </div>
                    <div class="setting-form">
                        <div class="form-container">
                            <label for="Password">Password</label>
                            <input type="password" id="Password" form="Form" required>
                        </div>
                        <div class="form-container pass-inspection">
                            <label for="Pass-confirm">Password Again</label>
                            <input type="password" id="Pass-confirm" form="Form" required>
                        </div>
                    </div>
                    <div class="setting-form">
                        <span class="openness-description">Do you want to publish your account?</span>
                        <div class="switch-area">
                            <input type="checkbox" id="openness">
                            <label for="openness"></label>
                            <div id="Circle"></div>
                        </div>
                    </div>
                </div>
                <div id="Secret-question" hidden>
                    <p>Please choose a question and answer in the bottom form</p>
                    <div class="form-container">
                        <select id="select-menu" required>
                            <option value="0" selected disabled>choose a question</option>
                        </select>
                    </div>
                    <div class="form-container">
                        <input type="text" id="answer" form="Form">
                    </div>
                </div><!--Secret-Questionのステップ-->
                <div id="Confirm" hidden>
                    <div class="form-container">
                        <label for>ID</label>
                        <input type="text" id="confirm-id" disabled>
                    </div>
                    <div class="form-container">
                        <label for>Password</label>
                        <input type="password" id="confirm-pass" disabled>
                    </div>
                    <div class="form-container openness">
                        <label>openness</label>
                        <div class="switch-container">
                            <div class="switch-area">
                                <input type="checkbox" id="confirm-openness" disabled>
                                <label for="confirm-openness"></label>
                                <div id="confirm-circle"></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-container">
                        <label for>Question</label>
                        <input type="text" id="confirm-question" disabled>
                    </div>
                    <div class="form-container">
                        <label for>Answer</label>
                        <input type="text" id="confirm-answer" disabled>
                    </div>
                </div>
            </div>
            <div class="button-container">
                <input type="submit" id="Submit" value="Send" class="button" form="Form" disabled>
            </div>
        </div>
    </div>
</div>
<p id="Description">If you already have an account,click <a href="<%=SERVLET_LOGIN%>">here</a></p>
<footer>
    <small>&copy;H&amp;H 2019</small>
</footer>
</body>
</html>
