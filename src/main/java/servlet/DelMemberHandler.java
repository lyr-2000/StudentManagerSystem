package servlet;

import bean.Member;
import dao.Dao;
import dao.daoImpl.MemberDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2019/8/6.
 *
 * 用来处理删除成员的事务（删除单个）
 *
 *
 */
@WebServlet(name = "Servlet",urlPatterns = "/del.do")
public class DelMemberHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id= request.getParameter("id");
        System.out.println(id);
        MemberDao dao = MemberDao.getInstance();


        JSONObject jsonObject = null;


        String tableName = request.getParameter("tableName");


        //是否有指定 table 数据库表的名字，如果有，在下面判断，并调用对应的方法
        boolean b = tableName!=null&&tableName.equals("loginmember");
        try
        {
            //如果是管理员审核 注册申请，就调用 deleteChoose方法，否则就是删除 member表里的方法，调用del 方法
            if(b) {
                dao.deleteChoose(new String[]{id},tableName);
            }else{
                Member m = new Member();
                m.setId(id);
                dao.del(m);
            }
            jsonObject = new JSONObject("{delAns:true}");
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject = new JSONObject("{delAns:false}");
        }finally {
            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }




}
