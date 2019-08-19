<%@ page import="bean.PageForMember" %>
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
    <title>大厅</title>
    <script src="../js/jquery-1.12.4.min.js"></script>
    <style>
        td{
            width:200px;
            text-align: center;
        }


    </style>


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
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <%--<script src="/js/jquery-1.12.4.min.js"></script>--%>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="/js/jquery-2.1.0.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style type="text/css">

        td, th {
            text-align: center;
        }
        table{
            width:89% !important;
            margin:auto !important;
        }
        .last{
            width:120px !important;
        }
        .admin{
            float: right;
            height: 80px;
        }
        header#header{
            overflow:hidden !important;
            height: 18px;

        }
        #get{
            margin-left: 18px;
        }
        hgroup{
            padding:65px;
        }

        .relative{
            position: relative;

        }
        .footer{
            text-align: center;
            position: absolute;

            bottom: -40px;
            left: 0;
            right: 0
        }
    </style>


    <script>
        <%--全选功能--%>
         count=0;
        function show1(){

            if(count==0) {
                count++;
            }else{
                count--;
            }

            if(count) {
                /*全部选中*/
                allSelect();

            }else{
                noSelect();
            }





        }



        function allSelect() {
            var oBox = document.getElementById("boxt");

            var aInput = document.getElementsByClassName("input");
            for(var i = 0; i < aInput.length; i++) {
                aInput[i].checked = true;
            }

        }
        function noSelect() {
            var oBox = document.getElementById("boxt");

            var aInput = document.getElementsByClassName("input");
            for(var i = 0; i < aInput.length; i++) {
                aInput[i].checked = false;
            }
        }



    </script>



</head>
<body>
<script>

</script>

    <header id="header">
        <article class="admin">
            您当前身份为 ${loginUser.getAdmin().equals("管理员")? "管理员":"普通成员"}
        </article>
    </header>

    <br><br>

    <div style="float:left;margin-left:62px">
        <form class="navbar-form navbar-left" role="search"  id = "form-nav" method="post" action="/findByPage.do?search=search">
            <div class="form-group">
                <label for="search1">名字</label>
                <input type="text" class="form-control" placeholder="用户名字" id="search1"  name = "uName"  value="${uName}">
            </div>


            <div class="form-group">
                <label for="search2">&nbsp;&nbsp;学号</label>
                <input type="text" class="form-control" placeholder="用户学号" id="search2"    name="uId" value="${uId}">
            </div>



            <button type="submit" class="btn btn-default" id="searchForPage" >搜索</button>



        </form>
    </div>


    <div style="float:right;margin-right:80px">
        <a  href="/view/addMember.html" class="btn btn-primary">添加成员</a>
        <a href="javascript:void(0);" class="btn btn-danger" id="del"  onclick="confirm_()">删除选中</a>
    </div>

    <br><br>
    <form action="" id="form"  action="/dels.do"  method="post"   class="relative" >
        <table  border="1" class="table table-bordered table-hover">
            <tr id="col-title" class="success">
                <th><input type="checkbox"    id="boxt"  onclick="show1()"></th>
                <th>学号</th>
                <th>姓名</th>
                <th>系院</th>
                <th>类别</th>
                <th>加入时间</th>
                <th>电话</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>


            </tr>


            <%-- 查询所有信息 --%>
            <c:forEach var="i" items="${list}">
                <tr >
                    <td><input type="checkbox"     name="uids"  value="${i.getId()}"        class="input"  ></td>
                    <td>${i.getId()}</td>
                    <td>${i.getName()}</td>
                    <td>${i.getSubject()}</td>
                    <td class="work">${i.getWork()}</td>
                    <td>${i.getJoinTime()}</td>
                    <td>${i.getPhone()}</td>
                    <td><a href="/show.do?type=detail&id=${i.getId()}" class="btn btn-default btn-sm">查看详情</a></td>

                    <td><a href="javascript:void(0)" class="btn btn-block btn-sm" onclick="sendUrl('/alter.do?id=${i.getId()}&flag=0')" >修改</a></td>
                    <td>
                        <a href="javascript:void(0)" class="btn btn-warning" onclick="sendUrl('/view/addMember.html')" >增加</a>
                    </td>
                    <td class="last">
                        <a href="javascript:void(0)" onclick="del(${i.getId()},this)" class="btn btn-danger">删除</a>

                    </td>



                </tr>
            </c:forEach>
            <div class="footer">
                <strong>${list.size()} ${list==null? "":"条记录"}</strong>
            </div>



            <%--分页查询--%>
            <c:forEach var="i" items="${pm.getList()}">
                <tr>
                    <td><input type="checkbox"     name="uids"  value="${i.getId()}"        class="input"  ></td>
                    <td>${i.getId()}</td>
                    <td>${i.getName()}</td>
                    <td>${i.getSubject()}</td>
                    <td class="work">${i.getWork()}</td>
                    <td>${i.getJoinTime()}</td>
                    <td>${i.getPhone()}</td>
                    <td><a href="/show.do?type=detail&id=${i.getId()}" class="btn btn-default btn-sm">查看详情</a></td>
                    <td><a href="javascript:void(0)" class="btn btn-default btn-sm"   onclick="sendUrl('/alter.do?id=${i.getId()}&flag=0')">修改</a></td>
                    <td>
                        <a href="javascript:void(0)" class="btn btn-warning btn-sm"  onclick="sendUrl('/view/addMember.html')">增加</a>
                    </td>
                    <td class="last">
                        <a href="javascript:void(0)" onclick="del(${i.getId()},this)" class="btn btn-danger btn-sm">删除</a>

                    </td>



                </tr>
            </c:forEach>
        </table>

    </form>









    <nav aria-label="Page navigation" style="margin:auto;width: 17%" id="show-nav" >
        <ul class="pagination">



                <li>

                    <a  aria-label="Previous"  href="/findByPage.do?currentPage=${pm.getCurrentPage()==1? 1:pm.getCurrentPage()-1}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>




            <c:forEach begin="1" end="${pm.getTotalPage()}"  var="i">
                <c:if test="${pm.getCurrentPage() !=i}">
                    <li>
                        <a href="/findByPage.do?currentPage=${i}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">${i}</a>
                    </li>
                </c:if>

                <c:if test="${pm.getCurrentPage() ==i}">
                    <li class="active">
                        <a href="/findByPage.do?currentPage=${i}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">${i}</a>
                    </li>
                </c:if>

            </c:forEach>


            <li>


                <a  aria-label="Next"  href="/findByPage.do?currentPage=${pm.getCurrentPage()<pm.getTotalPage() ?pm.getCurrentPage()+1:pm.getCurrentPage()}&rows=5&flag=${pm==null? 0: pm.getCurrentPage()<=pm.getTotalPage() ? 0:1}&first=0&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>

            <%--</c:if>--%>


        </ul>

        <div><strong>共${pm.getTotalCount()}条记录，${pm.getTotalPage()}页</strong></div>
    </nav>

    <hgroup>
        <button id="get"  class="btn btn-default btn-sm"  >
            获取所有信息
        </button>
        <button id="get2"  class="btn btn-default btn-sm"  >
            获取分页信息
        </button>
        <a href="javascript:void(0)"   class="btn btn-default btn-sm" onclick="sendUrl('/verify.do')">
            审核注册
        </a>
    </hgroup>


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>




    <hidden>
        <span id = "hidden-uName">${uName}</span>
        <span id="hidden-uId">${uId}</span>
        <span id="admin">${isAdmin}</span>
    </hidden>
