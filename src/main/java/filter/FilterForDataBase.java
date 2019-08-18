package filter;

import bean.Member;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ASUS on 2019/8/18.
 *
 * 对于不是管理员的 用户 如果 在地址栏里 输入了操作数据库的网页 或者 servlet 的路径，要进行 拦截
 * 比如 用户可以 查看 部落里 其他成员的 信息，但是 绝对不能修改，增加，删除...
 *
 *
 *
 */
@WebFilter(filterName = "FilterForDb",urlPatterns = "/*")
public class FilterForDataBase implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter3  ");
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        HttpServletResponse httpServletResponse = (HttpServletResponse)resp;
        Member self = (Member)httpServletRequest.getSession().getAttribute("loginUser");
        // String admin = self.getAdmin();

        String uri = httpServletRequest.getRequestURI();
        if(self==null) {
            /*
            * 有 FilterForLogin 进行拦截处理
            * */
            chain.doFilter(httpServletRequest,httpServletResponse);
            System.out.println("你好世界");
            return;
        }


        /*
         *
         * 数据库显示不是管理员，就重定向到 原来的页面
         *
         * */
        if(!(self.getAdmin()).equals("管理员")){
            System.out.println("不是管理员");

            int find1 = uri.indexOf("alter");
            int find2 = uri.indexOf("del");
            int find3 = uri.indexOf("add");
            if(find1>0||find2>0||find3>0) {
                System.out.println("回去");
                // httpServletResponse.sendRedirect("/show.do");
                redirect(httpServletRequest,httpServletResponse,"/findByPage.do?admin=false");
                return;
            }



        }
        /*
         *
         *管理员可以通过操作
         *
         * */


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }


    private void redirect(HttpServletRequest request,HttpServletResponse response,String redirectUrl) throws IOException{
        String ctx = request.getContextPath();
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //如果是ajax请求
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "redirect");
            //告诉ajax我重定向的路径
            response.setHeader("redirectUrl", redirectUrl);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            response.getWriter().write("notAdmin");


            System.out.println("ajax 处理");
        }else{
            response.sendRedirect(redirectUrl);
            System.out.println("重定向 处理");
        }
    }

}
