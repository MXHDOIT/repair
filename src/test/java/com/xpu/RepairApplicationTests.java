package com.xpu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.RepairApplication;
import com.xpu.repair.mapper.TechnicianMapper;
import com.xpu.repair.vo.TechnicianVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = RepairApplication.class)
@RunWith(SpringRunner.class)
class RepairApplicationTests {

    @Autowired
    TechnicianMapper technicianMapper;

    @Test
    void contextLoads() {
        Page<TechnicianVo> page = new Page<>(1,2);
        List<TechnicianVo> pageTechnicians = technicianMapper.findPageTechnicians(page);
        page.setRecords(pageTechnicians);
        System.out.println(page);
        System.out.println(page.getRecords());
    }

}
