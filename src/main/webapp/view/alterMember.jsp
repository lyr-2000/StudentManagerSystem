<%@ page import="bean.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/7
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改成员</title>

    <script src="../js/jquery-1.12.4.min.js"></script>
    <style>
        #signature{
            width:1000px;
            height:700px;
        }
        form{
            width:1200px;
            margin:auto;
            display:block;
        }
        button{
            background-color:blue;
            color: white;
            border:1px solid blue;
            position: absolute;
            top:79px;
            right: 84px;
        }
    </style>

</head>
<body>

<form action="/add.do" id="msg" method="post">
    id: <input type="text" placeholder="输入学生的学号"  required  id="num"  value="${member.getId()}"       name="id">               <br><br>
    password: <input type="text" placeholder="请输入注册的密码"  required    value="${member.getPassword()}"                   name="password">         <br>  <br>
    名字： <input type="text"                           value="${member.getName()}"             id="name"  name="name">
    性别:
    <select  id="sex"                                value="${member.getSex()}"                  name="sex">              <br><br><br>
        <option value="0">女</option>
        <option value="1">男</option>
    </select>


    加入时间: <input type="date"  id="joinTime"     required                            name="joinTime">         <br><br><br>
    加入组织：
    <select name="work"  id="select"                 >                                                           <br><br><br>
        <option value="9">请选择</option>
        <option value="0">算法组</option>
        <option value="1">前端组</option>
        <option value="2">后端组</option>
        <option value="4">爬虫组</option>
        <option value="3">项目经理组</option>

    </select>
    生日：<input type="date"        id="birthday"                                  name="birthday">  <br><br><br>
    院系：<input type="text"         required        value="${member.getSubject()}"                               name="subject"><br><br><br>
    电话号码： <input type="text"     required      id="phone"       value="${member.getPhone()}"                        name="phone"><br><br><br>

    <br><br><br>
    他的个性签名： <br>
    <textarea  id="signature"                    name="signature">
        ${member.getSignature()}

    </textarea>






</form>
<button id="submit">
    一键提交
</button>


</body>


<script>

    $(document).ready(

        function(){
            $("#submit").click(
                function () {
                    if(checknum())
                    {
                        alert("不准提交");
                        return;
                    }
                    if(handler())
                    {
                        alert("电话号码不合规矩");
                        return;
                    }



                    $.ajax({

                        type:"post",
                        url:"/alter.do",
                        data:$("#msg").serialize()+"&flag=1",
                        dataType:"json",
                        success:function (ans) {
                            res = ans.update;

                            if(res==0) {
                                alert("信息缺少了");
                            }
                            if(res==1) {
                                alert("修改成功");
                            }


                        }


                    })
                }
            )



        }



    );



</script>

<script>

    function checknum(){
        var obj = $("#num");


        if(!/^[0-9]*$/.test(obj.val()))
        {
            alert("id的话 请输入数字! ");
            alert("别指望修改脚步，后台也不能过");
            return true;
        }
        return false;
    }

    function handler() {
        b = $("#userId").val()==""||$("#password").val()==""||$("name").val()==""||$("#joinTime").val()==""||$('#phone').val()=="";
        if(b) {
            alert("基础信息没填完，不让过");
            return false;
        }
        b = checkPhone();
        return b;
    }

    function checkPhone(){
        var phone = document.getElementById('phone').value;
        if(!(/^1[34578]\d{9}$/.test(phone))){
            alert("手机号码有误，请重填");
            return true;
        }
        return false;
    }


</script>
    <hidden>
        <%--隐藏的信息，主要用给 js 读取--%>
        <span id="time1" style="color:white">${member.getJoinTime()}</span>
        <span id="time2" style="color:white">${member.getBirthday()}</span>

    </hidden>


</html>

<script>
    $(document).ready(
        function () {

            if(${member.getJoinTime()!=null}) {
                var str1 =$("#time1").html().toString();
                console.log(str1);


                $("#joinTime").attr("value",str1);
            }

            if(${member.getBirthday()!=null}) {


                var str1 = $("#time2").html().toString();
                console.log(str1);

                $("#birthday").attr("value",str1);
            }
            var i = ${member.getWork()};
            $("#select").find("option[value = '"+i+"']").attr("selected",true);

            var sex = ${member.getSex()};

            $("#sex").find("option[value = '"+i+"']").attr("selected",true);
        }


    )



</script>

