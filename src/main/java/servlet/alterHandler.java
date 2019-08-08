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
 * Created by ASUS on 2019/8/7.
 */
@WebServlet(name = "Servlet2",urlPatterns = "/alter.do")
public class alterHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String i = request.getParameter("flag");
        if(i.equals("0")) {
            getInfo(response,request);
        }else if(i.equals("1")) {




            updateInfo(request,response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private void updateInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password= request.getParameter("password");
        String sex= request.getParameter("sex");
        String joinTime= request.getParameter("joinTime");
        String work= request.getParameter("work");
        String birthday= request.getParameter("birthday");
        String subject= request.getParameter("subject");
        String phone= request.getParameter("phone");
        String signature= request.getParameter("signature");
        if(addMemberHandler.filter(id,name,password,phone)==false) {

            JSONObject jsonObject = new JSONObject("{update:0}");//发送0表示更新失败
            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
            return;
        }
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setBirthday(birthday);
        member.setJoinTime(joinTime);
        member.setPhone(phone);
        member.setSex(sex);
        member.setWork(work);
        member.setSubject(subject);
        member.setSignature(signature);
        member.setPassword(password);
        System.out.println("报告，我已经收到消息了11");



        Dao dao = MemberDao.getInstance();
        dao.set(member);
        JSONObject jsonObject = new JSONObject("{update:1}");//发送1，表示更新成功
        response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
        System.out.println("dao层没毛病");
        return;


    }

    private void getInfo(HttpServletResponse response,HttpServletRequest request) {
        Dao dao = MemberDao.getInstance();
        String id = request.getParameter("id");
        Object unknow = dao.QueryById2(id);



        Member m = (Member)unknow;


        System.out.println(m);
        System.out.println("__________________________________");
        request.setAttribute("member",m);
        try {
            request.getRequestDispatcher("/view/alterMember.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
