package servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        if("admin".equals(id)) {
            System.out.println("d对的对的");
            jsonObject = new JSONObject("{serverAns:true}");
        }else{
            jsonObject = new JSONObject("{serverAns:false}");
        }
        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
