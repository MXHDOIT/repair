package com.xpu.repair.controller;

import com.xpu.repair.pojo.dto.ResultDTO;
import com.xpu.repair.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private FileService fileService;

    /**
     * 跳转首页，登录
     * @return
     */
    @RequestMapping(value = {"/","/login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:login";
    }
}
