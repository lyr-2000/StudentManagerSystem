package servlet;

import bean.Member;
import bean.PageForMember;
import dao.daoImpl.MemberDao;

import util.QueryTool;
import util.serviceUtil.QueryForPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



/**
 * Created by ASUS on 2019/8/10.
 *
 * 普通的分页查询 或者条件 查询的事务
 *
 *
 */
@WebServlet(name = "Servlet4",urlPatterns = "/findByPage.do")
public class FindByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");
        System.err.println(search+"          dddddddddddddddddddddddd");


        // search 是 空，说明不是 条件查询 的 请求
        if(search==null) {
            search = "";
        }
        //如果是条件查询，就执行括号中的方法，并且返回，否则跳过括号执行普通的分页方法
        boolean isSearch = !search.equals("");
        System.err.println("isSearch:"+ isSearch);


            /*
            *
            * 如果是条件查询，就调用相应的函数并且返回
            *
            *
            * */

        if(isSearch) {
            System.out.println("进入条件查询方法");
            QueryByCondition(request,response);


            return;
        }







        //_______*********************************************************


        /*
        *
        *
        * 不是 条件 查询 就默认执行 最普通的 分页 查询
        *
        * */


        //当前页码数
        String currentPage = request.getParameter("currentPage");

        System.out.println(currentPage);
        //每页要显示多少条数
        String rows = request.getParameter("rows");

        if(currentPage==null||rows==null||currentPage.equals("-1")||currentPage.equals("")) {
            currentPage = "1";
            rows = "5";
        }
        String flag = request.getParameter("flag");
        if(flag==null) {
            flag="";
        }



        MemberDao serviceDao = MemberDao.getInstance();
        PageForMember pm;


        if(flag.equals("1")) {
            //如何页数超了，会发来一个标记flag，如果flag是1 说明超了，要减回去
            int currentPage1 = Integer.parseInt(currentPage)-1;


             String currentPage2 = String.valueOf(currentPage1);


             pm = serviceDao.QueryForPage(currentPage2,rows);

            pm.setCurrentPage(currentPage1);
        }else{
            //按页数来查询
            pm = serviceDao.QueryForPage(currentPage,rows);
            int currentPage2 = Integer.parseInt(currentPage);
            pm.setCurrentPage(currentPage2);
        }




        String uName = request.getParameter("uName");
        String uId = request.getParameter("uId");
        request.setAttribute("uName",uName);
        request.setAttribute("uId",uId);
        request.setAttribute("pm",pm);

        /*
        *
        * 有 过滤器 拦截 到 非管理员用户操作数据库， 这时也要重定向到 页面，并且发送消息
        *
        * */
        String isAdmin = request.getParameter("admin");
        if(isAdmin!=null&&isAdmin.equals("false")) {
            request.setAttribute("isAdmin",false);
        }



        request.getRequestDispatcher("/view/showMember.jsp").forward(request,response);
        System.out.println("发送成功ddd");




    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("_____________________________________");
        doPost(request,response);

    }

    /**
     *
     * 条件查询，把 用户查询的姓名 和 学号 查询并发送到jsp页面， 支持 逗号 和空格 分隔 查询多组数据
     *
     *
     *
     * */
    private void QueryByCondition(HttpServletRequest request,HttpServletResponse response) {

        String uName = request.getParameter("uName");
        String uId = request.getParameter("uId");
        String[] arr = new String[]{uName,uId};

        Map<String,String[]> map = new TreeMap(Collections.reverseOrder());
        for(int i=0;i<arr.length;i++) {
            String [] item1 = arr[i].split(",");
            String [] item2 = arr[i].split("，");
            String [] item3 = arr[i].split(" ");
            Set<String> a = insertionUtil(item1,item2,item3);

            if(i==0) {
                //放入名字
                String[] p1 = a.toArray(new String[]{});
                map.put("`name`",p1);
                System.out.println(p1+"+++++++++++++++++++++++++++++++");

            }else{
                String[] p2 = a.toArray(new String[]{});
                map.put("`id`",p2);
                System.out.println(p2+"dddddddddddddddddddddddddddddddddddddd");
            }
        }
        System.err.println(map);




        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");


        if(currentPage==null||rows==null||currentPage.equals("-1")||currentPage.equals("")) {
            currentPage = "1";
            rows = "5";
        }
        //下一页的操作
        String flag = request.getParameter("flag");
        if(flag==null) {
            //如果flag不是 1 说明页数没有超，可以查询
            flag="";
        }

        QueryTool tool = QueryForPage.getInstance();

        PageForMember pm = new PageForMember();

        int row  = Integer.parseInt(rows);
        if(flag.equals("1")) {
            //如何页数超了，会发来一个标记flag，如果flag是1 说明超了，要减回去
            int currentPage1 = Integer.parseInt(currentPage);



            int begin = (currentPage1-1)*row;

            List<Member> mList = tool.findByPage(begin,row,map);
            pm.setList(mList);

            pm.setCurrentPage(currentPage1);
            pm.setRows(row);
        }else{
            /**
             * 预留的代码块，预备其他状况
             *
             * */

            int t;
            int currentPage2 = Integer.parseInt(currentPage);

            int begin = (currentPage2-1)*row;
            List<Member>mList = tool.findByPage(begin,row,map);
            pm.setList(mList);

            pm.setCurrentPage(currentPage2);
            pm.setRows(row);

        }
        pm.setQueryStyle((byte) 1);

        int totalCount = QueryForPage.getInstance().count(map);
        pm.setTotalPage(totalCount%row==0?totalCount/row:totalCount/row+1);
        pm.setTotalCount(totalCount);





        request.setAttribute("pm",pm);
        //记录分页的信息
        request.setAttribute("uName",uName);
        request.setAttribute("uId",uId);

        try {
            request.getRequestDispatcher("/view/showMember.jsp").forward(request,response);
            System.out.println("发送成功");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            System.err.println("条件查询函数出现异常，出来处理一下");
        }




        return;
    }

    /**
     *
     * 工具函数 ，传入查询 的名字，学号的值 的数组，取交集并且返回该集合
     *
     *
     * */

    public Set insertionUtil(String[]... arr) {
        Set<String> insertSet = new TreeSet<>();

        for(int i=0;i<arr.length;i++) {
            List<String> p = Arrays.asList(arr[i]);
            insertSet.addAll(p);
            System.out.println("insertSet **********    "+insertSet);
        }

        return insertSet;
    }


}
