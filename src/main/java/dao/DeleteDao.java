package dao;

/**
 * Created by ASUS on 2019/8/9.
 *
 * 主要是后面加了个功能，处理批量删除这个业务逻辑
 *
 *
 */
public interface DeleteDao {
    void deleteSelected(String[] arr);

}
