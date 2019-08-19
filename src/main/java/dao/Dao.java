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

    /**
     *
     * @see dao.daoImpl.MemberDao
     * @param sqlCmd 用户如果传入sql 命令，就使用对应的命令，不然就使用默认的sql 语句
     * */
    boolean add(Object o,String... sqlCmd) throws SQLException;

    /**
    *
    * 删除
     *
     * @param o  用户传入 Member对象，对数据库的记录进行删除
     *
    * */
    void del(Object o);

    /*
    * 查询
    * */
    void Query(Object o);
    /**
     *
     * 查询数据库的所有记录，并且返回 Member的 集合
     * @return Member 的集合
     *
     * */
    List QueryAll();//把数据库member表 中的所有用户返回

    /**
     *
     * 根据id 进行查询
     * @param tableName 数据库表名字，如果传入表的名字，就使用默认的表（member）
     * @param id 用户查询用 的id,
     * @return 返回布尔值，如果用户存在，返回 true，否则返回 false
     *
     * */
    boolean QueryById(String id,String... tableName);  //通过学号查询数据,使用不定长参数，如果 上一层传入数据库表 名字，就按照指定的表来，不然使用默认的 member表，返回值表示是否存在用户

    Object QueryById2(String id,String... tableName);    //返回查询到的bean对象




    /**
    *
    * 修改
     *
     * 管理员修改调用
     * @param o 传入 Member对象，把数据读取出来根据数据库的表进行修改
     *
    * */
    void set(Object o);
}
