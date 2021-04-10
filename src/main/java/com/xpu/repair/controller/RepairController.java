package com.xpu.repair.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.xpu.repair.dto.R;
import com.xpu.repair.entity.Maintenance;
import com.xpu.repair.entity.Profession;
import com.xpu.repair.entity.Repair;
import com.xpu.repair.entity.Technician;
import com.xpu.repair.enums.RepairStatusEnum;
import com.xpu.repair.service.MaintenanceService;
import com.xpu.repair.service.ProfessionService;
import com.xpu.repair.service.RepairService;
import com.xpu.repair.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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

    @Autowired
    TechnicianService technicianService;

    @Autowired
    ProfessionService professionService;

    @Autowired
    MaintenanceService maintenanceService;

    @RequestMapping(value = "showRepairsPage",method = RequestMethod.GET)
    public String showRepairsPage(Model model,@RequestParam(value = "pageNum",
            required = false,defaultValue = "1") int pageNum) {
        Page<Repair> repairPage = repairService.findRepairPage(pageNum);
        model.addAttribute("page",repairPage);
        return "admin/showAllRepairs";
    }

    @RequestMapping(value = "showUnallocatedRepairsPage",method = RequestMethod.GET)
    public String showUnallocatedRepair(Model model,@RequestParam(value = "pageNum",
            required = false,defaultValue = "1") int pageNum) {
        Page<Repair> UnallocatedRepairPage = repairService.findUnallocatedRepairPage(pageNum);

        //所有维修工人
        List<Profession> professionList = professionService.list(null);
        List<Technician> technicianList = Lists.newArrayList();
        for (Profession profession : professionList) {
            QueryWrapper<Technician> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("profession_id",profession.getId());
            List<Technician> technicians = technicianService.list(queryWrapper);
            for (Technician technician : technicians) {
                technician.setName(profession.getName()+"-"+technician.getName());
            }
            technicianList.addAll(technicians);
        }

        model.addAttribute("technicianList",technicianList);
        model.addAttribute("page",UnallocatedRepairPage);
        return "/admin/showUnallocatedRepairs";
    }

    @Transactional
    @RequestMapping(value = "allocated",method = RequestMethod.POST)
    @ResponseBody
    public R allocated(int repairId,String technicianId) {
        Repair repair = repairService.getById(repairId);
        repair.setStatus(RepairStatusEnum.ALLOCATED.getStatusId());

        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",repairId);
        boolean updateResult = repairService.update(repair, queryWrapper);

        Maintenance maintenance = new Maintenance();
        maintenance.setRepairId(repairId);
        maintenance.setTechnicianId(technicianId);
        maintenance.setStartTime(new Date());

        boolean saveResult = maintenanceService.save(maintenance);
        if (updateResult && saveResult){
            return R.ok().data("url","/repair/showUnallocatedRepairsPage");
        }else {
            return R.error().message("分配失败");
        }
    }
}

