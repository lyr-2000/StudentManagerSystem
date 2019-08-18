package servlet;

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



        String[] idArray= request.getParameterValues("uids");

        MemberDao dao = MemberDao.getInstance();
        dao.deleteSelected(idArray);

        Map map=new HashMap();
        map.put("ans",1);

        JSONObject msg = JSONObject.fromObject(map);
        response.getWriter().println(msg);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
