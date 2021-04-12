package com.xpu.repair.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("maxinhangdoit@163.com");
        mailSender.setPassword("PLLIICTXALTYPEKI");
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }
}
