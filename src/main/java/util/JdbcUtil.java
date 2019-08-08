package util;

/**
 * Created by ASUS on 2019/8/5.
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @deprecated
 * 这个类没有用了，使用QueryRunner 和C3p0 更加方便
 * */
public class JdbcUtil {
    // private static DataSource dataSource=null;
    // static{
    //     dataSource=new ComboPooledDataSource("mysql");
    // }

    /**
     * 实现对数据库的增加操作
     *
     * @deprecated
     * @return
     */
    public static void add(String sql,String[] arr) {
        Connection con =  QueryRunnerUtil.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement(sql);

            for(int i=0;i<arr.length;i++)
            {
                pstm.setString(i+1,arr[i]);
            }
            pstm.executeUpdate();
            System.out.println("插入成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(pstm,con);
        }


    }

    /**
     *
     * @deprecated
     *
    * */
    public static void release(ResultSet rs, PreparedStatement stmt, Connection conn) {
        if(rs!=null) {
            try {
                rs.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null) {
            try {
                stmt.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     *
     *
     *
     * @deprecated
     *
    * */
    public static void release(PreparedStatement stmt, Connection conn) {
        if(stmt!=null) {
            try {
                stmt.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
