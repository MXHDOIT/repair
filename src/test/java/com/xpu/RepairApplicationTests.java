package com.xpu;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.RepairApplication;
import com.xpu.repair.mapper.MaintenanceMapper;
import com.xpu.repair.mapper.RepairMapper;
import com.xpu.repair.mapper.TechnicianMapper;
import com.xpu.repair.vo.MaintenanceVo;
import com.xpu.repair.vo.RepairVo;
import com.xpu.repair.vo.TechnicianVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = RepairApplication.class)
@RunWith(SpringRunner.class)
class RepairApplicationTests {

    @Autowired
    TechnicianMapper technicianMapper;

    @Autowired
    MaintenanceMapper maintenanceMapper;
    @Test
    void contextLoads() {
       Page<MaintenanceVo> page = new Page<>(1,10);
        List<MaintenanceVo> allMaintenanceVO = maintenanceMapper.findAllMaintenanceVO(page);
        page.setRecords(allMaintenanceVO);

        System.out.println(page.getRecords().get(0));
    }

}
