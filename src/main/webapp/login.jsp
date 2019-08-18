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


    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>


    <!-- 1. 导入CSS的全局样式 -->
    <!-- 1. 导入CSS的全局样式 -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/jquery-2.1.0.min.js"></script>
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <%--<script src="/js/jquery-1.12.4.min.js"></script>--%>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="/js/bootstrap.min.js"></script>


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
        form{
            width: 300px;
            position: relative;
        }
        label{
            float: left;
        }

        .code{
            width: 106px;
            height: 20px;
        }

        #vcode{


            display: block;

            position: absolute;
            left: 193px;
            top: 175px;
            height: 23px;
            width: 105px;

        }
        .center{
            text-align: center;
        }
        .center *{
            display:block;
            margin:auto;
        }

    </style>
    <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>

</head>
<body>

    <h1>${loginUser==null? "您还没有登录":"已经登录"}</h1>


    <br><br><br><br><br><br>
    <form action="/login.do" method="post" id="loginForm">
        <label for="userId">账号：</label><input type="text" id="userId" name="userId"   class="form-control">
        <br>
        <label for="password">
            密码：
        </label><input type="password" id="password" name="password"   class="form-control">
        <br><br>

        <label for="userId">验证码：</label>
        <input type="text" id="code" name="code"   class="form-control code">
        <img src="/checkCodeServlet" title="看不清点击刷新" id="vcode"  onclick="refreshCode()" />


        <br>
        <input type="button" value="提交" id="btn" class="btn btn-primary  ">
    </form>
    <br>
    <br><br><br><br>

    <div class="center">
        <span id="ans"></span>
    </div>



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
                            alert("接收到消息");
                            var serverAns = ans.serverAns;
                            if (serverAns ===true) {
                                alert("欢迎来到爪哇部落");
                                $("#ans").text("登录成功").css({color: "green", position: "absolute"});
                                refreshCode();
                                window.setTimeout("window.location.href='/findByPage.do?currentPage=1&rows=5'", 1000);

                            } else {
                                refreshCode();
                                $("#ans").text("您输入的用户名或者密码不正确").css({color: "red", position: "absolute"});
                            }
                        }
                    });
                }
            })
    });







</script>

<script>
    function refreshCode(){
        //1.获取验证码图片对象
        var vcode = document.getElementById("vcode");

        //2.设置其src属性，加时间戳
        vcode.src = "/checkCodeServlet?time="+new Date().getTime();
    }



</script>
<script>
    $(document).ready(
        function () {
            refreshCode();



        }
    )
</script>