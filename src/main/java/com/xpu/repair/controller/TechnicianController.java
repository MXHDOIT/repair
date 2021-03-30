package com.xpu.repair.controller;


import com.xpu.repair.dto.R;
import com.xpu.repair.entity.Technician;
import com.xpu.repair.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 维修人员表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/technician")
public class TechnicianController {

    @Autowired
    TechnicianService technicianService;

    @PostMapping("/login")
    public R login(Technician technician){

        Technician getTechnician = technicianService.getById(technician.getId());
        if (getTechnician != null && technician.getPassword().equals(getTechnician.getPassword())){
            return R.ok().data("id",technician.getId()).data("password",technician.getPassword());
        }else {
            return R.error();
        }
    }
}

