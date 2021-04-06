package com.xpu.repair.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.dto.R;
import com.xpu.repair.entity.User;
import com.xpu.repair.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MaXinHang
 * @since 2021-03-29
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param id
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R login(@RequestParam("id") String id,@RequestParam("password") String password, HttpServletRequest request){
        logger.info("================user login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================user login===================");

        if (id != null && password != null){  //账号密码不为空
            User user = userService.getById(id);

            if (user != null && password.equals(user.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("user",user);
                return R.ok().data("url","user/index");
            }
        }
        return R.error().message("账号或密码错误,请重新登录");
    }

    /**
     * 分页查询用户
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/showUsersPage",method = RequestMethod.GET)
    public String showUsersPage(Model model, @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum){
        Page<User> userByPage = userService.findUserByPage(pageNum);
        model.addAttribute("page",userByPage);
        logger.info("userByPage"+userByPage.getRecords());
        return "admin/showUsers";
    }

    /**
     * 通过userId删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public R delete(String userId){
        boolean result = userService.removeById(userId);
        if (result){
            return R.ok().data("url","/user/showUsersPage");
        }
        return R.error().message("删除失败");
    }

    /**
     * 返回添加用户页面
     */
    @RequestMapping(value = "/addUserPage",method = RequestMethod.GET)
    public String addUserPage() {
        return "admin/addUser";
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public R add(User user){
        User userServiceById = userService.getById(user.getId());
        if (userServiceById != null){
            return R.error().message("用户已经存在");
        }

        boolean save = userService.save(user);
        if (save){
            return R.ok();
        }
        return R.error().message("添加用户失败");
    }
}

