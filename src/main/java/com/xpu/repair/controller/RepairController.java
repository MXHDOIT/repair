package com.xpu.repair.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.entity.Repair;
import com.xpu.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报修单表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    RepairService repairService;

    @RequestMapping(value = "showRepairsPage",method = RequestMethod.GET)
    public String showRepairsPage(Model model,@RequestParam(value = "pageNum",
            required = false,defaultValue = "1") int pageNum) {
        Page<Repair> repairPage = repairService.findRepairPage(pageNum);
        model.addAttribute("page",repairPage);
        return "admin/showRepairs";
    }
}

