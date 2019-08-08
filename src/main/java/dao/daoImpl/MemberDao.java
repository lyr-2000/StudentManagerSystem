package dao.daoImpl;

import bean.Member;
import dao.Dao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.QueryRunnerUtil;

import java.sql.SQLException;
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
public class MemberDao implements Dao {

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
            List<Member> list  = qr.query(QueryRunnerUtil.getConnection(),ans, new BeanListHandler<Member>(Member.class));
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
            qr.update(QueryRunnerUtil.getConnection(),sql,param);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("dao层的更新操作出问题了，处理一下");

        }

        return false;
    }
}
