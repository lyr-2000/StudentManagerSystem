package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

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


    /*
    *
    * 使用 QueryRunner，不用手动关闭连接
    *
    * */
    public static QueryRunner getQrConn() {
        return new QueryRunner(ds);
    }

}
