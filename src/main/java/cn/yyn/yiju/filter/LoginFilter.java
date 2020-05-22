package cn.yyn.yiju.filter;



import cn.yyn.yiju.bean.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 =   (HttpServletRequest)request;
        HttpServletResponse response1 =   (HttpServletResponse)response;

        HttpSession session = request1.getSession();
        UserInfo user = (UserInfo) session.getAttribute("user");
        String url = request1.getRequestURI();
        System.out.println(url);
        if(user == null && url.indexOf("/user/login.do") == -1 &&
                url.indexOf("/house/findFourHouse.do") == -1 &&
                url.indexOf("index.jsp") == -1 &&
                url.indexOf("main.jsp") == -1 &&
                url.indexOf("login.jsp") == -1 &&
                url.indexOf("signup.jsp") == -1 &&
                (!url.equals("/yiju_war_exploded/"))){
            response1.sendRedirect(request1.getContextPath() + "/pages/login.jsp");
        }else {
            chain.doFilter(request1,response1);
        }

    }

    @Override
    public void destroy() {

    }
}
