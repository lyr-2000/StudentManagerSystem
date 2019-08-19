package dao;

import bean.PageForMember;

import java.util.List;

/**
 * Created by ASUS on 2019/8/9.
 */
public interface QueryForPageDao {

    /**
     *
     * 把分页查询得到的集合封装并返回 一个 PageForMember 对象
     *
     * @param currentPage
     * @param rows
     * @param tableName 用户传入的数据库表名字，如果没有，默认使用 member表
     *
     * */
    PageForMember QueryForPage(String currentPage, String  rows,String... tableName);



}
