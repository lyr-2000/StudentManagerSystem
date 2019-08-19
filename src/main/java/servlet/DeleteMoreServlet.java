package servlet;

import bean.Member;
import dao.daoImpl.MemberDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2019/8/9.
 *
 * 处理管理员批量删除 的业务
 *
 */
@WebServlet(name = "Servlet3",urlPatterns = "/dels.do")
public class DeleteMoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.err.println("删除消息收到");
        String[] idArray= request.getParameterValues("uids");
        String tableName = request.getParameter("tableName");
        if(tableName!=null&&tableName.equals("loginmember")) {
            MemberDao dao = MemberDao.getInstance();
            dao.deleteChoose(idArray,tableName);
            System.err.println("tableName   ->  "+tableName);
        }else{
            MemberDao dao = MemberDao.getInstance();
            dao.deleteSelected(idArray);
            System.out.println("删除了 member 表");
        }
        System.out.println("在在");



        Map map=new HashMap();
        map.put("ans",1);

        JSONObject msg = JSONObject.fromObject(map);
        response.getWriter().println(msg);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
