package com.xpu.repair.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xpu.repair.dto.R;
import com.xpu.repair.entity.Profession;
import com.xpu.repair.entity.Technician;
import com.xpu.repair.entity.User;
import com.xpu.repair.service.ProfessionService;
import com.xpu.repair.service.TechnicianService;
import com.xpu.repair.vo.TechnicianVo;
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

    /**
     * 维修人员登录
     * @param id
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R login(@RequestParam("id") String id,@RequestParam("password") String password,  HttpServletRequest request){
        logger.info("================technician login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================technician login===================");

        if (id != null && password != null){  //账号密码不为空
            Technician technician = technicianService.getById(id);

            if (technician != null && password.equals(technician.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("technician",technician);
                return R.ok().data("url","technician/index");
            }
        }
        return R.error().message("账号或密码错误,请重新登录");
    }

    /**
     * 分页查询维修人员
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/showTechniciansPage",method = RequestMethod.GET)
    public String showTechniciansPage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum){
        Page<TechnicianVo> technicianPage = technicianService.findTechnicianPage(pageNum);
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
    public R delete(String technicianId){
        boolean result = technicianService.removeById(technicianId);
        if (result){
            return R.ok().data("url","/technician/showTechniciansPage");
        }
        return R.error().message("删除失败");
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
    public R add(Technician technician){
        Technician technicianServiceById = technicianService.getById(technician.getId());
        if (technicianServiceById != null){
            return R.error().message("维修人员已经存在");
        }

        boolean save = technicianService.save(technician);
        if (save){
            return R.ok();
        }
        return R.error().message("添加维修人员失败");
    }

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
}

