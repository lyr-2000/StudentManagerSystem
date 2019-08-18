package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2019/8/16.
 *
 * 用于过滤用户登录验证
 *
 *
 */
@WebFilter(filterName = "FilterForLogin",urlPatterns = "/*")
public class FilterForLogin implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("filter2  ---");

        HttpServletRequest hrequest = (HttpServletRequest)req;
        HttpServletResponse hresponse = (HttpServletResponse)resp;
        Object loginUser = hrequest.getSession().getAttribute("loginUser");
        String uri = hrequest.getRequestURI();

        if(uri.contains("/login.do")||uri.contains("/checkCodeServlet")||uri.contains("/login.jsp")||uri.contains("/index.jsp")) {
            System.out.println("放行.....");
            chain.doFilter(hrequest,hresponse);
            return;
        }
        if(loginUser==null&&(uri.contains("/show.do")||uri.contains("/findByPage.do")||uri.contains("/add.do")||uri.contains("/alter.do")||uri.contains("/dels.do")||uri.contains("/del.do"))) {
            hresponse.sendRedirect("/index.jsp");
            System.err.println("打劫.....");
            return;
        }

        /*
        *
        * 如果在地址栏里 有操作数据库的 网页，重定向到登录页面
        *
        * */
        if(loginUser==null) {

            String str = uri;
            int find1 = str.indexOf("/show.do");
            int find2 = str.indexOf("/findByPage.do");
            int find3 = str.indexOf("/alter.do");
            int find4 = str.indexOf("del");

            if(find1>0||find2>0||find3>0||find4>0) {
                hresponse.sendRedirect("/index.jsp");
                System.out.println("拦路...");
                return;
            }





        }



           System.err.println(" 默认 放行--------------------------------------  ");
           chain.doFilter(req, resp);
           return;


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
