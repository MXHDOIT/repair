package com.xpu.repair.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpu.repair.enums.RepairStatusEnum;
import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.pojo.entity.*;
import com.xpu.repair.service.*;
import com.xpu.repair.pojo.vo.RepairVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    TechnicianService technicianService;

    @Autowired
    UrgentrepairService urgentrepairService;

    @Autowired
    FileService fileService;

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

    /**
     * 进行催单，未完成
     * @param repairId
     * @param request
     * @return
     */
    @RequestMapping(value = "/reminders",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO reminders(int repairId,HttpServletRequest request){
        Repair repair = repairService.getById(repairId);
        //得到状态
        Integer status = repair.getStatus();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("maxinhangdoit@163.com");
        if (status == RepairStatusEnum.UNALLOCATED.getStatusId()){
            //未分配，给管理员发邮件
            mailMessage.setTo("2826372596@qq.com");
        }else {
            //分配未完成，给维修人员发邮件，并产生记录
            QueryWrapper<Maintenance> queryWrapper = new QueryWrapper<>();
            Maintenance maintenance = maintenanceService.getOne(queryWrapper.eq("repair_id", repairId));
            Technician technician = technicianService.getById(maintenance.getTechnicianId());
            //接受者邮箱
            mailMessage.setTo(technician.getEmail());

            //获得用户ID
            User user = (User) request.getSession().getAttribute("user");

            //记录入库
            Urgentrepair urgentrepair = new Urgentrepair();
            urgentrepair.setStatus(repair.getStatus());
            urgentrepair.setRepairId(repairId);
            urgentrepair.setUserId(user.getId());
            urgentrepair.setCreateTime(new Date());
            urgentrepairService.save(urgentrepair);
        }
        mailMessage.setSubject(repairId+"号报修催单");
        mailMessage.setText("在"+repair.getPlace()+"发生"+repair.getDetail()+"故障，影响正常生活和学习，请及时维修");
        javaMailSender.send(mailMessage);
        return ResultDTO.ok().data("url","/user/remindersPage");
    }

    /**
     * 删除未分配的报修记录
     * @param repairId
     * @return
     */
    @RequestMapping(value = "/deleteRepair",method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO deleteRepair(int repairId) {
        Repair repair = repairService.getById(repairId);

        //只有未分配的可以删除
        if (repair.getStatus() == RepairStatusEnum.UNALLOCATED.getStatusId()){
            boolean removeResult = repairService.removeById(repairId);
            if (removeResult){
                return ResultDTO.ok().data("url","/user/repairRecord");
            }else {
                return ResultDTO.error().message("删除失败");
            }
        }

        return ResultDTO.error().message("不能删除已经分配或已经维修完成的报修记录");
    }

    /**
     * 新增报修
     * @param detail
     * @param place
     * @param file
     * @return
     */
    @RequestMapping(value = "/addRepair",method = RequestMethod.POST)
    public String addRepair(String detail, String place, MultipartFile file,HttpServletRequest request) {
        //上传图片到阿里云OSS返回访问url
        String uploadUrl = fileService.upload(file);
        //session中获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        Repair repair = new Repair();
        repair.setUserId(user.getId());
        repair.setDetail(detail);
        repair.setPlace(place);
        repair.setPictureUrl(uploadUrl);
        repair.setSubmitTime(new Date());
        boolean saveResult = repairService.save(repair);

        return "redirect:/user/repairRecord";
    }
}


