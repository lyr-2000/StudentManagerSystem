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
 * Created by ASUS on 2019/8/18.
 *
 * 处理用户 注册 操作
 *
 * 在注册页面进行注册 的操作，这里 注册会在数据库另一个表（loginMember）里面写入数据，有管理员统同意再转正
 *
 */
@WebServlet(name = "register",urlPatterns = "/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        * 查看验证码是否正确
        * */
        String code = request.getParameter("code").toLowerCase().trim();

        //获取验证码
        String ans = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        System.out.println("ans = "+ans +"   code = "+code);
        String str = ans.toLowerCase();

        //对比用户输入的验证码是否正确
        if(!code.equals(str)) {
            System.err.println(code.equals(str));
            //返回数据
            JSONObject jsonObject = new JSONObject("{code:false}");

            response.getOutputStream().write(jsonObject.toString().getBytes("utf-8"));

            return;//如果验证码不对，直接返回
        }



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
        String admin = request.getParameter("admin").equals("0") ? "普通成员":"管理员";
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

        member.setAdmin(admin);




      /*
      * 获得 dao对象
      * */
        MemberDao dao = MemberDao.getInstance();
        JSONObject jsonObject = null;
        try{
             /*
            * 如果数据库显示有这个人了，返回true,否则，数据库没有这个人了，再进行注册提交，返回false
            *
            * */
            boolean b = dao.register(member);
            if(!b) {
                //数据库显示有这个人了，给页面返回 false，表示注册失败
                System.out.println("注册失败，dddd");
                jsonObject = new JSONObject("{register:false}");
            }else{


                jsonObject = new JSONObject("{register:true}");
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
