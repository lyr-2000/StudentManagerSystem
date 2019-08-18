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
 *用来处理数据库成员的添加操作，一个servlet
 *
 */
@WebServlet(name = "addMemberHandler",urlPatterns = "/add.do")
public class AddMemberHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String id= request.getParameter("id");
      String name = request.getParameter("name");
      String password= request.getParameter("password");
      String sex= request.getParameter("sex");
      String joinTime= request.getParameter("joinTime");
      String work= request.getParameter("work");
      String birthday= request.getParameter("birthday");
      String subject= request.getParameter("subject");
      String phone= request.getParameter("phone");
      String signature= request.getParameter("signature");
      //过滤用户信息，用户可能修改前台js脚本提交，后台再过滤一遍
      if(filter(id,name,password,phone)==false) {

          JSONObject jsonObject = new JSONObject("{addAns:false,errNum:true}");
          response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
          return;
      }

      /*
      *
      * 把添加成员的表单信息取出来后，创建member对象，通过Dao层
      * 存入数据库
      *
      * */

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
      System.out.println("报告，我已经收到消息了");




      /*
      * 获得 dao对象
      * */
        Dao dao = MemberDao.getInstance();
        JSONObject jsonObject = null;
      try{
        boolean b = dao.add(member);
        if(b) {
            /*
            * 如果数据库显示提交成功，返回true,否则，数据库可能已经有这个人了，返回false
            *
            * */
            jsonObject = new JSONObject("{addAns:true}");
        }else{
            jsonObject = new JSONObject("{addAns:false}");
        }


      }catch (Exception e) {
        e.printStackTrace();
        System.err.println("额，在 dao操作数据库添加操作时出现问题了，处理一下");
        jsonObject = new JSONObject("{addAns:false,infos:false}");
      }finally {
          response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));
      }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /*
    *
    * 网页传来异步请求，检验必填项是否有填写，如果没填写，返回false
    *
    * */
    /**
     *用来检验常用的属性是否齐全，如果用户信息不全，返回false
     *
     * @param  arr 不定长参数
     *
     * @return boolean
    *
    * */
    public static boolean filter(String ... arr) {
        for(String i:arr) {
            if(i==null||i.equals("")||i.equals("null")) {
                return false;
            }
        }
        return true;
    }



}