</body>
</html>

<script>

    function attention() {
        var str=location.href; //取得整个地址栏
        var num=str.indexOf("admin=false");
        //如果显示 admin=false ,说明服务器显示 非管理员用户操作 数据库，提示用户并且 阻止
        if(num>0) {
            alert("您没有管理员权限,只能查看其他成员的信息哦");
        }
    }

    $(document).ready(function () {

        setTimeout(function () {
            var b = ${list!=null};
            if(b) {
                //如果是显示 所有 人的消息的话，就不需要分页栏了
                $("#show-nav").css({display:"none"});
            }


        },100);

        setTimeout(function () {
            var uName = $("#hidden-uName").html();
            var uId = $("#hidden-uId").html();
//            alert(uName)
            console.log(uName)
            $("#search1").val(uName);
            $("#search2").val(uId);




        },20);

        var obj = document.getElementsByTagName("checkbox");



        $("#get").css({
            color:"blue"
        });

        /*给按钮绑定事件*/
        $('#get').click(
            function () {
                window.location.href="/show.do?type=show";



            }
        );

        $("#get2").click(function () {
            window.location.href="/findByPage.do?currentPage=1&rows=5";
        });




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
            arr对应的值显示出来,servlet写好了才发现问题，不想再改了，就用这个方法

        */
        $('.work').each(
            function () {
                $(this).html(arr[$(this).html()]);
            }
        )









    })
</script>
<%--删除的脚步编写--%>
<script>
    function del(id,obj){
        if(!confirm("是否删除")) {
            return false;
        }else{

            $.ajaxSetup({



                statusCode: {
                    403:function () {
                        alert("您不是管理员，拒绝服务");
                    },

                    404: function() {
                        alert('数据获取/输入失败，没有此服务。404');
                    },
                    504: function() {
                        alert('数据获取/输入失败，服务器没有响应。504');
                    },
                    500: function() {
                        alert('服务器有误。500');
                    }
                }
            });

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




           });



        }
    }




    /*测试是否可以进行删除操作*/
    function del_test(obj){
        var i = $(obj);
       i.parent().parent().css({display:"none"});

    }
</script>


<script>

    function sendUrl(url) {
        //提醒用户是否 为管理员，是否权限越界了
        attention();
        window.location.href=url;

    }
</script>






<%--删除选中成员--%>


<script>
    function confirm_() {
        var b = confirm("是否删除选中?");
        if(b) {
            /* 防止出现空指针异常还有判断是否有被选中的 */
            var arrs = document.getElementsByClassName("input");
            var flag = false;
            for(var i=0;i<arrs.length;i++) {
                if(arrs[i].checked) {
                    flag = true;

                    break;
                }
            }

            if(flag) {
                /*使用 ajax方式提交 */
                removes(arrs);


            }else{
                alert("确定选中了？");
            }

        }
    }

    function removes(arrs) {


        $.ajax({

            type:"post",
            url:"/dels.do",
            data:$("#form").serialize(),
            dataType:"json",
            success:function (msg) {
                var b = msg.ans;
                if(b==1) {
                    alert("删除成功");
                    invisible(arrs);

                }else {
                    alert("删除失败");
                }
            }

        }
        );




    }

    function invisible(arrs) {
        for(var i=0;i<arrs.length;i++) {
            if(arrs[i].checked) {
                var p = $(arrs[i]);
                p.parent().parent().css({display:"none"});


            }
        }
    }


</script>

<script>

</script>















