package dao;

import bean.Member;

/**
 * Created by ASUS on 2019/8/18.
 *
 * 用户注册，插入数据到数据库
 *
 */
public interface RegisterPlugin {

    /*
     * 用户注册申请
     *
     *
     * */
     boolean register(Member m);

}
