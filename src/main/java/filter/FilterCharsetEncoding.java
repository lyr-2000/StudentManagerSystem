package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2019/8/5.
 * 设置字符集编码 统一为 utf-8
 *
 */
@WebFilter(filterName = "FilterCharsetEncoding",urlPatterns = "/*")
public class FilterCharsetEncoding implements Filter {
    // private FilterConfig filterConfig = null;
    // private String defaultCharest = "utf-8";


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter1  -----");
        HttpServletResponse hresp =(HttpServletResponse)resp;
        HttpServletRequest hreq = (HttpServletRequest)req;
        //获取xml中配置的字符集


        hreq.setCharacterEncoding("utf-8");
        hresp.setCharacterEncoding("utf-8");
        chain.doFilter(hreq,hresp);





    }

    public void init(FilterConfig config) throws ServletException {
        // this.filterConfig = config;
    }

}
