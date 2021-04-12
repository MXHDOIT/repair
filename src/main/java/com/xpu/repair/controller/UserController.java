package com.xpu.repair.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.User;
import com.xpu.repair.service.RepairService;
import com.xpu.repair.service.UserService;
import com.xpu.repair.pojo.vo.RepairVO;
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

    @Autowired
    RepairService repairService;

    /**
     * 用户登录
     * @param id
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO login(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request){
        logger.info("================user login===================");
        logger.info("admin id: "+id);
        logger.info("admin password: "+password);
        logger.info("================user login===================");

        if (id != null && password != null){  //账号密码不为空
            User user = userService.getById(id);

            if (user != null && password.equals(user.getPassword())){ //用户存在，并且密码正确
                request.getSession().setAttribute("user",user);
                return ResultDTO.ok().data("url","user/index");
            }
        }
        return ResultDTO.error().message("账号或密码错误,请重新登录");
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
    public ResultDTO delete(String userId){
        boolean result = userService.removeById(userId);
        if (result){
            return ResultDTO.ok().data("url","/user/showUsersPage");
        }
        return ResultDTO.error().message("删除失败");
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
    public ResultDTO add(User user){
        User userServiceById = userService.getById(user.getId());
        if (userServiceById != null){
            return ResultDTO.error().message("用户已经存在");
        }

        boolean save = userService.save(user);
        if (save){
            return ResultDTO.ok();
        }
        return ResultDTO.error().message("添加用户失败");
    }

    /**
     * 跳转到个人信息页面
     * @return
     */
    @RequestMapping(value = "/messagePage",method = RequestMethod.GET)
    public String messagePage(){
        return "user/userMessage";
    }

    /**
     * 用户更新
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO update(User user, HttpServletRequest request){
        User userSession = (User) request.getSession().getAttribute("user");
        user.setId(userSession.getId());

        boolean updateResult = userService.updateById(user);
        return ResultDTO.ok();
    }

    /**
     * 报修记录ByUserId
     * @param model
     * @param request
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/repairRecord",method = RequestMethod.GET)
    public String showRepairRecordPage(Model model,HttpServletRequest request, @RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum){
        User user = (User) request.getSession().getAttribute("user");
        String userId = user.getId();

        //查询报修单通过user_id
        Page<RepairVO> repairByUserId = repairService.findRepairByUserId(pageNum, userId);

        model.addAttribute("page",repairByUserId);

        return "/user/repairRecord";
    }

    /**
     * 跳转到提交报修记录页面
     * @return
     */
    @RequestMapping(value = "/addRepairPage",method = RequestMethod.GET)
    public String showAddRepairPage(){
        return "user/addRepair";
    }

    /**
     * 跳转到催单页面
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/remindersPage",method = RequestMethod.GET)
    public String showRemindersPage(Model model,@RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum){
        Page<RepairVO> reminders = repairService.findReminders(pageNum);

        model.addAttribute("page",reminders);
        return "user/reminders";
    }
}


