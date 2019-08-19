package dao;

/**
 * Created by ASUS on 2019/8/9.
 *
 * 主要是后面加了个功能，处理批量删除这个业务逻辑
 *
 *
 */
public interface DeleteDao {
    /**
     *
     * 批量删除业务
     * @param tableName 用户要操作的数据库表名字，如果没有，默认操作 member表
     * @param arr 用户的 id 数组，通过id批量删除表的记录
     *
     * */
    void deleteSelected(String[] arr,String...tableName);
    void deleteChoose(String[] arr,String tableName);

}
