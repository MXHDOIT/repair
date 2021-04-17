package com.xpu.repair.controller;

import com.xpu.repair.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

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
