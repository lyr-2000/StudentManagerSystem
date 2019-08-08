package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ASUS on 2019/8/5.
 */
public class QueryRunnerUtil {

    private static DataSource ds;
    /*
    *
    * 静态初始化
    *
    * */
    static {
        ds = new ComboPooledDataSource("mysql");
    }


    /**
    *
    * 使用 QueryRunner，不用手动关闭连接
     *
     *
    *
    * */
    public static QueryRunner getQrConn() {
        return new QueryRunner(ds);
    }

    /**
     *
     * @return Connection
     * 获得数据库C3p0连接
    *
    * */
    public static Connection getConnection(){
        java.sql.Connection conn=null;
        try {
            conn=ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
