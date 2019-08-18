package util;

import bean.Member;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2019/8/14.
 *
 *
 * 条件查询功能的 接口
 *
 * @version 2.0
 *
 *
 */
public interface QueryTool {

    /**
     *
     * 分页条件查询，返回查询的记录数
     *
     * @return
     * */
    int count(Map<String, String[]> conditon);

    /**
     *
     * 返回 对于的 查询结果集合
     *
     * @param start 在第几条记录开始
     * @param rows 要返回多少条记录
     * @param condition 要查询的条件， 比如 name ->{"124","999"   }  ,id -> {"000","001"}
     *
     * @return
     *
     * */


    List<Member> findByPage(int start, int rows, Map<String, String[]> condition);
}
