package com.xpu;

import com.xpu.repair.RepairApplication;
import com.xpu.repair.mapper.MaintenanceMapper;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.mapper.TechnicianMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RepairApplication.class)
@RunWith(SpringRunner.class)
class RepairApplicationTests {

    @Autowired
    TechnicianMapper technicianMapper;

    @Autowired
    MaintenanceMapper maintenanceMapper;

    @Autowired
    RepairMapper repairMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("maxinhangdoit@163.com");
        mailMessage.setTo("new_fc@163.com");
        mailMessage.setSubject("西邮研究生录取通知书");
        mailMessage.setText("祝研究生之旅顺利！！！");

        javaMailSender.send(mailMessage);
    }

}
