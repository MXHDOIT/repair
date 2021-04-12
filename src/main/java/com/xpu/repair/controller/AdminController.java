package com.xpu.repair.controller;


import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.Admin;
import com.xpu.repair.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * @param id
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO login(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request){
        logger.info("================admin login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================admin login===================");

        if (id != null && password != null){  //账号密码不为空
            Admin admin = adminService.getById(id);

            if (admin != null && password.equals(admin.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("admin",admin);
                return ResultDTO.ok().data("url","admin/index");
            }
        }
        return ResultDTO.error().message("账号或密码错误,请重新登录");
    }

    /**
     * 跳转个人信息页面
     * @return
     */
    @RequestMapping(value = "/infoPage",method = RequestMethod.GET)
    public String infoPage(Model model){
        return "admin/adminInfo";
    }
}

