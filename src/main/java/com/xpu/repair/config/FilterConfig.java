package com.xpu.repair.config;

import com.xpu.repair.filter.AdminFilter;
import com.xpu.repair.filter.TechnicianFilter;
import com.xpu.repair.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBeanUser() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new UserFilter());
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addInitParameter("exclusions","/user/login");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanAdmin() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/technician/*");
        registrationBean.addInitParameter("exclusions","/technician/login");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean registrationBeanTechnician() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TechnicianFilter());
        registrationBean.addUrlPatterns("/technician/*");
        registrationBean.addInitParameter("exclusions","/technician/login");
        return registrationBean;
    }
}
