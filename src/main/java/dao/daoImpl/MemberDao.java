package dao.daoImpl;

import bean.Member;
import bean.PageForMember;
import dao.Dao;
import dao.QueryForPageDao;
import dao.DeleteDao;
import dao.LoginPlugin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.connectonUtil.JdbcUtil;
import util.connectonUtil.QueryRunnerUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/8/5.
 */
/**
 *
 * 写代码的时候我发现了一个问题，每次操作数据库就 实例化一个dao 出来，那么不如使用单例模式吧
 *
 * @param
 * @return
 *
 * */
public class MemberDao implements Dao , DeleteDao, QueryForPageDao, LoginPlugin {

    private static MemberDao dao = null;
    static {
        dao = new MemberDao();
    }

    /**
     *
     * 饿汉式单例模式
     * */
    public static MemberDao getInstance() {
        return dao;
    }


    private MemberDao(){

    }




    @Override
    public boolean add(Object member)  {
        if (member instanceof Member) {
            Member m = (Member)member;

            boolean b = QueryById(m.getId());
            if(b==true) {
                /*
                * 如果发生数据库里有这个人了，不要再执行insert 操作了
                *
                * */
                System.err.println("非常抱歉，已经有这个人了，不能再添加了");
                return false;//数据库已经有这个人了，返回false表示添加失败
            }


            String sql = "insert into member(id,name,sex,joinTime,work,birthday,subject,phone,signature,password) values(?,?,?,?,?,?,?,?,?,?)";
            QueryRunner qr = QueryRunnerUtil.getQrConn();
            Object[] params={
              m.getId(),
              m.getName(),
              m.getSex(),
              m.getJoinTime(),
              m.getWork(),
              m.getBirthday(),
              m.getSubject(),
              m.getPhone(),
              m.getSignature(),
              m.getPassword()



            };

            try {
                qr.update(QueryRunnerUtil.getConnection(),sql,params);
                System.out.println("很好，添加成功");

            } catch (SQLException e) {
                System.err.println("不要意思，在最后添加成员的时候出现问题了，处理一下");
                e.printStackTrace();
            }

            return true;
        }
        return false;

    }
    /*
    *
    * 通过id进行删除
    * */
    @Override
    public void del(Object member) {
        if(member instanceof Member)
        {
            Member m = (Member)member;
            if(m.getId()==null) return;
            QueryRunner qr =QueryRunnerUtil.getQrConn();
            String sql = "delete from member where `id`="+"'"+m.getId()+"'";
            try {
                qr.update(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void Query(Object member) {

    }


    /*
    *
    * 查询所有成员的信息
    *
    * */
    @Override
    public List QueryAll() {

        List<Member> list = null;
        try{
            QueryRunner qr = QueryRunnerUtil.getQrConn();
            String sql = "select * from member";
            list = qr.query(QueryRunnerUtil.getConnection(),sql,new BeanListHandler<Member>(Member.class));



        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @return boolean
     * 通过id查询是否数据库中存在这个对象，是的话返回true，如果数据库没有的话返回false
     *
     * */

    @Override
    public boolean QueryById(String id) {
        String  sql = "select * from member where `id` = '";
        String end = id+"'";
        String ans = sql + end;

        QueryRunner qr = QueryRunnerUtil.getQrConn();
        try {
            List list = qr.query(QueryRunnerUtil.getConnection(),ans,new BeanListHandler<Member>(Member.class));
            if(!list.isEmpty()) {
                /*
                *
                * 如果发生数据库已经有了这个人了，就不要加了， true表示有，false表示查无此人
                *
                * */
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
    /**
     *
     * @return Member
     * 通过 id 查询数据库返回对于的bean对象
     *
     * */
    @Override
    public Member QueryById2(String id) {
        String  sql = "select * from member where `id` = '";
        String end = id+"'";
        String ans = sql + end;
        QueryRunner qr = QueryRunnerUtil.getQrConn();

        try {
            List<Member> list  = qr.query(ans, new BeanListHandler<Member>(Member.class));
            /*
            * 获得Member集合，然后取出第一个对象（数据库没问题就是唯一的对象）并且返回去
            *
            * */
            System.err.println(list.get(0));
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new Member();
    }
    /**
    *
    * 负责对数据库的内容进行修改操作
    *
    * @param member
    * Member或者bean 对象
    * */
    @Override
    public void set(Object member) {
        if(member instanceof Member) {
            Member m = (Member)member;
            update(m);

        }
    }


    /**
     *
     * 解决添加成员的业务逻辑
     *
     * @param m
     *
    * */
    private boolean update(Member m) {
        String sql1 =
                "update member set `name`=?,sex=?,joinTime=?,work=?,birthday=?,subject=?,phone=?,signature=?,`password`=?";
        String sql2 = " where `id`=  "+ m.getId();
        String sql = sql1+sql2;

        Object[] param ={

                m.getName(),
                m.getSex(),
                m.getJoinTime(),
                m.getWork(),
                m.getBirthday(),
                m.getSubject(),
                m.getPhone(),
                m.getSignature(),
                m.getPassword()

        };

        System.out.println(sql+"--------------");
        QueryRunner qr = QueryRunnerUtil.getQrConn();
        try {
            qr.update(sql,param);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("dao层的更新操作出问题了，处理一下");

        }

        return false;
    }

    /**
     *
     *
     * 重写 deleteDao接口，解决批量删除的业务逻辑
     * @param arr  这是一个存放 用户 id 的数据，通过id 访问数据，并且删除 数据
     *
     * */
    @Override
    public void deleteSelected(String[] arr) {


        for(String id: arr){

            Member m = new Member();
            m.setId(id);
            dao.del(m);
        }
    }



    /**
     *
     * 分页查询并返回 对于的 集合 的 实现方法
     *
     * @param currentPage
     * @param rows
     *
     *
     *
     * */

    @Override
    public PageForMember QueryForPage(String currentPage, String  rows) {
         PageForMember pb = new PageForMember();
         int currentPage1 = Integer.parseInt(currentPage);
         int rows1 = Integer.parseInt(rows);

         //设置当前所在页数
         int start = (currentPage1-1)*rows1;


         List<Member> list = JdbcUtil.findByPage(start,rows1);

         //获得 对象集合 并且封装成 PageForMember 对象的变量
         pb.setList(list);
         //————————————————————————————
         for(Member i: list) {
             System.out.println(i);
         }
         //------- ------- -------- --------- ------- ------- --------

        //设置总的数据量信息
        pb.setTotalCount(this.getTotalCount());

         /*
         *
         * 计算总的页数
         *
         * */
         int totalPage = pb.getTotalCount()%rows1==0 ? pb.getTotalCount()/rows1: pb.getTotalCount()/rows1+1;
         pb.setTotalPage(totalPage);



         return pb;







    }

    /**
     *
     * 查询数据库中的记录总数
     *
     *
     * */
    public int  getTotalCount() {


        /*
        * 查询数据库中的 记录数量
        * */
        int count = JdbcUtil.count();

        return count;
    }

    /**
     * 登录页面的时候调用 该方法，查询用户是否存在
     *
     * @param id       用户学号
     * @param password 用户密码
     * @param admin    是否查询用户的身份，用户有分 管理员 和普通成员两种
     */
    @Override
    public List QueryById(String id, String password, boolean admin) {


        String sql = "select * from member where `id`=? and `password`=?";
        QueryRunner qr = QueryRunnerUtil.getQrConn();

        Object[] param = {id,password};
        try {
            List<Member> m = qr.query(sql,new BeanListHandler<Member>(Member.class),param);
            if(admin) {
                System.out.println(m);
            }
            return m;
        } catch (SQLException e) {
            System.err.println("注册验证的时候查询 数据库 出现问题");
            e.printStackTrace();
        }


        List p = new ArrayList<>();
        p.add(new Member());
        return p;
    }
}
