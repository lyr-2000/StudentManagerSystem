/**
 * Created by ASUS on 2019/8/5.
 */

import bean.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import util.JdbcUtil;
import util.QueryRunnerUtil;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
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
            List<Member> list = qr.query(JdbcUtil.getConnection(),sql,new BeanListHandler<Member>(Member.class));
            for(Member i:list) {

                System.out.println(i.getName());
                System.out.println(i.getSignature());
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
