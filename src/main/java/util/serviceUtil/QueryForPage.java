package util.serviceUtil;

import bean.Member;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.QueryTool;
import util.connectonUtil.QueryRunnerUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by ASUS on 2019/8/14.
 *
 * 实现条件查询功能
 *
 *
 */
public class QueryForPage implements QueryTool {

    private static QueryForPage theObj = null;

    private QueryForPage() {
    }
    public static QueryForPage getInstance() {

        if(theObj==null) {
            synchronized (QueryForPage.class){
                if (theObj==null) {
                    theObj = new QueryForPage();
                }
            }
        }



        return theObj;
    }





    /**
     * 分页条件查询，返回查询的记录数
     *
     * @return
     */
    @Override
    public int count(Map<String, String[]> conditon) {
        //1.定义基础的 sql语句
        String sql = "select count(*) from member where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        Iterator it = conditon.entrySet().iterator();
        Object key;


        List<Object> params = new ArrayList<>(20);

        //如果是第一次进入程序 由于 1=1 于是要加 and ,后面sql 都拼接 or
        boolean isTheFirst = true;

        while (it.hasNext()) {
            //使用 entrySet 获取键值
            Map.Entry entry = (Map.Entry)it.next();

            key = entry.getKey();



            //过滤掉没用的条件
            if (key.equals("currentPage")||key.equals("rows")) {
                continue;
            }
            /*
             *添加动态查询条件
             *
             * */
            String[] arr = conditon.get(key);
            String theFirstValue = arr[0];
            if(theFirstValue!=null &&!"".equals(theFirstValue)) {
                /*
                *
                * 如果是第一次进入程序 拼接 and ,不然就拼接 or
                * */
                if (isTheFirst) {
                    sb.append("and "+key+" like ? ");
                    isTheFirst = false;
                }else{
                    sb.append("or "+key+" like ? ");
                }
                params.add("%"+theFirstValue+"%");



                //多重同一条件查询
                for(int i=1;i<arr.length;i++) {
                    if(arr[i]!=null && !arr[i].equals("")) {
                        sb.append(" or "+key+" like ? ");
                        params.add("%"+arr[i]+"%");
                    }
                }



            }
            System.out.println(sb.toString());
            System.out.println(params);






        }

        QueryRunner qr = QueryRunnerUtil.getQrConn();

        Object[] p = params.toArray();
        int temp = 0;
        try {

            temp = (int)(long) qr.query(sb.toString(),new ScalarHandler<>(),p);
            System.out.println(temp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return temp;
    }

    /**
     * 返回 对应的 查询结果集合
     *
     * @param start 在第几条记录开始
     * @param rows  要返回多少条记录
     *
     * @return
     */
    @Override
    public List<Member> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from member where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        //使用 entrySet 遍历
        Iterator it = condition.entrySet().iterator();
        ArrayList<String> params = new ArrayList<>(10);


        //第一次进入程序 sql 语句要用 and ,后面都用 or
        boolean isTheFirst = true;

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();

            String key = (String) entry.getKey();

            if("currentPage".equals(key)||"rows".equals(key)) {
                continue;
            }

            String[] arr = condition.get(key);

            if(arr[0]!=null&&!arr[0].equals("")) {

                if(isTheFirst) {
                    sb.append("and "+key+" like ? ");
                    isTheFirst = false;
                }else{
                    sb.append(" or "+key+" like ? ");
                }

                params.add("%"+arr[0]+"%");
            }

            for(int i=1;i<arr.length;i++) {
                if(arr[i]!=null && (!arr[i].equals(""))) {
                    sb.append(" or "+key+" like ? ");
                    params.add("%"+arr[i]+"%");
                }
            }

            //打印输出
            System.out.println(sb.toString());
            System.err.println(params);
            //*************************************************


        }
        /*
        *
        * 分页查询  分别对应 start 和 rows 参数
        *
        * */
        sb.append(" limit "+start+","+rows);
        // params.add(""+start+"");
        // params.add(""+rows+"");
        System.out.println(sb);
        System.out.println("params:       "+params);

        QueryRunner qr = QueryRunnerUtil.getQrConn();

        Object[] p = params.toArray();

        try {



            List<Member> mList = qr.query(sb.toString(),new BeanListHandler<>(Member.class),p);
            System.err.println(mList);
            System.err.println(sb.toString());

            return mList;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("返回失败");
        return null;
    }
}
