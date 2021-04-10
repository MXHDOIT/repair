package com.xpu.repair.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Maintenance;
import com.xpu.repair.service.MaintenanceService;
import com.xpu.repair.vo.MaintenanceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 维修记录表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MaintenanceService maintenanceService;

    @RequestMapping(value = "showCompleteMaintenancePage",method = RequestMethod.GET)
    public String showCompleteMaintenancePage(Model model, @RequestParam(value = "pageNum",
            required = false,defaultValue = "1") int pageNum) {
        logger.info("pageNum:{}",pageNum);
        Page<MaintenanceVo> completeMaintenance = maintenanceService.findCompleteMaintenance(pageNum);
        model.addAttribute("page",completeMaintenance);
        return "admin/showCompleteMaintenance";
    }
}

