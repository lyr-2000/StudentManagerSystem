package dao;

/**
 * Created by ASUS on 2019/8/5.
 */
public interface Dao {
    /*
    * 增
    * */
    void add(Object o);

    /*
    *
    * 删除
    * */
    void del(Object o);

    /*
    * 查询
    * */
    void Query(Object o);
    void QueryAll();


    /*
    *
    * 修改
    * */
    void set(Object o);
}
