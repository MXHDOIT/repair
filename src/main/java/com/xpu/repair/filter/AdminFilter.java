package com.xpu.repair.filter;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] excludedUris;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String parameter = filterConfig.getInitParameter("exclusions");
        if (StringUtils.isNoneBlank(parameter)){
            this.excludedUris = parameter.split(",");
        }
        logger.info("AdminFilter过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.info("进入AdminFilter过滤器,处理请求");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        for (String uri : excludedUris) {
            if (request.getRequestURI().equals(uri)){
                chain.doFilter(request,response);
                return;
            }
        }
        //session验证
        if (request.getSession(false) != null && request.getSession(false).getAttribute("admin") != null){
            chain.doFilter(request,response);
        }else{
            response.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
        logger.info("AdminFilter过滤器销毁");
    }
}
