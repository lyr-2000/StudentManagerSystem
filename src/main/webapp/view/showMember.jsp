<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@ page language="java" pageEncoding="UTF-8" import="java.util.*" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/5
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-1.12.4.min.js"></script>
    <style>
        td{
            width:200px;
            text-align: center;
        }
    </style>
</head>
<body>
<script>

</script>
    <table>
        <tr id="col-title">
            <th>学号</th>
            <th>姓名</th>
            <th>系院</th>
            <th>类别</th>
            <th>加入时间</th>
            <th>电话</th>
            <th>&nbsp;</th>
            <tj>&nbsp;</tj>
            <th>&nbsp;</th>


        </tr>

        <c:forEach var="i" items="${list}">
            <tr>
                <td>${i.getId()}</td>
                <td>${i.getName()}</td>
                <td>${i.getSubject()}</td>
                <td class="work">${i.getWork()}</td>
                <td>${i.getJoinTime()}</td>
                <td>${i.getPhone()}</td>
                <td><a href="/show.do?type=detail&id=${i.getId()}">查看详情</a></td>
                <td><a href="/alter.do?id=${i.getId()}&flag=0">修改</a></td>
                <td>
                    <a href="/view/addMember.html">增加</a>
                </td>
                <td>
                    <a href="javascript:void(0)" onclick="del(${i.getId()},this)">删除</a>

                </td>



            </tr>
        </c:forEach>
    </table>

    <button id="get">
        获取详细信息
    </button>
</body>
</html>

<script>
    $(document).ready(function () {
        $("#get").css({
            color:"blue"
        });

        /*给按钮绑定事件*/
        $('#get').click(
            function () {
                window.location.href="/show.do?type=show";
            }
        );

        var arr =[
            "算法组",
            "前端组",
            "后端组",
            "爬虫组",
            "项目经理组"
        ];
        console.log(arr['0'])
        console.log($('td').html());
        
        /*
            该操作是对数据进行解码，把组员的组织信息输出，因为在数据库里面存的是数字，通过
            arr对应的值显示出来

        */
        $('.work').each(
            function () {
                $(this).html(arr[$(this).html()]);
            }
        )
        console.log(str);




    })
</script>
<%--删除的脚步编写--%>
<script>
    function del(id,obj){
        if(!confirm("是否删除")) {
            return false;
        }else{

           $.ajax({

               type: "post",
               url: "del.do",
               data: {
                   'type':"del",
                   "id" : id,

               },
               dataType: "json",
               success: function (ans) {
                   var s = ans.delAns;
                   if(s) {
                       alert("删除成功");
                       del_test(obj);
                   }else {
                       alert("删除失败");
                   }

               }




           })

        }
    }
    /*测试是否可以进行删除操作*/
    function del_test(obj){
        var i = $(obj);
       i.parent().parent().css({display:"none"});

    }
</script>
