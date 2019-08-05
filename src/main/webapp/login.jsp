<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/5
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <style>
        form,a,span{
            display:block;
            margin:auto;
            text-align: center;
            width:800px;
        }
        center{
            margin-left: 300px;
        }

    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

</head>
<body>
    <br><br><br><br><br><br>
    <form action="/login.do" method="post" id="loginForm">
        账号：<input type="text" id="userId" name="userId">
        <br>
        密码：<input type="password" id="password" name="password">
        <br><br>
        <input type="button" value="提交" id="btn">
    </form>
    <br>
    <br><br><br><br>

    <center>
        <span id="ans"></span>
    </center>



    <br>
    <br>
    <br><br><br><br>
    <a href="#">注册</a>
</body>
</html>

<script type="text/javascript">
    $(document).ready(function() {
        $('#btn').click(

            function (){
                alert("你好");
                if (!$('#userId').val()) {
                    alert("请输入账号！");
                    return;
                }
                if (!$('#password').val()) {
                    alert("请输入密码！");
                    return;
                } else {
                    $.ajax({
                        type: "post",
                        url: "login.do",
                        data: $('#loginForm').serialize(),//序列化form表单发送过去
                        dataType: "json",
                        success: function (ans) {
                            var serverAns = ans.serverAns;
                            if (serverAns ===true) {
                                alert("欢迎来到爪哇部落");
                                $("#ans").text("登录成功").css({color: "green", position: "absolute"});
                                window.setTimeout("window.location.href='./view/home.jsp'", 1000);
                            } else {

                                $("#ans").text("您输入的用户名或者密码不正确").css({color: "red", position: "absolute"});
                            }

















                        }
                    });
                }
            })
    });







</script>