package dao.daoImpl;

import bean.Member;
import dao.Dao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.QueryRunnerUtil;

import java.util.List;

/**
 * Created by ASUS on 2019/8/5.
 */
public class MemberDao implements Dao {
    @Override
    public void add(Object member) {

    }

    @Override
    public void del(Object member) {

    }

    @Override
    public void Query(Object member) {

    }


    /*
    *
    * 查询所有成员的信息
    *
    * */
    @Override
    public void QueryAll() {
        try{
            QueryRunner qr = QueryRunnerUtil.getQrConn();
            String sql = "select * from member";
            List<Member> list = (List<Member>) qr.query(sql,new BeanHandler<Member>(Member.class));
            for(Member i:list) {
                System.out.println(i);
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(Object member) {

    }
}
