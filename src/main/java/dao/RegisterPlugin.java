package dao;

import bean.Member;

/**
 * Created by ASUS on 2019/8/18.
 *
 * 用户注册，插入数据到数据库
 *
 */
public interface RegisterPlugin {

    /**
     * 用户注册申请
     *
     *@return 用户注册是否成功
     *
     * @param m 用户提交信息
     * */
     boolean register(Member m);

     /**
      *
      * @return 用户注册提交信息，存放在logimMember表中，把信息插入该表 ，等待管理员审核
      *
      * */
     boolean registerInsert(Member m);

}
