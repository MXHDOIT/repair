package com.xpu;

import com.aliyun.oss.OSSClient;
import com.xpu.repair.RepairApplication;
import com.xpu.repair.mapper.MaintenanceMapper;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.mapper.TechnicianMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${aliyun.oss.file.endpoint}")
    String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    String accessKeyId;

    @Value("${aliyun.oss.file.keysecret}")
    String accessKeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    String bucketName;

    @Test
    void testCreateBucket() {
        //创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        //创建存储空间
//        ossClient.createBucket("mxh-test-file");

        //查看指定存储空间是否存在
        boolean bucketExist = ossClient.doesBucketExist(bucketName);
        System.out.println(bucketExist);
        //关闭OSSClient
        ossClient.shutdown();
    }
}
