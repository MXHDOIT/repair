package com.xpu.repair.controller;


import com.xpu.repair.dto.R;
import com.xpu.repair.entity.Admin;
import com.xpu.repair.service.AdminService;
import com.xpu.repair.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public R login(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request){
        logger.info("================admin login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================admin login===================");

        if (id != null && password != null){  //账号密码不为空
            Admin admin = adminService.getById(id);

            if (admin != null && password.equals(admin.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("admin",admin);
                return R.ok().data("url","admin/index");
            }
        }
        return R.error().message("账号或密码错误,请重新登录");
    }
}

