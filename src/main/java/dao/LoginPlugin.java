package dao;

import bean.Member;

import java.util.List;

/**
 * Created by ASUS on 2019/8/16.
 */
public interface LoginPlugin {


    /**
     *
     * 登录页面的时候调用 该方法，查询用户是否存在
     *
     * @param id 用户学号
     * @param password 用户密码
     * @param admin 是否查询用户的身份，用户有分 管理员 和普通成员两种
     *
     * */
    List QueryById(String id, String password, boolean admin);



}
