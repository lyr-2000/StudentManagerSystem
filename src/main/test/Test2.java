/**
 * Created by ASUS on 2019/8/5.
 */

import bean.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import dao.daoImpl.MemberDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import util.connectonUtil.JdbcUtil;
import util.connectonUtil.QueryRunnerUtil;
import util.serviceUtil.QueryForPage;

import java.sql.*;
import java.util.*;

public class Test2 {

    /*
    * 对QueryRunner 的测试类
    *不用手动关闭数据库连接
    * */
    @Test
    public void QueryRunnerTest()
    {
        System.out.println("fff");
        ComboPooledDataSource ds = new ComboPooledDataSource("mysql");
        //传递连接池对象，不需要手动关闭
        QueryRunner qr = new QueryRunner(ds);
        String sql = "insert into member(?,?,?,?,?,?,?,?,?)";



    }

    /*
    *
    * 测试成功
    *
    * */
    @Test
    public void QueryAll() {
        try{
            QueryRunner qr = QueryRunnerUtil.getQrConn();
            String sql = "select * from member";
            List<Member> list = qr.query(QueryRunnerUtil.getConnection(),sql,new BeanListHandler<Member>(Member.class));
            for(Member i:list) {

                System.out.println(i.getName());
                System.out.println(i.getSignature());
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*
    *
    *
    * 用来尝试对表进行插入操作
    * 经过试验，是成功的
    *
    * */
    @Test
    public void add() throws SQLException {
        String sql = "insert into test (name,password)value(?,?)";
        QueryRunner qr = QueryRunnerUtil.getQrConn();
        String [] params = {
                "444",
                "扫]]]地"
        };
        /*
        *
        * 总结：一定要在配置文件指定 为utf-8编码，不然中文乱码
        *
        * */

        JdbcUtil.add(sql, params);



    }
    @Test
    public void mm() {
        String[] arr1 = new String[]{"123","234"};
        String[] arr2 = new String[]{"123","456"};


        List<String >l2 = Arrays.asList(arr2);
        Set set = new TreeSet();
        set.addAll(l2);
        set.addAll(Arrays.asList(arr1));
        System.out.println(set);



    }

    @Test
    public void dbTool() {
        QueryForPage p = QueryForPage.getInstance();
        Map<String,String[]> map = new HashMap();
        p.count(map);
    }

    /*
    *
    * 测试用例通过
    *
    * */
    @Test
    public void dbTool2() {
        QueryForPage p = QueryForPage.getInstance();
        Map<String,String[]> map = new HashMap();
        map.put("name",new String[]{"林东","123"});
        map.put("`id`",new String[]{"777"});


        p.count(map);
    }

    /*
    *
    * 测试用例通过
    *
    * */
    @Test
    public void dbToolForList() {
        QueryForPage p = QueryForPage.getInstance();
        Map<String,String[]> map = new TreeMap();
        map.put("`id`",new String[]{"777"});
        map.put("name",new String[]{"林东","123"});

        p.findByPage(0,5,map);
    }


    /*
    *
    * 取交集，运算，条件查询的 函数测试
    *
    * */
    @Test
    public void insertSet () {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> insertSet = new TreeSet<>();
                String[][] arr = new String[][]{ {"123"},{"345"},{"123"}   };
                for(int i=0;i<arr.length;i++) {
                    List<String> p = Arrays.asList(arr[i]);
                    insertSet.addAll(p);
                }

                System.out.println(insertSet);
            }
        }).run();

    }

    @Test
    public void say() {

        String sb = "select * from member where 1 = 1 and `name` like '%林东%' limit 0,5";
        Object p = new String[] {"'%林东%'"};
        System.out.println(p.toString()+"---------------------");

        QueryRunner qr = QueryRunnerUtil.getQrConn();
        List<Member> mList = null;
        try {
            mList = qr.query(sb.toString(),new BeanListHandler<>(Member.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.err.println(mList);
        System.err.println(sb.toString());
    }

    @Test
    public void filter() {
        String s = "林东";
        String name = "林东";
        System.out.println(s.compareTo(name));
    }

    @Test
    public void login() {

        List<Member> mList = MemberDao.getInstance().QueryById("admin","admin",true);
        Member m = mList.get(0);
        System.out.println(m.getAdmin());
        System.out.println(m);




    }





}
