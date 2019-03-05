package com.jit.edu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:guxiang
 * @version:2019/3/4
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将请求和响应强制转换成Http形式
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 处理响应乱码
        response.setContentType("text/html;charset=utf-8");

        // 自定义一个request对象：MyRequest，对服务器原来的requset进行增强，使用装饰设计模式
        // 要增强原来的request对象，必须先获取到原来的request对象
        MyRequest myRequest = new MyRequest(request);

        // 注意：放行的时候应该传入增强后的request对象
        filterChain.doFilter(myRequest, response);
    }

    @Override
    public void destroy() {

    }
}
