import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

/**
 * Created by ASUS on 2019/8/5.
 */
public class Test1 {
    @Test
    public void demo() throws Exception{
        System.out.println("hello");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        if(dataSource!=null)
            System.out.println("初始化成功");
        else
            System.out.println("初始化失败");
        Connection conn = dataSource.getConnection();
        System.out.println(conn);



    }
    /*
    *
    * 测试用例成功
    *
    * */
    @Test
    public void demo2() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
        System.out.println("第一步准备就绪");

        Connection conn = dataSource.getConnection();
        System.out.println("******************** 1 ********************************");

        String sql = "SELECT * FROM Me";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        PreparedStatement sta = pre;

        if(rs.next())
        {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }

        if(rs!=null)
        {
            try {
                rs.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(sta!=null)
        {
            try {

                sta.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }



        if(conn!=null) {
            try {
                conn.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }


    }





    @Test
    public  void hello() {
        System.out.println("hellollll" +
                "");
    }


    @Test
    public void find() {
        String str = "http://localhost:8080/findByPage.do?currentPage=1&rows=5&search=search&uName=&uId=";
        System.out.println(str.indexOf("findByPage.do?"));
        System.out.println(str.length());
        String s = "1234";
        System.out.println(s.indexOf("0"));
    }



}