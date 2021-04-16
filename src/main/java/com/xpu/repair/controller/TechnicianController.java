package com.xpu.repair.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.enums.RepairStatusEnum;
import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.Maintenance;
import com.xpu.repair.pojo.entity.Profession;
import com.xpu.repair.pojo.entity.Repair;
import com.xpu.repair.pojo.entity.Technician;
import com.xpu.repair.pojo.vo.MaintenanceVO;
import com.xpu.repair.service.*;
import com.xpu.repair.pojo.vo.TechnicianVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 维修人员表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/technician")
public class TechnicianController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TechnicianService technicianService;

    @Autowired
    ProfessionService professionService;

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    RepairService repairService;

    @Autowired
    FileService fileService;

    /**
     * 维修人员登录
     * @param id
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO login(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request){
        logger.info("================technician login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================technician login===================");

        if (id != null && password != null){  //账号密码不为空
            Technician technician = technicianService.getById(id);

            if (technician != null && password.equals(technician.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("technician",technician);
                return ResultDTO.ok().data("url","technician/index");
            }
        }
        return ResultDTO.error().message("账号或密码错误,请重新登录");
    }

    /**
     * 跳转首页面
     * @return
     */
    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    public String technicianIndex(){
        return "technician/index";
    }

    /**
     * 跳转维修人员个人信息页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/infoPage",method = RequestMethod.GET)
    public String showInfoPage(HttpServletRequest request,Model model) {
        Technician technician = (Technician) request.getSession().getAttribute("technician");
        Integer professionId = technician.getProfessionId();
        //个人工种
        Profession myProfession = professionService.getById(professionId);
        //所有工种
        List<Profession> professionList = professionService.list(null);
        model.addAttribute("myProfession",myProfession);
        model.addAttribute("professionList",professionList);

        return "technician/technicianMessage";
    }

    /**
     * 维修人员更新
     * @param technician
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO update(Technician technician,HttpServletRequest request){
        Technician technicianSession = (Technician) request.getSession().getAttribute("technician");
        String technicianSessionId = technicianSession.getId();

        boolean updateResult = technicianService.updateById(technician.setId(technicianSessionId));
        if (updateResult) {
            return ResultDTO.ok();
        }else {
            return ResultDTO.error();
        }
    }

    /**跳转到未完成维修页面
     *
     * @param model
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping(value = "/unCompleteMaintenancePage",method = RequestMethod.GET)
    public String showUnCompleteMaintenancePage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,HttpServletRequest request) {
        Technician technician = (Technician) request.getSession().getAttribute("technician");
        String technicianId = technician.getId();

        Page<MaintenanceVO> maintenanceVOPage = maintenanceService.listUnCompleteMaintenanceByTechnicianId(technicianId, pageNum);
        model.addAttribute("page",maintenanceVOPage);
        return "technician/unCompleteMaintenance";
    }

    /**
     * 跳转到维修完成完成页面
     * @param model
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping(value = "/completeMaintenancePage",method = RequestMethod.GET)
    public String showCompleteMaintenancePage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,HttpServletRequest request) {
        Technician technician = (Technician) request.getSession().getAttribute("technician");
        String technicianId = technician.getId();

        Page<MaintenanceVO> maintenanceVOPage = maintenanceService.listCompleteMaintenanceByTechnicianId(technicianId, pageNum);
        model.addAttribute("page",maintenanceVOPage);
        return "technician/completeMaintenance";
    }

    /**
     * 提交完成维修
     * @param maintenanceId
     * @param file
     * @return
     */
    @RequestMapping(value = "/completeMaintenance",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO completeMaintenance(int maintenanceId, MultipartFile file){
        //存储图片到OSS，获取指定url
        String uploadUrl = fileService.upload(file);

        //更新数据库
        //维修表
        Maintenance maintenance = maintenanceService.getById(maintenanceId);
        maintenance.setEndTime(new Date());
        maintenance.setPictureUrl(uploadUrl);
        boolean updateResultMaintenance = maintenanceService.updateById(maintenance);
        //报修表
        Repair repair = repairService.getById(maintenance.getRepairId());
        repair.setStatus(RepairStatusEnum.COMPLETE.getStatusId());
        boolean updateResultRepair = repairService.updateById(repair);
        //返回
        return ResultDTO.ok();
    }
}

