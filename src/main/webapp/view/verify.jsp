<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/18
  Time: 16:26
  To change this template use File | Settings | File Templates.

  管理员审核注册人员的页面

--%>
<%@ page import="bean.PageForMember" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员审核注册人员</title>
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

<br>
<hgroup>
    <h1>
        注册申请
    </h1>
</hgroup>
<br>

<div style="float:left;margin-left:62px">
    <form class="navbar-form navbar-left" role="search"  id = "form-nav" method="post" action="/verify.do?search=search&tableName=loginmember">
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
    <a href="/findByPage.do" class="btn btn-primary">返回大厅</a>

    <a href="javascript:void(0);" class="btn btn-primary" id="del"  onclick="confirm_()">删除选中</a>
</div>

<br><br>
    <form action="" id="form"  action="/dels.do"  method="post"   >
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

            </tr>




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

                    <td><a href="/show.do?type=detail&id=${i.getId()}&tableName=loginmember" class="btn btn-default btn-sm">查看详情</a></td>
                    <td><a href="/verify.do?id=${i.getId()}&flag=0&pass=pass" class="btn btn-warning btn-sm">通过</a></td>
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

                <a  aria-label="Previous"  href="/verify.do?currentPage=${pm.getCurrentPage()==1? 1:pm.getCurrentPage()-1}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>




            <c:forEach begin="1" end="${pm.getTotalPage()}"  var="i">
                <c:if test="${pm.getCurrentPage() !=i}">
                    <li>
                        <a href="/verify.do?currentPage=${i}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">${i}</a>
                    </li>
                </c:if>

                <c:if test="${pm.getCurrentPage() ==i}">
                    <li class="active">
                        <a href="/verify.do?currentPage=${i}&rows=5&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">${i}</a>
                    </li>
                </c:if>

            </c:forEach>


            <li>


                <a  aria-label="Next"  href="/verify.do?currentPage=${pm.getCurrentPage()<pm.getTotalPage() ?pm.getCurrentPage()+1:pm.getCurrentPage()}&rows=5&flag=${pm==null? 0: pm.getCurrentPage()<=pm.getTotalPage() ? 0:1}&first=0&search=${uName==null&&uId==null? "":"search"}&uName=${uName}&uId=${uId}">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>

            <%--</c:if>--%>


        </ul>

        <div><strong>共${pm.getTotalCount()}条记录，${pm.getTotalPage()}页</strong></div>
    </nav>


    <hidden style="display:none">
        <span id = "hidden-uName">${uName}</span>
        <span id="hidden-uId">${uId}</span>
        <span id="admin"></span>
    </hidden>


</body>
</html>



<script>
    $(document).ready(function () {

        setTimeout(function () {
            var b = ${list!=null};
            if(b) {
                //如果是显示 所有 人的消息的话，就不需要分页栏了
                $("#show-nav").css({display:"none"});
            }

            var str=location.href; //取得整个地址栏
            var num=str.indexOf("admin=false");
            //如果显示 admin=false ,说明服务器显示 非管理员用户操作 数据库，提示用户并且 阻止
            if(num>0) {
                alert("抱歉，您没有管理员权限");
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
                     "tableName":"loginmember"

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
                data:$("#form").serialize()+"&tableName=loginmember",
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





