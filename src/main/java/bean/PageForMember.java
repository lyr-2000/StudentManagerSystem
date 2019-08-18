package bean;
import java.util.List;

/**
 * Created by ASUS on 2019/8/10.
 *
 * 封装了Member对象，返回Member 集合实现分页查询
 *
 *
 */
public class PageForMember<T> {
    /**
     *
     * 查询的类型
     * 是否为带条件的分页查询
     *
     * */
    private byte QueryStyle;



    /**
     *
     * 总人数
     *
     * */

    private int totalCount;
    /**
     * 总页数
     *
     * */
    private int totalPage;

    /**
     *
     * 放入 Member 集合
     * 实现分页查询功能
     *
     *
     * 每页的数据
     *
     * */
    private List<T> list;
    //当前页数

    /**
     *
     * 当前页数
     *
     * */
    private int currentPage;
    //行数
    /**
     *
     * 行数
     *
     * */
    private int rows;
    /**
     *
     * 返回总页数
     *
     * */
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    /**
     *
     * Member List 集合
     *
     * */
    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }









    public byte getQueryStyle() {
        return QueryStyle;
    }

    public void setQueryStyle(byte queryStyle) {
        QueryStyle = queryStyle;
    }

    public PageForMember() {

        /*
        *
        * 默认不是带条件的分页查询，如果前端发送查询条件，再由 servlet层修改
        * */
        this.QueryStyle = 0;
    }
}
