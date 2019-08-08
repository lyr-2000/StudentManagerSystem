<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/8
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="../js/jquery-1.12.4.min.js"></script>
</head>
<body>
    <header>
        <h2>关于他（她）</h2>
    </header>
    <section>
        <p>${member.getSex()==0 ? "女":"男"}</p>
        <p>
            ta的名字：${member.getName()}
        </p>
        <p>
            TA来自 ${member.getSubject()}
        </p>
        <em>ta的生日是 ${member.getBirthday()}</em>
        <p>ta的联系方式：${member.getPhone()}</p>
        <em id="work"></em>
    </section>
    <footer>
        <hgroup>
            <h2>ta的个性签名</h2>
        </hgroup>
        <article>
            ${member.getSignature()}

        </article>
    </footer>

</body>
</html>

<script>
    $(document).ready(function () {
        var work = ${member.getWork()};



        var arr =[
            "算法组",
            "前端组",
            "后端组",
            "爬虫组",
            "项目经理组"
        ];
        $("#work").html("组织："+arr[work]);




    })
</script>
