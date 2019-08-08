package dao;

import bean.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ASUS on 2019/8/5.
 */
public interface Dao {
    /*
    * 增
    * */
    boolean add(Object o) throws SQLException;

    /*
    *
    * 删除
    * */
    void del(Object o);

    /*
    * 查询
    * */
    void Query(Object o);
    List QueryAll();
    boolean QueryById(String id);//通过学号查询数据
    Object QueryById2(String id);//返回对于的bean对象

    /*
    *
    * 修改
    * */
    void set(Object o);
}
