package com.xpu.repair.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.Maintenance;
import com.xpu.repair.pojo.entity.Profession;
import com.xpu.repair.pojo.entity.Technician;
import com.xpu.repair.pojo.vo.MaintenanceVO;
import com.xpu.repair.service.MaintenanceService;
import com.xpu.repair.service.ProfessionService;
import com.xpu.repair.service.TechnicianService;
import com.xpu.repair.pojo.vo.TechnicianVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 分页查询维修人员
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/showTechniciansPage",method = RequestMethod.GET)
    public String showTechniciansPage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum){
        Page<TechnicianVO> technicianPage = technicianService.findTechnicianPage(pageNum);
        model.addAttribute("page",technicianPage);
        logger.info("technicianByPage:{}",technicianPage.getRecords());
        return "admin/showTechnicians";
    }

    /**
     * 删除维修人员
     * technicianId
     * @param technicianId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO delete(String technicianId){
        boolean result = technicianService.removeById(technicianId);
        if (result){
            return ResultDTO.ok().data("url","/technician/showTechniciansPage");
        }
        return ResultDTO.error().message("删除失败");
    }

    /**
     * 返回添加维修人员页面
     */
    @RequestMapping(value = "/addTechnicianPage",method = RequestMethod.GET)
    public String addBookPage(Model model) {
        List<Profession> list = professionService.list(null);
        model.addAttribute("professionList",list);
        return "admin/addTechnician";
    }

    /**
     * 添加维修人员
     * @param technician
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO add(Technician technician){
        Technician technicianServiceById = technicianService.getById(technician.getId());
        if (technicianServiceById != null){
            return ResultDTO.error().message("维修人员已经存在");
        }

        boolean save = technicianService.save(technician);
        if (save){
            return ResultDTO.ok();
        }
        return ResultDTO.error().message("添加维修人员失败");
    }

    /**
     * 跳转个人信息页面
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

    @RequestMapping(value = "/unCompleteMaintenancePage",method = RequestMethod.GET)
    public String showUnCompleteMaintenancePage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,HttpServletRequest request) {
        Technician technician = (Technician) request.getSession().getAttribute("technician");
        String technicianId = technician.getId();

        Page<MaintenanceVO> maintenanceVOPage = maintenanceService.listUnCompleteMaintenanceByTechnicianId(technicianId, pageNum);
        model.addAttribute("page",maintenanceVOPage);
        return "technician/unCompleteMaintenance";
    }
}

