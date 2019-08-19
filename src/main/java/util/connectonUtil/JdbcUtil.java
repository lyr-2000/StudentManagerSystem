package util.connectonUtil;

/**
 * Created by ASUS on 2019/8/5.
 *
 * 实现分页等查询功能
 *
 *
 *
 */

import bean.Member;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @deprecated
 * 这个类部分功能不用了，使用QueryRunner 和C3p0 方便
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
     * 释放内存资源，关闭数据库connection
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
     *关闭数据库 connection
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
    /**
     *
     *
     * 返回数据库 表中的总记录数（总人数）
     *
     * @deprecated 没有实现条件查询功能
     *
     * @see  util.serviceUtil.QueryForPage 有相应的方法，实现条件查询
     *
     * */
    public static int count(String... tableName) {

        String sql =tableName==null||tableName.length!=1? "select count(*) from member":"select count(*) from "+tableName[0];
        Connection conn = QueryRunnerUtil.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            int count = 0;
            psmt = conn.prepareStatement(sql);

            rs = psmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
                System.out.println("总人数");

            }


            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(rs,psmt,conn);

        }

        return 0;


    }

    /**
     *
     * 实现分页查询功能
     *
     * 每次只是取出固定人数，分页查询
     *
     * @return start  从哪页开始
     *
     * @return rows 行数
     *
     * */
    public static List<Member> findByPage(int start,int rows/* 每页 rows 个 */,String...tableName) {
        List<Member> list = null;
        String sql =tableName==null||tableName.length!=1?  "select * from member limit ?,?":"select * from "+tableName[0]+" limit ?,?";
        System.err.println(sql);
        try{

            QueryRunner qr = QueryRunnerUtil.getQrConn();
            list = qr.query(sql,new BeanListHandler<Member>(Member.class),start,rows);
            System.out.println("分页查询底层操作成功");
            for(Member i: list) {
                System.out.println(i);
            }

            return list;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
