package servlet;

import bean.Member;
import com.google.gson.Gson;
import dao.Dao;
import dao.daoImpl.MemberDao;
import sun.misc.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ASUS on 2019/8/6.
 *
 * 用于展示成员的数据
 *
 */
@WebServlet(name = "show",urlPatterns = {"/show.do"})
public class ShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("成功触发");

        String type = request.getParameter("type");
        if("show".equals(type)) {
            try{
                showStudent(request,response);
            }catch (Exception e)
            {
                System.err.println("展示学生数据时出现问题了，处理一下*******************");
                e.printStackTrace();
            }
        }else if("detail".equals(type)) {

            String id = request.getParameter("id");
            Dao dao = MemberDao.getInstance();
            Member m = (Member)dao.QueryById2(id);

            request.setAttribute("member",m);
            request.getRequestDispatcher("/view/detail.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }



    private void showStudent(HttpServletRequest request, HttpServletResponse response)
    {
        Dao memberDao = MemberDao.getInstance();

        List<Member> list = memberDao.QueryAll();
        request.setAttribute("list",list);
        request.setAttribute("a",1);



        try
        {
            request.getRequestDispatcher("./view/showMember.jsp").forward(request,response);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("你转发出现了异常，在发送成员集合的地方");
        }
    }


}
