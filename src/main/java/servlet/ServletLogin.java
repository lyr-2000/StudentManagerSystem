package servlet;


import bean.Member;
import dao.daoImpl.MemberDao;
import dao.LoginPlugin;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2019/8/5.
 *
 * 处理登录事务
 */
@WebServlet(name = "ServletLogin",urlPatterns = "/login.do")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userId");
        String password = request.getParameter("password");
        JSONObject jsonObject = null;
        System.out.println(id);
        System.out.println(password);
        // System.out.println("在");

        /*
        *
        * 对比验证码看是否正确
        * 二次验证
        *
        * */
        String code = request.getParameter("code").toLowerCase();//全部转化为小写字母验证
        String ans = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        if(ans.toLowerCase().equals(code)) {

            LoginPlugin QueryHandler = MemberDao.getInstance();
            List<Member> list = QueryHandler.QueryById(id,password,true);
            System.err.println(list);
            /*
            *
            * 如果查询到的 集合不为空，说明 有这个人，和 jsp 返回 true
            *
            * */
            if(!list.isEmpty()) {
                System.out.println("ddddddddddddddd");
                request.getSession().setAttribute("loginUser",list.get(0));

                // String  userName = list.get(0).getName();
                // Cookie cookie = new Cookie("userName",userName);
                // response.addCookie(cookie);
                // System.out.println("1");
                jsonObject = new JSONObject("{serverAns:true}");
                System.out.println("1");
            }else{
                jsonObject = new JSONObject("{serverAns:false}");
                System.out.println("1*************");
            }


        }else{
            jsonObject = new JSONObject("{serverAns:false}");
            System.out.println("2---------");
        }


        System.out.println("--------------------------------------------");
        System.err.println("在的");



        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
        System.out.println("发送成功");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
